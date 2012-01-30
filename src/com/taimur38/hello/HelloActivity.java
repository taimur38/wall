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
        ImageView test = new ImageView(this);
        ImageDownloader downloader = new ImageDownloader();
        StringBuilder bob = new StringBuilder("");
        String url = "http://clossit.com/api/User.aspx?id=1&q=Clossit&results=3";
        pageDownloader wc = new pageDownloader(url, bob);
        wc.start();
        
        
        //wait to dl the page
       
        while(!wc.done){try{Thread.sleep(1000);} catch(Exception e){}}
        String json = bob.toString();
        
        JSONArray clossit;
        try{
        	clossit = new JSONArray(json);}
        catch(Exception e){
        	clossit = null; }
        	
        for(int i = 0; i < clossit.length(); i++)
        {
        	try{
        	Clothing a = new Clothing(clossit.getString(i));
        	String tdest = a.Image();
        	downloader.download(a.Image(), test);
        	layout.addView(test);}
        	
        	catch(Exception e){}
        }
        
    }
    
}