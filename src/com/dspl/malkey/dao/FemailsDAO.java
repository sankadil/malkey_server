package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fdebtor;
import com.dspl.malkey.domain.Femails;
import com.dspl.malkey.domain.Femployee;
import com.dspl.malkey.domain.Fsupplier;

public interface FemailsDAO {
	int count();
	void create(Femails femails, List<Femails> lstAttachments);
	Femails findByID(String emailid);
	List<Femails> listAll();
	List<Femails> list(int startIndex, int numItems);
	void update(Femails femails, List<Femails> lstAttachments);
	void removeByID(String emailid);
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
	void moveToFolder(int iCurrentFolder, int iTargetFolder, int iRecordID);
}
