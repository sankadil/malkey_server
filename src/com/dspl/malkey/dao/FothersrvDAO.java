package com.dspl.malkey.dao;

import java.util.List;
import com.dspl.malkey.domain.Fothersrv;
import com.dspl.malkey.domain.Fothersrvrate;

public interface FothersrvDAO {
	int count();
	//void create(Fothersrv fothersrv);
	Fothersrv findByID(String srvid);
	List<Fothersrv> List(int startIndex, int numItems);
	List<Fothersrv> ListAll();
	//void udpate(Fothersrv fothersrv);
	//void removeByID(String srvid);
	
	List<Fothersrv> listServices();
	Boolean create(Fothersrv fothersrv,List<Fothersrvrate> fothersrvrates);
	Boolean update(Fothersrv fothersrv,List<Fothersrvrate> fothersrvrates);
	Boolean removeByID(String srvid);
}
