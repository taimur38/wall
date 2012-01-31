package com.taimur38.hello;

import java.security.MessageDigest;
import java.util.HashMap;

public class Session {
	
	static Me _Me = null;
	static HashMap<String, Clothing> Clothes = null;
	
	public Session()
	{
		
	}

	static Me getUser()
	{
		if(_Me == null)
			_Me = new Me("admin@clossit.com", "Engin33r");
		return _Me;
	}
	
	static HashMap<String, Clothing> addClothingItem(Clothing item){
		Clothes.put(item.ID(), item);
		return Clothes;
	}
	
	static Clothing getClothingItem(String id){
		return Clothes.get(id);
	}
	
	static String getMD5(String msg){
		try {
			return  MessageDigest.getInstance("MD5").toString();}
		catch(Exception e){return null;}
	}

}
