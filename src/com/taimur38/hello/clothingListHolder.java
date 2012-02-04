package com.taimur38.hello;

import java.util.ArrayList;

public class ClothingListHolder {

	public static ArrayList<ClothingModel> list = null;
	private static int position = -1;
	
	public ClothingListHolder()
	{
		list = new ArrayList<ClothingModel>();
	}
	
	public static void addToList(ClothingModel model)
	{
		if(list == null)
			list = new ArrayList<ClothingModel>();
		list.add(model);
	}
	
	public static int getPosition()
	{
		return position;
	}
	
	public static ClothingModel index(int i)
	{
		return list.get(i);
	}
	
	public static ClothingModel next()
	{
		if(++position >= list.size())
			position = 0;
		return list.get(position);
	}
	
	public static ClothingModel current()
	{
		if(list == null)
			return null;
		return list.get(position);
	}
	
	public static ClothingModel previous()
	{
		if(--position < 0)
			position = list.size()-1;
		return list.get(position);
	}
	
	public static void clear()
	{
		if(list != null)
			list.clear();
		position = -1;
	}
}
