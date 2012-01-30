package com.taimur38.hello;

import org.json.JSONObject;

public class Clothing {
	private JSONObject json;
	
	public Clothing(String newJson)
	{
		try{
			json = new JSONObject(newJson);}
		catch(Exception e){
			json = null;}
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
	
	public String Image()
	{
		try{
			return json.getString("image");}
		catch(Exception e){
			String test = e.toString();
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
