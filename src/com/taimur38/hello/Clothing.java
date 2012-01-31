package com.taimur38.hello;

import org.json.JSONObject;

public class Clothing {
	private JSONObject json;
	
	public Clothing(JSONObject newJson)
	{
		try{
			json = newJson.getJSONObject("clothing");
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
		try{
			return json.getString("name");}
		catch(Exception e){
			return null;
		}
	}
	
	public String Store()
	{
		try{
			return json.getString("store");}
		catch(Exception e){
			return null;}
	}
	
	public String ID()
	{
		try{
			return json.getString("id");}
		catch(Exception e){return "-1";}
	}
	
	public String Image()
	{
		try{
			return json.getString("image");}
		catch(Exception e){
			String test = e.toString(); //to read message when debugging
			return "http://upload.wikimedia.org/wikipedia/commons/e/ec/Happy_smiley_face.png";}
	}
	
	public String Description()
	{
		try{
			return json.getString("description");}
		catch(Exception e){
			return "I'm so happy!";}
	}
}
