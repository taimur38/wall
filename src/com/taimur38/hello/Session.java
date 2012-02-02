package com.taimur38.hello;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;


public class Session {
	
	static Me _Me = null;
	static HashMap<String, Clothing> Clothes = null;
	
	static Me getUser()
	{
		if(_Me == null)
			_Me = new Me("admin@clossit.com", "Engin33r");
		return _Me;
	}
	
	static HashMap<String, Clothing> addClothingItem(Clothing item){ 
		if(Clothes == null)
			Clothes = new HashMap<String, Clothing>();
		Clothes.put(item.ID(), item);
		return Clothes;
	}
	
	static Clothing getClothingItem(String id){
		return Clothes.get(id);
	}
	
	static String getMD5(String msg){
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(msg.getBytes(),0,msg.length());
			return new BigInteger(1,m.digest()).toString(16);}
		catch(Exception e){return null;}
	}

}
