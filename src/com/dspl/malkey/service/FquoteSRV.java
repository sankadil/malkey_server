package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fquote;

public interface FquoteSRV {
	int count();
	Boolean create(Fquote fquote);
	Fquote findByID(String quoteno);
	List<Fquote> List(int startIndex, int numItems);
	List<Fquote> ListAll();
	Boolean udpate(Fquote fquote);
	Boolean removeByID(String quoteno);
}
