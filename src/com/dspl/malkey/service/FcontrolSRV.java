package com.dspl.malkey.service;

import com.dspl.malkey.domain.Fcontrol;

public interface FcontrolSRV {
	Fcontrol ListAll();
	Boolean update(Fcontrol fcontrol);
}
