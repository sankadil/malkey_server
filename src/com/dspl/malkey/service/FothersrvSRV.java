package com.dspl.malkey.service;

import java.util.List;
import com.dspl.malkey.domain.Fothersrv;
import com.dspl.malkey.domain.Fothersrvrate;

public interface FothersrvSRV {
	int count();
	//Boolean create(Fothersrv fothersrv);
	Fothersrv findByID(String srvid);
	List<Fothersrv> List(int startIndex, int numItems);
	List<Fothersrv> ListAll();
	//Boolean udpate(Fothersrv fothersrv);
	//Boolean removeByID(String srvid);
	
	List<Fothersrv> listServices();
	Boolean create(Fothersrv fothersrv,List<Fothersrvrate> fothersrvrates);
	Boolean update(Fothersrv fothersrv,List<Fothersrvrate> fothersrvrates);
	Boolean removeByID(String srvid);
}
