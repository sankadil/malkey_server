package com.dspl.malkey.service;

import java.util.List;

import com.dspl.malkey.domain.Fresrates;
import com.dspl.malkey.domain.FresratesPK;

public interface FresratesSRV {
	int count();
	Boolean create(Fresrates fresrates);
	Fresrates findByID(FresratesPK fresratesPK);
	List<Fresrates> List(int startIndex, int numItems);
	List<Fresrates> ListAll();
	Boolean udpate(Fresrates fresrates);
	Boolean removeByID(FresratesPK fresratesPK);
}
