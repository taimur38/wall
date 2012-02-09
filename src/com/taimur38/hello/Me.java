package com.taimur38.hello;

import org.json.JSONObject;

public class Me extends ClossitUser{

	protected String apiKey;
	
	public Me(String email, String pwd) {
			apiKey = Session.getMD5(email + ":" + pwd);
			String _root = "http://clossit.com/API/";
			try { json = new JSONObject(PageDownloader.fakeAsync(_root + "Basic/" + apiKey));}
			catch(Exception e){ json = null;}
		}
	
	public String getAPIKey(){
		return apiKey;
	}
		
	}
