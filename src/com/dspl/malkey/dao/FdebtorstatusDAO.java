package com.dspl.malkey.dao;

import java.util.List;
import com.dspl.malkey.domain.Fdebtorstatus;

public interface FdebtorstatusDAO {
	int count();
	void create(Fdebtorstatus fdebtorstatus);
	Fdebtorstatus findByID(String id);
	List<Fdebtorstatus> List(int startIndex, int numItems);
	List<Fdebtorstatus> ListAll();
	void update(Fdebtorstatus fdebtorstatus);
	void removeByID(String id);
}
