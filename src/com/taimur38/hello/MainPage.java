package com.taimur38.hello;

import org.json.JSONArray;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class MainPage extends Activity {
	
	ViewPager viewpage;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.maingrid);
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		TextView label = (TextView)findViewById(R.id.usernameLabel);
		label.setText(Session.getUser().Name());
		ClothingListHolder.clear();
		download();
	}
	
	public void download()
	{
        JSONArray clossit = APICall.getClossit(0, 24);
        
        for(int i = 0; i < clossit.length(); i++)
        {
        	try{
        		ClothingModel a = new ClothingModel(clossit.getJSONObject(i));
        		ClothingListHolder.addToList(a); //change to clothingmodel holder
        		if(i == 1) ImageDownloader.download(Session.getClothingItem(a.clothingID).Image(), null);} //cache first image
        	catch(Exception e){}
        }
       initList();
	}
	
	public void initList()
	{
		ListView lv = (ListView)findViewById(R.id.listView);
		lv.setAdapter(new ClothingModelAdapter(this, R.id.listviewTitle, ClothingListHolder.list));
	}

}
