package com.taimur38.hello;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;

public class pageDownloader extends Thread {

	String url;
	StringBuilder res;
	boolean done;
	public pageDownloader(String nurl, StringBuilder val)
	{
		url = nurl;
		res = val;
		done = false;
	}
	
	public void run() {
		try{
			URL address = new URL(url);
			URLConnection conn = address.openConnection();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String s = "";
			
			while((s = br.readLine()) != null)
			 res.append(s);
			done=true;}
		
		catch(Exception e){
				String error = e.toString();
				res = res.append("penis");}
	}
}
