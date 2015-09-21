package com.dspl.malkey.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FaccessruleDAO;
import com.dspl.malkey.domain.Faccessrule;

import flex.messaging.io.ArrayCollection;

public class FaccessruleSRVImpl implements FaccessruleSRV {

	@Resource(name="faccessruleDAO")
	FaccessruleDAO faccessruleDAO;
	
	@Override
	public List<Faccessrule> findByUserTypeId(String UserTypeId){
		return faccessruleDAO.findByUserTypeId(UserTypeId);
	}

	@Override
	public Boolean insert(String UserTypeId, List<Faccessrule> faccessrule){
		return faccessruleDAO.insert(UserTypeId, faccessrule);
	}

	@Override
	public ArrayCollection getFunctionList(String UserTypeId, String TransCode) {
		return faccessruleDAO.getFunctionList(UserTypeId, TransCode);
	}
}
