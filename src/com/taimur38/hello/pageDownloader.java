package com.taimur38.hello;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class PageDownloader{
	private static String _url;
	private static String _result;
	
	public PageDownloader(String url){
		_url = url;
	}
	
	public static String sync(){
		StringBuilder sb = new StringBuilder();
		try{
			_url = _url.replace(" ", "%20");
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
	
	public static String fakeAsync(String url){
		_url = url;
		
		Thread t = new Thread(new DownloadTask());
		t.start();
		while(t.isAlive()){}
		
		return _result;
	}
	
	private static class DownloadTask implements Runnable{
		
		public void run(){
			_result = sync();
		}
	}
}