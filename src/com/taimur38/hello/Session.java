package com.taimur38.hello;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;


public class Session {
	
	static Me _Me = null;
	static HashMap<String, Clothing> Clothes = null;
	static HashMap<String, ClossitUser> Users = null;
	
	static ClothingListHolder clossit = new ClothingListHolder();
	static ClothingListHolder suggestions = new ClothingListHolder();
	
	static Me getUser()
	{
		if(_Me == null)
			_Me = new Me("admin@clossit.com", "Engin33r");
		return _Me;
	}
	
	static ClothingListHolder listContainingID(String id)
	{
		for(int i = 0; i < clossit.list.size(); i++)
			if(clossit.list.get(i).Equals(id))
				return clossit;
		
		for(int i = 0; i < suggestions.list.size(); i++)
			if(suggestions.list.get(i).Equals(id))
				return suggestions;
		return null;
	}
	
	static void addClothingItem(Clothing item){ 
		if(Clothes == null)
			Clothes = new HashMap<String, Clothing>();
		Clothes.put(item.ID(), item);
	}
	
	static Clothing getClothingItem(String id){
		return Clothes.get(id);
	}
	
	static void addClossitUser(ClossitUser user){
		if(Users == null)
			Users = new HashMap<String, ClossitUser>();
		Users.put(user.Id(), user);
	}
	
	static ClothingListHolder getList(String type) //Ugly as balls
	{
		if(type == "clossit")
			return clossit;
		if(type == "suggestions")
			return suggestions;
		return null;
	}
	
	static ClossitUser getClossitUser(String id){
		if(Users == null)
			Users = new HashMap<String, ClossitUser>();
		return Users.get(id);
	}
	
	static String getMD5(String msg){
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(msg.getBytes(),0,msg.length());
			return new BigInteger(1,m.digest()).toString(16);}
		catch(Exception e){return null;}
	}

}
