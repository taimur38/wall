package com.taimur38.hello;

import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class flingyOnClick extends Activity implements OnClickListener{
	
	private GestureDetector gestureDetector;
	View.OnTouchListener gestureListener;
	private static final int SWIPE_MIN = 120;
	private static final int MIN_VELOCITY = 200;
	
	public void onClick(View v) {
		
		
	}
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		gestureDetector = new GestureDetector(new MyGestureDetector());
		gestureListener = new View.OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) { 
				return gestureDetector.onTouchEvent(event);
			}
		};
	}


	class MyGestureDetector extends SimpleOnGestureListener {
	
		Stack<View> offScreen = new Stack<View>();
	
		@Override
		public boolean onFling(MotionEvent e1,MotionEvent e2, float velocityX, float velocityY){
			if(e1.getX() - e2.getX() > SWIPE_MIN && Math.abs(velocityX) > MIN_VELOCITY)
			{
				Toast.makeText(flingyOnClick.this, "left-swipe", Toast.LENGTH_SHORT).show();
				//if(offScreen != null)
					//flingOnStack(offScreen.pop()); 
			}
			
			if(e2.getX() - e1.getX() > SWIPE_MIN && Math.abs(velocityX) > MIN_VELOCITY)
			{
				Toast.makeText(flingyOnClick.this, "right-swipe", Toast.LENGTH_SHORT).show();
				//flingOffStack(offScreen.push(getCurrentFocus())); //TODO: does this get the right view?
			}
			
			return false;
		}
	}

}