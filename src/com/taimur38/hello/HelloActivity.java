package com.taimur38.hello;

import org.json.JSONArray;

import android.app.Activity;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        String url = "http://clossit.com/api/User.aspx?id=1&q=Clossit&results=24&page=0";
        String json = PageDownloader.fakeAsync(url);
        
        JSONArray clossit;
        try
        {   clossit = new JSONArray(json);}
        
        catch(Exception e)
        {	clossit = null; }
        
        for(int i = 0; i < clossit.length(); i++)
        {
        	try{
        		Clothing a = new Clothing(clossit.getJSONObject(i));
        		ClothingListHolder.addToList(a.ID());}
        	catch(Exception e){}
        }
        buildPage();
    }
    
    public void buildPage()
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
		
		Clothing current = ClothingListHolder.nextCloth();
		ImageDownloader.download(current.Image(), test);
		nameLabel.setText(current.Name());
		descLabel.setText(current.Description());
		layout.addView(test);
		
		Button addToClossitButton = new Button(this);
		addToClossitButton.setX(320);
		addToClossitButton.setY(425);
		addToClossitButton.setText("Add To Clossit");
		addToClossitButton.setId(555);
		addToClossitButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				String url = "http://www.clossit.com/api/User.aspx?q=wear&clothing=" 
						+ ClothingListHolder.currentCloth().ID() + "&key=" + Session.getUser().getAPIKey();
				String result = PageDownloader.fakeAsync(url);
				Button adder = ((Button)findViewById(555));
				if(result.toLowerCase().contains("true"))
					adder.setText("Remove from Clossit");
				else
					adder.setText("Add To Clossit");
			}
		});
		layout.addView(addToClossitButton);

		//Buttons are temporary - don't judge me
		Button nextButton = new Button(this);
        nextButton.setX(310);
        nextButton.setText("next");
        nextButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Clothing next = ClothingListHolder.nextCloth();
				ImageDownloader.download(next.Image(), (ImageView)findViewById(1337));
				
				 TextView nameLabel = (TextView)findViewById(R.id.nameLabel);
			     TextView descLabel = (TextView)findViewById(R.id.descLabel);
			     
			     nameLabel.setText(next.Name());
			     descLabel.setText(next.Description());
			}
		});
        layout.addView(nextButton);
        
        Button prevButton = new Button(this);
        prevButton.setX(310);
        prevButton.setY(100);
        prevButton.setText("prev");
        prevButton.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Clothing prev = ClothingListHolder.prevCloth();
				ImageDownloader.download(prev.Image(), (ImageView)findViewById(1337));
				
				 TextView nameLabel = (TextView)findViewById(R.id.nameLabel);
			     TextView descLabel = (TextView)findViewById(R.id.descLabel);
			     
			     nameLabel.setText(prev.Name());
			     descLabel.setText(prev.Description());
			}
		});
        layout.addView(prevButton);
    }
}