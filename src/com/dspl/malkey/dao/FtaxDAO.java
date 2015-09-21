package com.dspl.malkey.dao;

import java.util.List;
import com.dspl.malkey.domain.Ftax;

public interface FtaxDAO {
	int count();
	List<Ftax> listAll();
	List<Ftax> list(int startIndex, int numItems);
	void create(Ftax ftax);
	void update(Ftax ftax);
	void removeById(String taxcode);
	Ftax findById(String taxcode);
}
