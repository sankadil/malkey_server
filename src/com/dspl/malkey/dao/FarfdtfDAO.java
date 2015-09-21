package com.dspl.malkey.dao;

import java.util.List;
import com.dspl.malkey.domain.Farfdtf;
import com.dspl.malkey.domain.Fdtftax;
import com.dspl.malkey.domain.Finvdet;
import com.dspl.malkey.domain.Finvhed;
import com.dspl.malkey.domain.Fresaddcharge;
import com.dspl.malkey.domain.Freservation;
import com.dspl.malkey.domain.Freshed;
import com.dspl.malkey.report.FinvdetRPT;

import flex.messaging.io.ArrayCollection;

public interface FarfdtfDAO {
	List<Freshed> getAgreementList();
	List<Freservation> getSubAgreementList(String agrno);
	ArrayCollection genInvoice(Freshed freshed,ArrayCollection subAgrList);
	String saveInvoice(Finvhed finvhed,ArrayCollection acInvDet,List<Farfdtf> farfdtfList,List<Fdtftax> fdtftaxList);
	Finvhed getInvHedByInvNo(String invno);
	List<FinvdetRPT> getInvDetByInvNo(String invno);
	List<Finvdet> getInvDetById(String invno);
	List<Finvhed> getInvoiceList();
	int count();
	List<Finvhed> getInvoiceList(int startIndex, int numItems);
	List<Finvhed> getInvoiceListFeelLucky(String searchText);
	List<Finvhed> getInvoiceDateRange(String df,String dt);
	ArrayCollection genInvoice2(Freshed freshed, ArrayCollection subAgrList,String fromDateStr,String toDateStr);
	List<Freservation> getSubAgreementList2(String agrno);
	String saveInvoice2(Finvhed finvhed, ArrayCollection acInvDet,
			List<Farfdtf> farfdtfList, List<Fdtftax> fdtftaxList);
	List<FinvdetRPT> getInvDetByInvNo2(String invno);
	List<FinvdetRPT> getInvDetByInvNo(String invno, boolean flag);
}
