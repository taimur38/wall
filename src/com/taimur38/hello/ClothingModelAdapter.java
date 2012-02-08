package com.taimur38.hello;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClothingModelAdapter extends ArrayAdapter<ClothingModel> {

	LayoutInflater mInflate;
	public ClothingModelAdapter(Context context, int textViewResourceId, List<ClothingModel> objects) 
	{
		super(context, textViewResourceId, objects);
		mInflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ClothingModel a = ClothingListHolder.index(position);
		ListViewHolder holder;
		if(convertView == null)
		{
			convertView = mInflate.inflate(R.layout.list_item, parent, false);
			holder = new ListViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.listviewTitle);
			holder.title.setTextColor(Color.BLACK);
			holder.pic = (ImageView) convertView.findViewById(R.id.listviewPic);
			
			convertView.setTag(holder);
		}
		else
			holder = (ListViewHolder) convertView.getTag();
		
		holder.title.setText(a.getClothing().Name());
		holder.pic.setImageResource(R.drawable.dress);
		ImageDownloader.download(a.getClothing().Image(), holder.pic);
		
		return convertView;
	}
	
	
	static class ListViewHolder 
	{
		TextView title;
		ImageView pic;
	}
}


