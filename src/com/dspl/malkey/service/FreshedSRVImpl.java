package com.dspl.malkey.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;

import com.dspl.malkey.dao.FreshedDAO;
import com.dspl.malkey.domain.Freshed;
import com.dspl.malkey.util.MailMail;

public class FreshedSRVImpl implements FreshedSRV {

	@Resource(name="freshedDAO")
	FreshedDAO freshedDAO;
	
	@Resource(name="mailMail")
	MailMail mailMail;
	
	
	@Value( "${email.alert.dear}")
	String contect1 = "";
	@Value( "${email.alert.content}")
	String contect2 = "";
	

	@Override
	public void runTask() {
		System.out.println("FreshedSRVImpl.doSomething()");
		mailMail.sendMail(contect1,contect2);
	}
	

	

	
	
	
	@Override
	public int count() {
		return freshedDAO.count();
	}

	@Override
	public Boolean create(Freshed freshed) {
		return freshedDAO.create(freshed);
	}

	@Override
	public Freshed findByID(String agrno) {
		return freshedDAO.findByID(agrno);
	}

	@Override
	public List<Freshed> list(int startIndex, int numItems) {
		return freshedDAO.list(startIndex, numItems);
	}

	@Override
	public List<Freshed> listAll() {
		return freshedDAO.listAll();
	}

	@Override
	public Boolean removeByID(String agrno) {
		return freshedDAO.removeByID(agrno);
	}

	@Override
	public Boolean update(Freshed freshed) {
		return freshedDAO.update(freshed);
	}

	@Override
	public List<Freshed> listAllDescription() {
		return freshedDAO.listAllDescription();
	}
	@Override
	public List<Freshed> listAllDescriptionPage(int startIndex, int numItems) {
		return freshedDAO.listAllDescriptionPage(startIndex, numItems);
	}
	@Override
	public List<Freshed> feelLucky(String searchText) {
		return freshedDAO.feelLucky(searchText);
	}

}
