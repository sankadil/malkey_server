package com.dspl.malkey.dao;

import java.util.List;
import com.dspl.malkey.domain.Fusertype;

public interface FusertypeDAO {
	int count();
	int countInAR(String UserTypeId);
	List<Fusertype> listAll();
	List<Fusertype> list(int startIndex, int numItems);
	void create(Fusertype fusertype);
	void update(Fusertype fusertype);
	void removeById(String usertypeid);
	Fusertype findById(String usertypeid);
}
