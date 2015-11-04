package com.dspl.malkey.service;

import javax.servlet.http.HttpSession;

public interface UserInfoSRV {
	public String getHostName();
	public String getRemoteHost();
	public String getUser();
	public String getMachineName();
	String getUserHttpSession(HttpSession session);
}
