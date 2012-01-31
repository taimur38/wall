package com.taimur38.hello;

import org.json.JSONObject;

public class Me extends ClossitUser{

	protected String apiKey;
	
	public Me(String email, String pwd) {
			apiKey = Session.getMD5(email + ":" + pwd);
			
			pageDownloader dl = new pageDownloader("http://clossit.com/api/User.aspx?q=basic&key=" + apiKey);
			
			try { json = new JSONObject(dl.fakeAsync());}
			catch(Exception e){ json = null;}
		}
	
	public String getAPIKey(){
		return apiKey;
	}
		
	}
