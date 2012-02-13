package com.taimur38.hello;

import java.util.ArrayList;

import org.json.JSONArray;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;


public class MainPage extends Activity {
	
	ViewPager viewpage;
	Adapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.maingrid);
		
		TextView label = (TextView)findViewById(R.id.usernameLabel);
		label.setText(Session.getUser().Name());
		
		adapter = new Adapter(getFragmentManager());
		
		viewpage = (ViewPager)findViewById(R.id.viewpager);
		viewpage.setAdapter(adapter);
	}	
	
	@Override
	protected void onResume()
	{
		super.onResume();		
		Session.clossit.clear();
		Session.suggestions.clear();
		download();
	}
	
	public void download()
	{
        JSONArray clossit = APICall.getClossit(0, 24);
        for(int i = 0; i < clossit.length(); i++)
        {
        	try{
        		ClothingModel a = new ClothingModel(clossit.getJSONObject(i));
        		Session.clossit.addToList(a);
        		if(i == 1) ImageDownloader.download(a.getClothing().Image(), null);} //cache first image
        	catch(Exception e){}
        }
        
        JSONArray suggest = APICall.getSuggestions(0, 24);
        for(int i = 0; i < suggest.length(); i++)
        {
        	try{
        		ClothingModel a = new ClothingModel(suggest.getJSONObject(i));
        		Session.suggestions.addToList(a);
        		if(i == 1) ImageDownloader.download(a.getClothing().Image(), null);} //cache first image
        	catch(Exception e){}
        }
	}
	
	public static class Adapter extends FragmentPagerAdapter
	{
		public Adapter(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return ArrayListFragment.newInstance(position);
		}

		@Override
		public int getCount() {
			return 2;
		}
		
		public CharSequence getPageTitle(int position)
		{
			if(position == 0)
				return "Clossit";
			else
				return "Suggestions";
		}
	}
	
	public static class ArrayListFragment extends Fragment
	{
		int _num;
		
		static ArrayListFragment newInstance(int num)
		{
			ArrayListFragment f = new ArrayListFragment();
			Bundle args = new Bundle();
			args.putInt("num", num);
			f.setArguments(args);
			
			return f;
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState)
		{
			super.onCreate(savedInstanceState);
			_num = getArguments() != null ? getArguments().getInt("num") : 1;
		}

		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View v = inflater.inflate(R.layout.contentlistview, container, false);
			if(_num == 0)
				((ListView)v).setAdapter(new ClothingModelAdapter(getActivity(), R.id.listviewTitle, Session.clossit.list));
			if(_num == 1)
				((ListView)v).setAdapter(new ClothingModelAdapter(getActivity(), R.id.listviewTitle, Session.suggestions.list));

			return v;
		}
		
	}

}
