/**
 * 
 */
package com.dspl.malkey.service;

import java.util.List;

import javax.annotation.Resource;

import com.dspl.malkey.dao.FresvehiclerateDAO;
import com.dspl.malkey.domain.Fresvehiclerate;

/**
 * @author Sanka
 *
 */

public class FresvehiclerateSRVImpl implements FresvehiclerateSRV {

	@Resource(name="fresvehiclerateDAO")
	FresvehiclerateDAO fresvehiclerateDAO;
	
	/* (non-Javadoc)
	 * @see com.dspl.malkey.service.FresvehiclerateSRV#List(int, int)
	 */
	@Override
	public java.util.List<Fresvehiclerate> list(int startIndex, int numItems) {
		return fresvehiclerateDAO.list(startIndex, numItems);
	}

	/* (non-Javadoc)
	 * @see com.dspl.malkey.service.FresvehiclerateSRV#ListAll()
	 */
	@Override
	public java.util.List<Fresvehiclerate> listAll() {
		return fresvehiclerateDAO.listAll();
	}

	/* (non-Javadoc)
	 * @see com.dspl.malkey.service.FresvehiclerateSRV#count()
	 */
	@Override
	public int count() {
		return fresvehiclerateDAO.count();
	}

	/* (non-Javadoc)
	 * @see com.dspl.malkey.service.FresvehiclerateSRV#create(com.dspl.malkey.domain.Fresvehiclerate)
	 */
	@Override
	public void create(Fresvehiclerate fresvehiclerate) {
		fresvehiclerateDAO.create(fresvehiclerate);
	}

	/* (non-Javadoc)
	 * @see com.dspl.malkey.service.FresvehiclerateSRV#findByID(java.lang.String)
	 */
	@Override
	public Fresvehiclerate findByID(String resno) {
		return fresvehiclerateDAO.findByID(resno);
	}

	/* (non-Javadoc)
	 * @see com.dspl.malkey.service.FresvehiclerateSRV#removeByID(java.lang.String)
	 */
	@Override
	public void removeByID(String resno) {
		fresvehiclerateDAO.removeByID(resno);
	}

	/* (non-Javadoc)
	 * @see com.dspl.malkey.service.FresvehiclerateSRV#update(com.dspl.malkey.domain.Fresvehiclerate)
	 */
	@Override
	public void update(Fresvehiclerate faccrate) {
		fresvehiclerateDAO.update(faccrate);
	}

	@Override
	public List<Fresvehiclerate> listByResno(String resno) {
		return fresvehiclerateDAO.listByResno(resno);
	}

}
