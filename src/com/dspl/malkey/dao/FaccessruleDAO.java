package com.dspl.malkey.dao;  

import java.util.List;
import com.dspl.malkey.domain.Faccessrule;
import flex.messaging.io.ArrayCollection;

public interface FaccessruleDAO 
{
	List<Faccessrule> findByUserTypeId(String UserTypeId);
	Boolean insert(String UserTypeId,List<Faccessrule> faccessrule);
	ArrayCollection getFunctionList(String UserTypeId,String TransCode);
}
         