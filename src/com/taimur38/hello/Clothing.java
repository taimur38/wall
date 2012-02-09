package com.taimur38.hello;

import org.json.JSONObject;

public class Clothing {
	private JSONObject json;
	
	public Clothing(JSONObject newJson)
	{
		try{
			json = newJson;
			Session.addClothingItem(this);}
		catch(Exception e){
			json = null;}
	}
	
	public Clothing(String newJson){
		try{
			json = new JSONObject(newJson);
			Session.addClothingItem(this);}
		
		catch(Exception e){ json = null; }
	}
	public String Name()
	{
		return json.optString("name");
	}
	
	public String Store()
	{
		return json.optString("store");		
	}
	
	public String ID()
	{
		return json.optString("id");
	}
	
	public String Image()
	{
		return "http://clossit.com/Global/Thumbnail/?w=300&h=500&src=" + json.optString("image");
	}
	
	public String Description()
	{
		return json.optString("description");
	}
}
