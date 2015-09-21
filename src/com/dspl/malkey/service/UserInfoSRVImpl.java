package com.dspl.malkey.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpSession;

import com.dspl.malkey.domain.Fpass;

import flex.messaging.FlexContext;

public class UserInfoSRVImpl implements UserInfoSRV {


	/***
	 * return machine name eg:SANDARUWAN
	 * 
	 * 
	 */
	@Override
	public String getHostName() {
		String ipAddress="Unknown";
		try {
		InetAddress inetAddress = InetAddress.getByName(FlexContext.getHttpRequest().getRemoteHost());
		ipAddress = inetAddress.getHostName();
		//single line code	InetAddress.getByName(FlexContext.getHttpRequest().getRemoteHost()).getHostName();
		//InetAddress.getByName(FlexContext.getHttpRequest().getRemoteHost()).getHostName()
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ipAddress;
	}
	

	/***
	 * return IP address 192.168.0.115
	 * 
	 * 
	 */
	@Override
	public String getRemoteHost() {
		return FlexContext.getHttpRequest().getRemoteHost();
	}
	
	/***
	 * return Name-IP address eg:SANDARUWAN-192.168.0.115
	 * but in localhost it lookslike 127.0.0.1-127.0.0.1
	 * 
	 */
	@Override
	public String getMachineName() {
		String name="";
		try {
			name = InetAddress.getByName(FlexContext.getHttpRequest().getRemoteHost()).getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		String ip=FlexContext.getHttpRequest().getRemoteHost();
		return (name+"-"+ip);
	}
	
	/***
	 * return fPass.getUsername() of current user
	 * Fpass.Username 
	 * 
	 * 
	 */
	@Override
	public String getUser() {
		try{
		HttpSession session=FlexContext.getHttpRequest().getSession(true);
		Fpass fPass=(Fpass)session.getAttribute("user");
		return fPass.getUsername();
		}
		catch(Exception e)
		{
			throw new RuntimeException("Sorry,\nSession Timeout");
		}
	}
}
