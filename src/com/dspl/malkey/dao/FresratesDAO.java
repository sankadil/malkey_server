package com.dspl.malkey.dao;

import java.util.List;

import com.dspl.malkey.domain.Fresrates;
import com.dspl.malkey.domain.FresratesPK;

public interface FresratesDAO {
	int count();
	void create(Fresrates fresrates);
	Fresrates findByID(FresratesPK fresratesPK);
	List<Fresrates> List(int startIndex, int numItems);
	List<Fresrates> ListAll();
	void udpate(Fresrates fresrates);
	void removeByID(FresratesPK fresratesPK);
}
