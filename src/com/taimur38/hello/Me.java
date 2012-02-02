package com.taimur38.hello;

import org.json.JSONObject;

public class Me extends ClossitUser{

	protected String apiKey;
	
	public Me(String email, String pwd) {
			apiKey = Session.getMD5(email + ":" + pwd);
			
			try { json = new JSONObject(PageDownloader.fakeAsync("http://clossit.com/api/User.aspx?q=basic&key=" + apiKey));}
			catch(Exception e){ json = null;}
		}
	
	public String getAPIKey(){
		return apiKey;
	}
		
	}
