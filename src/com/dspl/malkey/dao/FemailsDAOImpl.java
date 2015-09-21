package com.dspl.malkey.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.dspl.malkey.domain.Fcontrol;
import com.dspl.malkey.domain.Fdebtor;
import com.dspl.malkey.domain.Femails;
import com.dspl.malkey.domain.Femployee;
import com.dspl.malkey.domain.Fsupplier;
import com.dspl.malkey.service.FcontrolSRV;
import com.dspl.malkey.service.FileUploadSRV;

import flex.messaging.FlexContext;

public class FemailsDAOImpl implements FemailsDAO {
	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fileUploadSRV")
	FileUploadSRV fileUploadSRV;

	@Resource(name="fcontrolSRV")
	FcontrolSRV fcontrolSRV;
	
	@Transactional
	@Override
	public List<Femails> list(int startIndex, int numItems) {
		return em.createNamedQuery("FemailsListAll").setFirstResult(startIndex).setMaxResults(numItems).getResultList();
	}

	@Transactional
	@Override
	public List<Femails> listAll() {
		return em.createNamedQuery("FemailsListAll").getResultList();
	}

	@Transactional
	@Override
	public int count() {
		return (Integer)(em.createNativeQuery("SELECT COUNT(recordid) AS COUNT FROM Femails").getSingleResult());
	}

	@Transactional(readOnly=false)
	@Override
	public void create(Femails femails, List<Femails> lstAttachments) {
		if (lstAttachments.size()>0)
			uploadAttachments(femails, lstAttachments);		// Upload any new attachments to the server
		
		em.persist(femails);
	}

	@Transactional
	@Override
	public Femails findByID(String emailid) {
		return em.find(Femails.class, emailid);
	}

	@Transactional(readOnly=false)
	@Override
	public void removeByID(String emailid) {
		em.remove(em.find(Femails.class, emailid));
	}

	@Transactional(readOnly=false)
	@Override
	public void update(Femails femails, List<Femails> lstAttachments) {
		if (lstAttachments.size()>0)
			uploadAttachments(femails, lstAttachments);		// Upload any new attachments to the server
		
		em.merge(femails);
	}

	@Transactional
	@Override
	public int countOnFolderType(int iFolderType) {
		return (Integer)(em.createNativeQuery("SELECT COUNT(recordid) AS COUNT FROM Femails WHERE foldertype=?1")
						.setParameter(1, iFolderType)
						.getSingleResult());
	}

	@Transactional
	@Override
	public int countUnreadOnFolderType(int iFolderType) {
		Integer iRecCount = (Integer)(em.createNativeQuery("SELECT COUNT(recordid) AS COUNT FROM Femails WHERE readstatus=1 AND foldertype="+iFolderType).getSingleResult());
		return iRecCount;
	}

