package com.taimur38.hello;

import org.json.JSONObject;

public class ClossitUser {
	
	protected JSONObject json;
	
	public ClossitUser(){
		json = null;
	}
	
	public ClossitUser(JSONObject newJson){
		json = newJson;
		Session.addClossitUser(this);
	}
	
	public ClossitUser(String newJson){
		try{
			json = new JSONObject(newJson);
			Session.addClossitUser(this);
		} catch(Exception e){json = null;}
	}
	
	public String Name(){
		return json.optString("name", "Happysmileyface");
	}
	
	public String Id(){
		return json.optString("id", "-1");
	}
	
	public String Image(){
		return json.optString("images", "lala");
	}

}
