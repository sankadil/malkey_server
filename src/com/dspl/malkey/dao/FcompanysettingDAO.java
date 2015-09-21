package com.dspl.malkey.dao;

import java.util.List;
import com.dspl.malkey.domain.Fcompanysetting;

public interface FcompanysettingDAO {
	void create(Fcompanysetting fcompanysetting);
	void update(Fcompanysetting fcompanysetting);
	void removeById(String csettingscode);
	Fcompanysetting findById(String csettingscode);
	List<Fcompanysetting> listAll();
	List<Fcompanysetting> list(int startIndex, int numItems);
	int count();
	String getRefNo(String cSettingsCode, int year, int month);
}
