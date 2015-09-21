package com.dspl.malkey.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import com.dspl.malkey.dao.FarfdtfDAO;
import com.dspl.malkey.domain.Farfdtf;
import com.dspl.malkey.domain.Fdtftax;
import com.dspl.malkey.domain.Finvdet;
import com.dspl.malkey.domain.Finvhed;
import com.dspl.malkey.domain.Freservation;
import com.dspl.malkey.domain.Freshed;
import com.dspl.malkey.report.FinvdetRPT;

import flex.messaging.io.ArrayCollection;

public class FarfdtfSRVImpl implements FarfdtfSRV {

	@Resource(name="farfdtfDAO")
	FarfdtfDAO farfdtfDAO;
	
	@Override
	public List<Freshed> getAgreementList() {
		return farfdtfDAO.getAgreementList();
	}

	@Override
	public List<Freservation> getSubAgreementList(String agrno) {
		return farfdtfDAO.getSubAgreementList(agrno);
	}

	@Override
	public ArrayCollection genInvoice(Freshed freshed, ArrayCollection subAgrList) {
		try{
			return farfdtfDAO.genInvoice(freshed, subAgrList);
		}catch(Exception e){
			System.out.println("FarfdtfSRVImpl genInvoice: " + e.getMessage());
			//e.printStackTrace();
		}
		return null;
	}

	@Override
	public String saveInvoice(Finvhed finvhed,ArrayCollection acInvDet,List<Farfdtf> farfdtfList,List<Fdtftax> fdtftaxList) {
		return farfdtfDAO.saveInvoice(finvhed, acInvDet, farfdtfList, fdtftaxList);
	}

	@Override
	public Finvhed getInvHedByInvNo(String invno) {
		return farfdtfDAO.getInvHedByInvNo(invno);
	}
	
	@Override
	public List<FinvdetRPT> getInvDetByInvNo(String invno) {
		//return farfdtfDAO.getInvDetByInvNo2(invno);
		return farfdtfDAO.getInvDetByInvNo(invno);
		//return farfdtfDAO.getInvDetByInvNo_TAX(invno);
	}
	@Override
	public List<FinvdetRPT> getInvDetByInvNoSVAT(String invno) {
		//return farfdtfDAO.getInvDetByInvNo2(invno);
		return farfdtfDAO.getInvDetByInvNo2(invno);
		//return farfdtfDAO.getInvDetByInvNo_TAX(invno);
	}

	@Override
	public List<Finvhed> getInvoiceList() {
		return farfdtfDAO.getInvoiceList();
	}

	@Override
	public List<Finvdet> getInvDetById(String invno) {
		return farfdtfDAO.getInvDetById(invno);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return farfdtfDAO.count();
	}

	@Override
	public List<Finvhed> getInvoiceListPage(int startIndex, int numItems) {
		// TODO Auto-generated method stub
		return farfdtfDAO.getInvoiceList(startIndex, numItems);
	}

	@Override
	public List<Finvhed> getInvoiceListFeelLucky(String searchText) {
		return farfdtfDAO.getInvoiceListFeelLucky(searchText);
	}
	
	@Override
	public List<Finvhed> getInvoiceDateRange(String df,String dt) {
		return farfdtfDAO.getInvoiceDateRange( df, dt);
	}

	@Override
	public ArrayCollection genInvoice2(Freshed freshed,
			ArrayCollection subAgrList, String fromDateStr, String toDateStr) {
		try{
			System.out.println("genInvoice2 ####################################");
			return farfdtfDAO.genInvoice2(freshed, subAgrList,fromDateStr,toDateStr);
		}catch(Exception e){
			System.out.println("FarfdtfSRVImpl genInvoice: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Freservation> getSubAgreementList2(String agrno) {
		return farfdtfDAO.getSubAgreementList2(agrno);
	}

	@Override
	public String saveInvoice2(Finvhed finvhed, ArrayCollection acInvDet,
			List<Farfdtf> farfdtfList, List<Fdtftax> fdtftaxList) {
		return farfdtfDAO.saveInvoice2(finvhed, acInvDet, farfdtfList, fdtftaxList);
	}

	@Override
	public List<FinvdetRPT> getInvDetByInvNo(String invno, boolean flag) {
		return farfdtfDAO.getInvDetByInvNo(invno, flag);
	}
}
