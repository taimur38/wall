package com.taimur38.hello;

import java.security.MessageDigest;
import java.util.HashMap;

import android.graphics.Bitmap;

public class Session {
	
	static Me _Me = null;
	static HashMap<String, Clothing> Clothes = null;
	static HashMap<String, Bitmap> Images = null;
	
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
			return  MessageDigest.getInstance("MD5").toString();}
		catch(Exception e){return null;}
	}
	
	static void cacheImage(String url, Bitmap bmp)
	{
		if(Images == null)
			Images = new HashMap<String, Bitmap>();
		Images.put(url, bmp);
	}
	
	static Bitmap getImage(String url)
	{
		if(Images == null)
			Images = new HashMap<String, Bitmap>();
		return Images.get(url);
	}

}
