package com.taimur38.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class MainPage extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.maingrid);
		
				
		ImageButton seeClossit = (ImageButton)findViewById(R.id.imageButton1);
		seeClossit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent passer = new Intent(getApplicationContext(), com.taimur38.hello.HelloActivity.class);
				passer.putExtra("url", "http://clossit.com/api/User.aspx?id=1&q=Clossit&results=24&page=0");
						
				startActivity(passer);}});
		
		ImageButton suggestion = (ImageButton)findViewById(R.id.imageButton2);
		suggestion.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent passer = new Intent(getApplicationContext(), com.taimur38.hello.HelloActivity.class);
				String suggestionUrl = "http://clossit.com/api/User.aspx?id=1&q=Suggestions&results=24&page=0&key=" + Session.getUser().getAPIKey();
				passer.putExtra("url", suggestionUrl);
				
				startActivity(passer);}});
	}

}
