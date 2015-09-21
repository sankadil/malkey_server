package com.dspl.malkey.service;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FemailsDAO;
import com.dspl.malkey.domain.Fdebtor;
import com.dspl.malkey.domain.Femails;
import com.dspl.malkey.domain.Femployee;
import com.dspl.malkey.domain.Fsupplier;

public class FemailsSRVImpl implements FemailsSRV {
	@Resource(name="femailsDAO")
	FemailsDAO femailsDAO;

	@Override
	public List<Femails> list(int startIndex, int numItems) {
		return femailsDAO.list(startIndex, numItems);
	}

	@Override
	public List<Femails> listAll() {
		return femailsDAO.listAll();
	}

	@Override
	public int count() {
		return femailsDAO.count();
	}

	@Override
	public Boolean create(Femails femails, List<Femails> lstAttachments) {
		try {
			femailsDAO.create(femails, lstAttachments);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Femails findByID(String emailid) {
		return femailsDAO.findByID(emailid);
	}

	@Override
	public Boolean removeByID(String emailid) {
		try {
			femailsDAO.removeByID(emailid);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean update(Femails femails, List<Femails> lstAttachments) {
		try {
			femailsDAO.update(femails, lstAttachments);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public int countOnFolderType(int iFolderType) {
		return femailsDAO.countOnFolderType(iFolderType);
	}

	@Override
	public int countUnreadOnFolderType(int iFolderType) {
		return femailsDAO.countUnreadOnFolderType(iFolderType);
	}

	@Override
	public List<Femails> listAllOnFolderType(int iFolderType) {
		return femailsDAO.listAllOnFolderType(iFolderType);
	}

	@Override
	public List<Femails> listOnFolderType(int iFolderType, int startIndex, int numItems) {
		return femailsDAO.listOnFolderType(iFolderType, startIndex, numItems);
	}

	@Override
	public void updateReadStatus(int iReadSts, int iRecordID) {
		femailsDAO.updateReadStatus(iReadSts, iRecordID);
	}

	@Override
	public void moveToTrash(int iCurrentFolder, int iRecordID) {
		femailsDAO.moveToTrash(iCurrentFolder, iRecordID);
	}

	@Override
	public void deleteForever(List<Femails> femails) {
		femailsDAO.deleteForever(femails);
	}

	@Override
	public List<Fdebtor> listDebtorEmailID() {
		return femailsDAO.listDebtorEmailID();
	}

	@Override
	public List<Femployee> listEmployeeEmailID() {
		return femailsDAO.listEmployeeEmailID();
	}

	@Override
	public List<Fsupplier> listSupplierEmailID() {
		return femailsDAO.listSupplierEmailID();
	}

//	@Override
//	public void moveSelectedEmailsToTrash(List<Femails> femails) {
//		Iterator<Femails> mails = femails.iterator();
//		while (mails.hasNext()){
//			Femails mailDtl = (Femails)mails.next();
//			femailsDAO.moveToTrash(mailDtl.getFoldertype(), mailDtl.getRecordid());
//		}
//	}

	@Override
	public void updateSelectedEmailsReadStatus(List<Femails> femails, int iReadSts) {
		Iterator<Femails> mails = femails.iterator();
		while (mails.hasNext()){
			Femails mailDtl = (Femails)mails.next();
			femailsDAO.updateReadStatus(iReadSts, mailDtl.getRecordid());
		}
	}

	@Override
	public void moveToFolder(List<Femails> femails) {
		Iterator<Femails> mails = femails.iterator();
		while (mails.hasNext()){
			Femails mailDtl = (Femails)mails.next();
			femailsDAO.moveToFolder(mailDtl.getFoldertype(), mailDtl.getMovedfrom(), mailDtl.getRecordid());
		}
	}
}
