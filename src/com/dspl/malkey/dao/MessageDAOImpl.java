package com.dspl.malkey.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.exceptions.DatabaseException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.dspl.malkey.domain.Tabledescription;
import com.dspl.malkey.domain.Tabletype;
import com.microsoft.sqlserver.jdbc.SQLServerException;

public class MessageDAOImpl implements MessageDAO {

	@PersistenceContext
	EntityManager em;
	

	/***
	 * 
	 * 
	 * @param e :PersistenceException of type exception
	 * @return :String error message
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String getMessage(PersistenceException e) {
		String msg="";
			DatabaseException sqlex=(DatabaseException) e.getCause();
			msg=getProcessMessage(sqlex);
		return msg;
	}
	
	/***
	 * 
	 * 
	 * @param e :TransactionSystemException of type exception
	 * @return :error message
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public String getMessage(TransactionSystemException e) {
		String msg="";
		if(e.contains(SQLServerException.class))
		{
			SQLServerException sqlex=(SQLServerException) e.getCause().getCause().getCause();
			msg=getProcessMessage(sqlex);
		}
		return msg;
	}

	
	/***
	 * 
	 * 
	 * @param msg :error messsage of exception
	 * @return :table name
	 */
	private String processDBMSG(String msg)
	{
		
		int indexOfTable=msg.indexOf("table");
		String offset=msg.substring(indexOfTable+5);
		
		int indexOfTable_=offset.indexOf("dbo.");
		String offset_=offset.substring(indexOfTable_+4);
		
		int indexOfTable_1=offset_.indexOf("\"");
		String tableName=offset_.substring(0,indexOfTable_1);
		
		return tableName;
		
	}


	/***
	 * 
	 * 
	 * @param msg :e of type Exception
	 * @return :table name
	 */
	private String getProcessMessage(Exception e)
	{
		String msg="";
		String getMesg=e.getMessage();
		String TABLE_NAME=processDBMSG(getMesg);
		Tabledescription tabledescription=(Tabledescription)em.createNamedQuery("TabledescriptionFindByTablename").setParameter("tablename",TABLE_NAME).getSingleResult();
		Tabletype tabletype=(Tabletype)em.createNamedQuery("TabletypeFindByType").setParameter("type",tabledescription.getType()).getSingleResult();
		//msg="Sorry\nthe record you are trying to delete is being refered by "+tabledescription.getDescription()+" "+tabletype.getType();
		msg="Already used in "+tabledescription.getDescription()+" "+tabletype.getType();
		return msg;
	}
	
}