	@Transactional
	@Override
	public List<Femails> listAllOnFolderType(int iFolderType) {
		Query query = em.createNamedQuery("FemailsListAllOnFolderType").setParameter("ifolderType", iFolderType);
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<Femails> listOnFolderType(int iFolderType,int startIndex, int numItems) {
		Query query = em.createNamedQuery("FemailsListAllOnFolderType").setParameter("ifolderType", iFolderType);
		query.setFirstResult(startIndex).setMaxResults(numItems);
		List<Femails> res = query.getResultList();
		return res;
	}

	@Transactional
	@Override
	public void updateReadStatus(int iReadSts, int iRecordID) {
		// Make use of this method update the read status of a particular email record. 0 - means Read Mail and 1 - means Un-read Mail
		
		em.createQuery("UPDATE Femails f " +
						"SET f.readstatus=?1 " +
						"WHERE f.recordid=?2")
		.setParameter(1, iReadSts)
		.setParameter(2, iRecordID)
		.executeUpdate();
	}

	@Transactional
	@Override
	public void moveToTrash(int iCurrentFolder, int iRecordID) {
		// Make use of this method move an email to the trash.
		em.createQuery("UPDATE Femails f " +
						"SET f.foldertype=3, f.movedfrom=?1, f.dmoved=?3 " +
						"WHERE f.recordid=?2")
		.setParameter(1, iCurrentFolder)
		.setParameter(2, iRecordID)
		.setParameter(3, new Date())
		.executeUpdate();
	}

	@Transactional
	@Override
	public void moveToFolder(int iCurrentFolder, int iTargetFolder, int iRecordID) {
		// Make use of this method move an email to the trash.
		em.createQuery("UPDATE Femails f " +
				"SET f.foldertype=?2, f.movedfrom=?1, f.dmoved=?3 " +
				"WHERE f.recordid=?4")
		.setParameter(1, iCurrentFolder)
		.setParameter(2, iTargetFolder)
		.setParameter(3, new Date())
		.setParameter(4, iRecordID)
		.executeUpdate();
	}

	@Transactional
	@Override
	public void deleteForever(List<Femails> femails) {
		// Make use of this method delete email messages permanently.
		
		// Get hold of the default attachment folder under the server
		Fcontrol fcontrol = fcontrolSRV.ListAll();
		String lsDefaultAttFldOnServer = fcontrol.getAttfldonserver().trim();
		// Remove any existing Front/ Back slashes
		lsDefaultAttFldOnServer = lsDefaultAttFldOnServer.replace("/", "");
		lsDefaultAttFldOnServer = lsDefaultAttFldOnServer.replace("\\", "");
				
		Iterator<Femails> mails = femails.iterator();
		while (mails.hasNext()){
			Femails mailDtl = (Femails)mails.next();
			
			if (mailDtl.getAttnames().length()>0){
				// Mail has attachment(s)
				String lsMailsUniqueAttStoreFolder = mailDtl.getUniqattfld().trim();
				
				// Remove any existing Front/ Back slashes
				lsMailsUniqueAttStoreFolder = lsMailsUniqueAttStoreFolder.replace("/", "");
				lsMailsUniqueAttStoreFolder = lsMailsUniqueAttStoreFolder.replace("\\", "");
				
				// If the any attachments were linked with this mail, delete them from the file system as well
				String lsAttachmentStoreFolderOnServer = "/" + lsDefaultAttFldOnServer + "/" + lsMailsUniqueAttStoreFolder + "/";
				fileUploadSRV.deleteFolderAndItsContents(lsAttachmentStoreFolderOnServer);
			}
			
			em.createQuery("DELETE FROM Femails f WHERE f.recordid=?1")
			.setParameter(1, mailDtl.getRecordid())
			.executeUpdate();
		}
	}

	@Transactional
	@Override
	public List<Fdebtor> listDebtorEmailID() {
		List<Fdebtor> lst = em.createNamedQuery("Fdebtor.EmailID").getResultList();
		System.out.println(lst.size());
		return lst;
	}

	@Transactional
	@Override
	public List<Femployee> listEmployeeEmailID() {
		List<Femployee> lst = em.createNamedQuery("Femployee.EmailID").getResultList();
		System.out.println(lst.size());
		return lst;
	}

	@Transactional
	@Override
	public List<Fsupplier> listSupplierEmailID() {
		List<Fsupplier> lst = em.createNamedQuery("Fsupplier.EmailID").getResultList();
		System.out.println(lst.size());
		return lst;
	}
	
	@Transactional
	private void uploadAttachments(Femails femails, List<Femails> lstAttachments){
		if (lstAttachments.size() > 0){
			// Get hold of the default attachment folder under the server
			Fcontrol fcontrol = fcontrolSRV.ListAll();
			String lsDefaultAttFldOnServer = fcontrol.getAttfldonserver().trim();
			// Remove any existing Front/ Back slashes
			lsDefaultAttFldOnServer = lsDefaultAttFldOnServer.replace("/", "");
			lsDefaultAttFldOnServer = lsDefaultAttFldOnServer.replace("\\", "");
			
			String lsLineFeed = "\n";
			String lsAttNames = "";			// Only the attachment file names separated with line break
			String lsAttachments = "";		// List of attachment files with the full Server URL for individual file separated with line break
			String lsMailsUniqueAttStoreFolder = "";
			
			// If this mail record is already saved then make use of the any existing attachment store folder. For new mails, always make use of separate new folder
			if (femails.getRecordid()>0 && femails.getUniqattfld()!=null)
				// The attachments are stored under "attachments" folder and within sub-folder with unique name. If this email record happens to have already saved
				// attachments then get hold of the unique folder name from the previously uploaded files. Otherwise compute a unique sub-folder name for this email message.
				lsMailsUniqueAttStoreFolder = femails.getUniqattfld().trim();
			
			if (lsMailsUniqueAttStoreFolder.length()==0){
				lsMailsUniqueAttStoreFolder = String.valueOf(UUID.randomUUID());		// Create unique name for attachment storage
				femails.setUniqattfld(lsMailsUniqueAttStoreFolder);
			}

			// Remove any existing Front/ Back slashes
			lsMailsUniqueAttStoreFolder = lsMailsUniqueAttStoreFolder.replace("/", "");
			lsMailsUniqueAttStoreFolder = lsMailsUniqueAttStoreFolder.replace("\\", "");
			
			String lsTargetFolder = "/" + lsDefaultAttFldOnServer + "/" + lsMailsUniqueAttStoreFolder + "/";
			String lsServerName = "/resource";//FlexContext.getHttpRequest().getContextPath();		// Only the server name (eg. /malkey_server)
			
			Iterator<Femails> newAtt = lstAttachments.iterator();
			while (newAtt.hasNext()){
				Femails attFileInfo = (Femails)newAtt.next();
				
				// If the attachment file is selected to be deleted, then remove physical file as well ensure the email record does not get updated
				if (attFileInfo.getIsdeleted()){ 
					if (attFileInfo.getFileurl().length()>0){							// Only delete already uploaded files
						String lsFileToDelete = attFileInfo.getFileurl();
						// Remove the server name from the stored file name
						if (lsFileToDelete.contains(lsServerName))
							lsFileToDelete = lsFileToDelete.replace(lsServerName, "");	// "deleteFile()" only requires relative path within the server 
							
						fileUploadSRV.deleteFile(lsFileToDelete);
					}						
					continue;		// Skip to next record
				}
				
				// Active attachment files
				if (attFileInfo.getNewlyadded()==1){
					// New attachment, upload it
					String lsFileName = attFileInfo.getFilename();
					String lsFilePath = lsServerName + lsTargetFolder + lsFileName;
					
					// Upload the New File to server
					Boolean lbFileUploaded = fileUploadSRV.uploadFileToServer(lsFileName, attFileInfo.getFiledata(), lsTargetFolder);
					
					if (lbFileUploaded==true){
						// Update the email with the uploaded attachment
						lsAttNames += lsFileName + lsLineFeed;
						lsAttachments += lsFilePath + lsLineFeed;
					}					
				} else {		// Already uploaded attachment file
					if (femails.getRecordid()>0){
						lsAttNames += attFileInfo.getFilename() + lsLineFeed;
						lsAttachments += attFileInfo.getFileurl() + lsLineFeed;
					} else {	
						/** This new mail is created using an existing mail. i.e. a mail in the Inbox/ Sent Item is being forwarded.  
						 * So create a copy of the attachments and move it the mails corresponding attachment storage folder. 
						 */
						String lsFileName = attFileInfo.getFilename();
						String lsSourceFile = attFileInfo.getFileurl();				// "/malkey_server/attachments/worldmap.jpg"
						
						// Remove the server name from the stored file name
						if (lsSourceFile.contains(lsServerName))
							lsSourceFile = lsSourceFile.replace(lsServerName, "");	// "copyFile()" only requires relative path within the server 
						
						// Target file name
						String lsTargetFile = lsTargetFolder + lsFileName;
						
						Boolean lbFileUploaded = fileUploadSRV.copyFile(lsSourceFile, lsTargetFile);
						
						if (lbFileUploaded==true){
							// Update the email with the uploaded attachment
							lsAttNames += lsFileName + lsLineFeed;
							lsAttachments += lsServerName + lsTargetFile + lsLineFeed;
						}
					}
				}				
			}

			// Remove trailing line feed character
			if (lsAttNames.endsWith(lsLineFeed)){
				int inLength = lsAttNames.length();
				lsAttNames = lsAttNames.substring(0, inLength-1);
			}

			if (lsAttachments.endsWith(lsLineFeed)){
				int inLength = lsAttachments.length();
				lsAttachments = lsAttachments.substring(0, inLength-1);
			}

			femails.setAttnames(lsAttNames);
			femails.setAttachment(lsAttachments);
		}
	}

}
