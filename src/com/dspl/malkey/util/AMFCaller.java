package com.dspl.malkey.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import flex.messaging.io.amf.client.AMFConnection;
import flex.messaging.io.amf.client.exceptions.ClientStatusException;
import flex.messaging.io.amf.client.exceptions.ServerStatusException;

public class AMFCaller {

	public static Collection getCollection(String url, String service) {

		List list = null;
/*		try {
			amfConnection.connect(url);
		} catch (ClientStatusException cse) {
			System.out.println(cse);			
		}*/
		try {
			AMFConnection amfConnection = new AMFConnection();
			amfConnection.connect(url);
			list =  (List) amfConnection.call(service);
			amfConnection.close();
		} catch (ClientStatusException cse) {
			System.out.println(cse);
		} catch (ServerStatusException sse) {
			System.out.println(sse);
		}		
		
		return list;
	}
	
	public static Collection getObject(String url, String service, String para) {

		List list = new ArrayList();
/*		try {
			amfConnection.connect(url);
		} catch (ClientStatusException cse) {
			System.out.println(cse);			
		}*/
		try {
			AMFConnection amfConnection = new AMFConnection();
			amfConnection.connect(url);
			Object obj =  (Object) amfConnection.call(service,para);
			list.add(obj);
			amfConnection.close();
		} catch (ClientStatusException cse) {
			System.out.println(cse);
		} catch (ServerStatusException sse) {
			System.out.println(sse);
		}		
		
		return list;
	}
	
	public static Collection getCollectionWPara(String url, String service, String para) {

		List list = null;
/*		try {
			amfConnection.connect(url);
		} catch (ClientStatusException cse) {
			System.out.println(cse);			
		}*/
		try {
			AMFConnection amfConnection = new AMFConnection();
			amfConnection.connect(url);
			list =  (List) amfConnection.call(service,para);
			amfConnection.close();
		} catch (ClientStatusException cse) {
			System.out.println(cse);
		} catch (ServerStatusException sse) {
			System.out.println(sse);
		}		
		
		return list;
	}
	
}
