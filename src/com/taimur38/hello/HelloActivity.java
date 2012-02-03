package com.taimur38.hello;

import org.json.JSONArray;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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
    	ClothingListHolder.clear();
    	download();
    }
    
    public void download()
    {
        String url = super.getIntent().getStringExtra("url");
        String json = PageDownloader.fakeAsync(url);
        
        JSONArray clossit;
        try
        {   clossit = new JSONArray(json);}
        
        catch(Exception e)
        {	clossit = null; }
        
        for(int i = 0; i < clossit.length(); i++)
        {
        	try{
        		ClothingModel a = new ClothingModel(clossit.getJSONObject(i));
        		ClothingListHolder.addToList(a); //change to clothingmodel holder
        		if(i < 5) ImageDownloader.download(Session.getClothingItem(a.clothingID).Image(), null);} //cache first 5 images 
        	catch(Exception e){}
        }
        buildPage();
    }
    public void buildPage() //init with clothing model
    {
    	RelativeLayout layout = (RelativeLayout)this.findViewById(R.id.RelativeLayout1);
        TextView nameLabel = (TextView)this.findViewById(R.id.nameLabel);
        TextView descLabel = (TextView)this.findViewById(R.id.descLabel);
        descLabel.setMovementMethod(new ScrollingMovementMethod());
        
        ImageView test = new ImageView(this);
        test.setId(1337);
        test.setX(10);
        test.setY(10);
        FlingyOnClick flingy = new FlingyOnClick();
        
		layout.setOnClickListener(flingy);
		layout.setOnTouchListener(flingy.gestureListener);
		
		ClothingModel current = ClothingListHolder.next();
		ImageDownloader.download(current.getClothing().Image(), test);
		nameLabel.setText(current.getClothing().Name());
		descLabel.setText(current.getClothing().Description());
		layout.addView(test);
		
		Button addToClossitButton = new Button(this);
		addToClossitButton.setX(320);
		addToClossitButton.setY(425);
		if(current.isWearing())
			addToClossitButton.setText("Remove from Clossit");
		else
			addToClossitButton.setText("Add to Clossit");
		
		addToClossitButton.setId(555);
		addToClossitButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Button adder = ((Button)findViewById(555));
				
				boolean res = Session.addToClossit(ClothingListHolder.current().getClothing());
				if(res)
					adder.setText("Remove from Clossit");
				else
					adder.setText("Add To Clossit");}});
		
		
		layout.addView(addToClossitButton);

		//Buttons are temporary - don't judge me
		Button nextButton = new Button(this);
        nextButton.setX(310);
        nextButton.setText("next");
        nextButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				ClothingModel next = ClothingListHolder.next();
				ImageDownloader.download(next.getClothing().Image(), (ImageView)findViewById(1337));
				
				 TextView nameLabel = (TextView)findViewById(R.id.nameLabel);
			     TextView descLabel = (TextView)findViewById(R.id.descLabel);
			     
			     nameLabel.setText(next.getClothing().Name());
			     descLabel.setText(next.getClothing().Description());
			}
		});
        layout.addView(nextButton);
        
        Button prevButton = new Button(this);
        prevButton.setX(310);
        prevButton.setY(100);
        prevButton.setText("prev");
        prevButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				ClothingModel prev = ClothingListHolder.previous();
				ImageDownloader.download(prev.getClothing().Image(), (ImageView)findViewById(1337));
				
				 TextView nameLabel = (TextView)findViewById(R.id.nameLabel);
			     TextView descLabel = (TextView)findViewById(R.id.descLabel);
			     
			     nameLabel.setText(prev.getClothing().Name());
			     descLabel.setText(prev.getClothing().Description());
			}
		});
        layout.addView(prevButton);
    }
}