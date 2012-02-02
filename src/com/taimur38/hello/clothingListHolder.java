package com.taimur38.hello;

import java.util.ArrayList;

public class clothingListHolder {

	public static ArrayList<String> list = null;
	private static int position = -1;
	
	public clothingListHolder()
	{
		list = new ArrayList<String>();
	}
	
	public static void addToList(String id)
	{
		if(list == null)
			list = new ArrayList<String>();
		list.add(id);
	}
	
	public static Clothing nextCloth()
	{
		if(++position >= list.size())
			position = 0;
		return Session.getClothingItem(list.get(position));
	}
	
	public static Clothing prevCloth()
	{
		if(--position < 0)
			position = list.size()-1;
		return Session.getClothingItem(list.get(position));
	}
}
