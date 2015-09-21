package com.dspl.malkey.dao;

import java.util.List;
import com.dspl.malkey.domain.Fpass;

public interface FpassDAO {
	int count();
	List<Fpass> listAll();
	List<Fpass> list(int startIndex, int numItems);
	void create(Fpass fpass);
	void update(Fpass fpass);
	void removeById(String userid);
	Fpass findById(String userid);
	List<Fpass> listUsers();
}
