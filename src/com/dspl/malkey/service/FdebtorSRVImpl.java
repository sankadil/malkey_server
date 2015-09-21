package com.dspl.malkey.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.dspl.malkey.dao.FdebtorDAO;
import com.dspl.malkey.domain.Fdebtor;

public class FdebtorSRVImpl implements FdebtorSRV {

	@PersistenceContext
	EntityManager em;
	
	@Resource(name="fdebtorDAO")
	FdebtorDAO fdebtorDAO;//=new fdebterImpl();
	
	@Override
	public List<Fdebtor> List(int startIndex, int numItems) {
		return fdebtorDAO.List(startIndex, numItems);
	}

	@Override
	public java.util.List<Fdebtor> ListAll() {
		return fdebtorDAO.ListAll();
		//return fdebtorDAO.listAllOptimized();
	}

	@Override
	public int count() {
		return fdebtorDAO.count();
	}

	@Override
	public String create(Fdebtor fdebtor,ArrayList driList) {
		return fdebtorDAO.create(fdebtor,driList);
	}

	@Override
	public Fdebtor findByID(String debcode) {
		return fdebtorDAO.findByID(debcode);
	}

	@Override
	public Boolean removeByID(String debcode) {
		return fdebtorDAO.removeByID(debcode);
	}

	@Override
	public Boolean update(Fdebtor fdebtor,ArrayList driList) {
		return	fdebtorDAO.update(fdebtor, driList);
	}

	@Override
	public List<Fdebtor> getDebList() {
		return fdebtorDAO.getDebList();
	}

	@Override
	public List<Fdebtor> getGDebList() {
		return fdebtorDAO.getGDebList();
	}

	@Override
	public List<Fdebtor> getDebtorListRPT() {
		return fdebtorDAO.getDebtorList();
	}
	
	@Override
	public List<Fdebtor> getDebtorList(String clientType,boolean isLongterm) {
		return fdebtorDAO.getDebtorList(clientType,isLongterm);
	}

	@Override
	public List<Fdebtor> listAllOptimized() {
		return fdebtorDAO.listAllOptimized();
	}

	@Override
	public List<Fdebtor> listAllOptimizedPage(int startIndex, int numItems) {
		return fdebtorDAO.listAllOptimizedPage(startIndex, numItems);
	}

	@Override
	public List<Fdebtor> listAllNameNIC() {
		return fdebtorDAO.listAllNameNIC();
	}

	@Override
	public List<Fdebtor> listLikeName(String name) {
		return fdebtorDAO.listLikeName(name);
	}

}
