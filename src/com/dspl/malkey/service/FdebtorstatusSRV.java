package com.dspl.malkey.service;

import java.util.List;
import com.dspl.malkey.domain.Fdebtorstatus;

public interface FdebtorstatusSRV {
	int count();
	Boolean create(Fdebtorstatus fdebtorstatus);
	Fdebtorstatus findByID(String id);
	List<Fdebtorstatus> List(int startIndex, int numItems);
	List<Fdebtorstatus> ListAll();
	Boolean update(Fdebtorstatus fdebtorstatus);
	Boolean removeByID(String id);
}
