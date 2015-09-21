package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fdebtor;
import com.dspl.malkey.domain.Femails;
import com.dspl.malkey.domain.Femployee;
import com.dspl.malkey.domain.Fsupplier;

public interface FemailsSRV {
	int count(); 
	Boolean create(Femails femails, List<Femails> lstAttachments);
	Femails findByID(String emailid);
	List<Femails> list(int startIndex, int numItems);
	List<Femails> listAll();
	Boolean update(Femails femails, List<Femails> lstAttachments);
	Boolean removeByID(String emailid);
	int countOnFolderType(int iFolderType);
	int countUnreadOnFolderType(int iFolderType);
	List<Femails> listAllOnFolderType(int iFolderType);
	List<Femails> listOnFolderType(int iFolderType, int startIndex, int numItems);
	void updateReadStatus(int iReadSts, int iRecordID);
	void moveToTrash(int iCurrentFolder, int iRecordID);
	void deleteForever(List<Femails> femails);
	List<Fdebtor> listDebtorEmailID();
	List<Femployee> listEmployeeEmailID();
	List<Fsupplier> listSupplierEmailID();
//	void moveSelectedEmailsToTrash(List<Femails> femails);
	void updateSelectedEmailsReadStatus(List<Femails> femails, int iReadSts);
	void moveToFolder(List<Femails> femails);
}
