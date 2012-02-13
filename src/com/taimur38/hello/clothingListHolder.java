package com.taimur38.hello;

import java.util.ArrayList;

public class ClothingListHolder {

	public ArrayList<ClothingModel> list = new ArrayList<ClothingModel>();
	public int position = 0;
	
	public ClothingListHolder()
	{
		list = new ArrayList<ClothingModel>();
	}
		
	public void addToList(ClothingModel model)
	{
		if(list == null)
			list = new ArrayList<ClothingModel>();
		list.add(model);
	}
	
	public ClothingModel next()
	{
		if(++position >= list.size())
			position = 0;
		return list.get(position);
	}
	
	public ClothingModel current()
	{
		if(list == null)
			return null;
		return list.get(position);
	}
	
	public ClothingModel previous()
	{
		if(--position < 0)
			position = list.size()-1;
		return list.get(position);
	}
	
	public void clear()
	{
		if(list != null)
			list.clear();
		position = -1;
	}
}
