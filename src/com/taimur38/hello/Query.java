package com.taimur38.hello;

public class Query 
{
	static String _root = "http://clossit.com/API/";
	
	static String basic(String userId)
	{
		return _root + "Basic/" + Session.getUser().apiKey;
	}
	
	static String suggestions(int page, int results)
	{
		return _root + "Suggestions/" + Session.getUser().apiKey + "/" + page + "/" + results;
	}
	
	static String clossit(int page, int results)
	{
		return _root + "Clossit/" + Session.getUser().apiKey + "/" + page +"/" + results;	
	}
	
	static String publicClossit(int id, int page, int results)
	{
		return _root + "PublicClossit/" + Session.getUser().apiKey + "/" + id + "/" + page + "/" + results;
	}
	
	static String stream(int page, int results)
	{
		return _root + "Stream/" + Session.getUser().apiKey + "/" + page + "/" + results;
	}
	
	static String followers(int page, int results)
	{
		return _root + "Followers/" + Session.getUser().apiKey + "/" + page + "/" + results;
	}
	
	static String following(int page, int results)
	{
		return _root + "Following/" + Session.getUser().apiKey + "/" + page + "/" + results;
	}
	
	static String wear(int id)
	{
		return _root + "Wear/" + Session.getUser().apiKey + "/" + id;
	}
	
}
