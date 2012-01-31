package com.taimur38.hello;

import org.json.JSONObject;

public class ClossitUser {
	
	protected JSONObject json;
	
	public ClossitUser(){
		json = null;
	}
	
	public ClossitUser(JSONObject newJson){
		json = newJson;
	}
	
	public ClossitUser(String newJson){
		try{
			json = new JSONObject(newJson);
		} catch(Exception e){json = null;}
	}
	
	public String Name(){
		try{
			return json.getString("name");
		}
		catch(Exception e){
			return "no";
		}
	}
	
	public String Id(){
		try{
			return json.getString("id");}
		
		catch(Exception e){ return "-1";}	
	}
	
	public String Image(){
		try{
			return json.getString("avatar");}
		
		catch(Exception e){ return null; }
	}

}
