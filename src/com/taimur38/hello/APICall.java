package com.taimur38.hello;

import org.json.JSONArray;
import org.json.JSONObject;

public class APICall 
{
	static JSONObject getBasic(String userID)
	{
		String json = PageDownloader.fakeAsync(Query.basic(userID));
		
		JSONObject basic;
		try
        {   basic = new JSONObject(json);}
        
        catch(Exception e)
        {	basic = null; }
		
		return basic;
	}
	
	static JSONArray getClossit(int page, int results)
	{
		String json = PageDownloader.fakeAsync(Query.clossit(page, results));
		
		JSONArray clossit;
		
		try
		{	clossit = new JSONArray(json); }
		catch(Exception e)
		{	clossit = null;	}
		
		return clossit;
	}
	
	static JSONArray getsuggestions(int page, int results)
	{
		String json = PageDownloader.fakeAsync(Query.suggestions(page, results));
		
		JSONArray suggest;
		try
		{	suggest = new JSONArray(json); }
		catch(Exception e)
		{	suggest = null;	}
		
		return suggest;
		
	}
	
	static JSONArray publicClossit(int id, int page, int results)
	{
		String json = PageDownloader.fakeAsync(Query.publicClossit(id, page, results));
		
		JSONArray res;
		
		try
		{	res = new JSONArray(json); }
		catch(Exception e)
		{	res = null;	}
		
		return res;
	}
	
	static JSONArray getFollowers(int page, int results)
	{
		String json = PageDownloader.fakeAsync(Query.followers(page, results));
		
		JSONArray res;
		
		try
		{	res = new JSONArray(json); }
		catch(Exception e)
		{	res = null;	}
		
		return res;
	}
	
	static JSONArray getFollowing(int page, int results)
	{
		String json = PageDownloader.fakeAsync(Query.following(page, results));
		
		JSONArray res;
		
		try
		{	res = new JSONArray(json); }
		catch(Exception e)
		{	res = null;	}
		
		return res;
	}
	
	static boolean wear(String id)
	{
		String json = PageDownloader.fakeAsync(Query.wear(id));
		
		return json.contains("true");
	}
}