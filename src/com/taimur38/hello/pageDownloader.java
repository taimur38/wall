package com.taimur38.hello;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class pageDownloader{
	private String _url;
	private String _result;
	
	public pageDownloader(String url){
		_url = url;
	}
	
	public String sync(){
		StringBuilder sb = new StringBuilder();
		try{
			URL address = new URL(_url);
			URLConnection conn = address.openConnection();
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String s = "";
			while((s=br.readLine()) != null)
				sb.append(s);
		} catch(Exception e){}
		
		return sb.toString();
		}
	
	public String fakeAsync(){
		Thread t = new Thread(new DownloadTask());
		t.start();
		while(t.isAlive()){}
		
		return _result;
	}
	
	private class DownloadTask implements Runnable{
		
		public void run(){
			_result = sync();
		}
	}
}