package com.taimur38.hello;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HelloActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        String url = "http://clossit.com/api/User.aspx?id=1&q=Clossit&results=24&page=0";
        pageDownloader wc = new pageDownloader(url);
        String json = wc.fakeAsync();
        
        JSONArray clossit;
        try{
        	clossit = new JSONArray(json);}
        
        catch(Exception e){
        	clossit = null; }
        
        
        
        for(int i = 0; i < clossit.length(); i++)
        {
        	try{
        		Clothing a = new Clothing(clossit.getJSONObject(i));
        		clothingListHolder.addToList(a.ID());
        		
        		
        	}catch(Exception e){
        		String el = e.toString();
        		String lala="";
        	}
        }
        
        buildPage();
        
    }
    
    public void buildPage()
    {
    	RelativeLayout layout = (RelativeLayout)this.findViewById(R.id.RelativeLayout1);
        TextView nameLabel = (TextView)this.findViewById(R.id.nameLabel);
        TextView descLabel = (TextView)this.findViewById(R.id.descLabel);
        descLabel.setMovementMethod(new ScrollingMovementMethod());
        
        ImageDownloader downloader = new ImageDownloader();
        
        ImageView test = new ImageView(this);
        test.setId(1337);
        flingyOnClick flingy = new flingyOnClick();
        
		layout.setOnClickListener(flingy);
		layout.setOnTouchListener(flingy.gestureListener);
		
		Clothing current = clothingListHolder.nextCloth();
		downloader.download(current.Image(), test);
		nameLabel.setText(current.Name());
		descLabel.setText(current.Description());
		layout.addView(test);
		
		//Buttons are temporary - don't judge me
		Button nextButton = new Button(this);
        nextButton.setX(310);
        nextButton.setText("next");
        nextButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Clothing next = clothingListHolder.nextCloth();
				ImageDownloader downloader = new ImageDownloader();
				downloader.download(next.Image(), (ImageView)findViewById(1337));
				
				 TextView nameLabel = (TextView)findViewById(R.id.nameLabel);
			     TextView descLabel = (TextView)findViewById(R.id.descLabel);
			     
			     nameLabel.setText(next.Name());
			     descLabel.setText(next.Description());
			}
		});
        layout.addView(nextButton);
        
        Button prevButton = new Button(this);
        prevButton.setX(310);
        prevButton.setY(100);
        prevButton.setText("prev");
        prevButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Clothing prev = clothingListHolder.prevCloth();
				ImageDownloader downloader = new ImageDownloader();
				downloader.download(prev.Image(), (ImageView)findViewById(1337));
				
				 TextView nameLabel = (TextView)findViewById(R.id.nameLabel);
			     TextView descLabel = (TextView)findViewById(R.id.descLabel);
			     
			     nameLabel.setText(prev.Name());
			     descLabel.setText(prev.Description());
			}
		});
        layout.addView(prevButton);
		
		
    }
    
}