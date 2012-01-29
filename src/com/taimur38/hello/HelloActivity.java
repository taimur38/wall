package com.taimur38.hello;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HelloActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        RelativeLayout layout = (RelativeLayout)this.findViewById(R.id.RelativeLayout1);
        //ImageView test = new ImageView(this);
        ImageView test = (ImageView)this.findViewById(R.id.imageView1);
        ImageDownloader downloader = new ImageDownloader();
        String url = "http://img87.imageshack.us/img87/5673/rotatetrans.png";
        
        
        downloader.download(url, test);
        //layout.addView(test);        
    }
    
}