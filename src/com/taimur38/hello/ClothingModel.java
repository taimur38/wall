package com.taimur38.hello;

import org.json.JSONObject;

public class ClothingModel {
	public String clothingID;
	public String userID;
	public boolean wearing;
	
	public ClothingModel(JSONObject json)
	{
		Clothing clothing = new Clothing(json.optJSONObject("clothing"));
		clothingID = clothing.ID();
		ClossitUser user = json.optJSONObject("owner") != null ? new ClossitUser(json.optJSONObject("owner")) : null;
		userID = user == null ? "1" : user.Id(); //for now,  default is owner = Clossit team
		wearing = json.optString("wearing").contains("true");
	}
	
	public Clothing getClothing()
	{
		return Session.getClothingItem(clothingID);
	}
	
	public ClossitUser getOwner()
	{
		return Session.getClossitUser(userID);
	}
	
	public boolean isWearing()
	{
		return wearing;
	}
	
	public String toString()
	{
		return getClothing().Name();
	}
	
	public boolean Equals(String id)
	{
		return this.clothingID.equals(id);
	}

}
