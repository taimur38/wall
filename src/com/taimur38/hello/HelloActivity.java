package com.taimur38.hello;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
        
        RelativeLayout layout = (RelativeLayout)this.findViewById(R.id.RelativeLayout1);
        TextView nameLabel = (TextView)this.findViewById(R.id.nameLabel);
        TextView descLabel = (TextView)this.findViewById(R.id.descLabel);
        descLabel.setMovementMethod(new ScrollingMovementMethod());
        
        ImageDownloader downloader = new ImageDownloader();
        String url = "http://clossit.com/api/User.aspx?id=1&q=Clossit&results=2&page=0";
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
        		ImageView test = new ImageView(this);
        		test.setX(i*150);
        			
        		Clothing a = new Clothing(clossit.getJSONObject(i));
        		downloader.download(a.Image(), test);
        		nameLabel.setText(a.Name());
        		descLabel.setText(a.Description());
        		layout.addView(test);}
        	catch(Exception e){
        		String el = e.toString();
        		String lala="";
        	}
        }
        
    }
    
}