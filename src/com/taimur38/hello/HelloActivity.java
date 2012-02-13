package com.taimur38.hello;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HelloActivity extends Activity {
    /** Called when the activity is first created. */
	final Context me = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);        
    }
    @Override
    protected void onResume()
    {
    	super.onResume();
    	buildPage();
    }
    
   
    public void buildPage() //init with clothing model
    {
    	RelativeLayout layout = (RelativeLayout)this.findViewById(R.id.RelativeLayout1);
        TextView nameLabel = (TextView)this.findViewById(R.id.nameLabel);
        TextView descLabel = (TextView)this.findViewById(R.id.descLabel);
        descLabel.setMovementMethod(new ScrollingMovementMethod());
        nameLabel.setMovementMethod(new ScrollingMovementMethod());
        
        ClothingListHolder list;

       	list = Session.listContainingID(super.getIntent().getStringExtra("ID"));
        
        list.position = (super.getIntent().getIntExtra("position", 0));
        
        ImageView test = (ImageView)findViewById(R.id.clothPic);
       
        FlingyOnClick flingy = new FlingyOnClick();
        
		layout.setOnClickListener(flingy);
		layout.setOnTouchListener(flingy.gestureListener);
		
		ClothingModel current = list.next();
		ImageDownloader.download(current.getClothing().Image(), test);
		nameLabel.setText(current.getClothing().Name());
		descLabel.setText(current.getClothing().Description());
			
		//start caching next image
		ClothingModel tmp = list.list.get(list.position++);
		if(tmp != null) ImageDownloader.download(tmp.getClothing().Image(), null);
		
		
		ImageButton addToClossitButton = new ImageButton(this);
		
		getResources().getConfiguration();
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
		{
			addToClossitButton.setX(600);
			addToClossitButton.setY(555);
		}
		else
		{
			addToClossitButton.setX(1000);
			addToClossitButton.setY(160);
		}
		
		addToClossitButton.setBackgroundColor(Color.TRANSPARENT);
		if(current.isWearing())
			addToClossitButton.setImageResource(R.drawable.unfollow);
		else
			addToClossitButton.setImageResource(R.drawable.follow);
		
		addToClossitButton.setId(555);
		addToClossitButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				ImageButton adder = ((ImageButton)findViewById(555));

				list.current().wearing = APICall.wear(list.position);
				if(list.current().wearing)
					adder.setImageResource(R.drawable.follow);
				else
					adder.setImageResource(R.drawable.unfollow);}});
		
		
		layout.addView(addToClossitButton);

		//Buttons are temporary - don't judge me
		Button nextButton = new Button(this);
        nextButton.setX(330);
        nextButton.setY(100);
        nextButton.setText("next");
        nextButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				ClothingModel next = ClothingListHolder.next();
				ImageDownloader.download(next.getClothing().Image(), (ImageView)findViewById(R.id.clothPic));
				
				 TextView nameLabel = (TextView)findViewById(R.id.nameLabel);
			     TextView descLabel = (TextView)findViewById(R.id.descLabel);
			     ImageButton adderButton = (ImageButton)findViewById(555);
			     
			     nameLabel.setText(next.getClothing().Name());
			     descLabel.setText(next.getClothing().Description());
			     if(next.isWearing())
			    	 adderButton.setImageResource(R.drawable.unfollow);
			     else
			    	 adderButton.setImageResource(R.drawable.follow);
			}
		});
        layout.addView(nextButton);
        
        Button prevButton = new Button(this);
        prevButton.setX(330);
        prevButton.setY(195);
        prevButton.setText("prev");
        prevButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				ClothingModel prev = ClothingListHolder.previous();
				ImageDownloader.download(prev.getClothing().Image(), (ImageView)findViewById(R.id.clothPic));
				
				 TextView nameLabel = (TextView)findViewById(R.id.nameLabel);
			     TextView descLabel = (TextView)findViewById(R.id.descLabel);
			     ImageButton adderButton = (ImageButton)findViewById(555);
			     
			     nameLabel.setText(prev.getClothing().Name());
			     descLabel.setText(prev.getClothing().Description());
			     if(prev.isWearing())
			    	 adderButton.setImageResource(R.drawable.unfollow);
			     else
			    	 adderButton.setImageResource(R.drawable.follow);
			}
		});
        layout.addView(prevButton);
    }
}