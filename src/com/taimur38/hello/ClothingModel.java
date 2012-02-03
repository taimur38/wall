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
		ClossitUser user = new ClossitUser(json.optJSONObject("owner"));
		userID = user.Id();
		wearing = json.optBoolean("wearing");
	}

}
