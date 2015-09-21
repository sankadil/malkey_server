package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fquote;

public interface FquoteDAO {
	int count();
	void create(Fquote fquote);
	Fquote findByID(String quoteno);
	List<Fquote> List(int startIndex, int numItems);
	List<Fquote> ListAll();
	void udpate(Fquote fquote);
	void removeByID(String quoteno);
}
