package com.taimur38.hello;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClothingModelAdapter extends ArrayAdapter<ClothingModel> {

	LayoutInflater mInflate;
	List<ClothingModel> list;
	
	public ClothingModelAdapter(Context context, int textViewResourceId, List<ClothingModel> objects) 
	{
		super(context, textViewResourceId, objects);
		mInflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		list = objects;
	}
	
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ClothingModel a = list.get(position);
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
		
		holder.title.setText(Session.getClothingItem(list.get(position).clothingID).Name());
		holder.pic.setImageResource(R.drawable.dress);
		holder.position=position;
		ImageDownloader.download(Session.getClothingItem(list.get(position).clothingID).Image(), holder.pic);
		
		convertView.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), com.taimur38.hello.HelloActivity.class);
				ListViewHolder hold = (ListViewHolder)v.getTag();
				intent.putExtra("position", hold.position-1);
				v.getContext().startActivity(intent);
				
			}
		});
		
		return convertView;
	}
	
	
	static class ListViewHolder 
	{
		TextView title;
		ImageView pic;
		int position;
	}
}


