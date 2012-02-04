package com.taimur38.hello;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;


public class ImageDownloader {
    private static final String LOG_TAG = "ImageDownloader";
    private static HashMap<String, Bitmap> Images = null;
    
    public static void download(String url, ImageView imageView) {
        url = url.replace(" ", "%20");
    	Bitmap bitmap = getImage(url);
        
        if (bitmap == null){
        	BitmapDownloaderTask task = new BitmapDownloaderTask(imageView);
        	task.execute(url); 
        }else 
        	if(imageView != null)
        		imageView.setImageBitmap(bitmap);
        
    }
    
    static void cacheImage(String url, Bitmap bmp)
	{
		if(Images == null)
			Images = new HashMap<String, Bitmap>();
		Images.put(url, bmp);
	}
	

	static Bitmap getImage(String url)
	{
		if(Images == null)
			Images = new HashMap<String, Bitmap>();
		synchronized(Images){
			return Images.get(url);}
	}

    static class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> 
    {
        private String url;
        private final WeakReference<ImageView> imageViewReference;

        public BitmapDownloaderTask(ImageView imageView) 
        {
        	if(imageView != null)
        		imageViewReference = new WeakReference<ImageView>(imageView);
        	else
        		imageViewReference = null;
        }

       
        @Override
        protected Bitmap doInBackground(String... params) 
        {
            url = params[0];
            return downloadBitmap(url);
        }

      
        @Override
        protected void onPostExecute(Bitmap bitmap) 
        {

            if (imageViewReference != null) {
                ImageView imageView = imageViewReference.get();
                imageView.setImageBitmap(bitmap);
                imageView.setMaxHeight(50);
                imageView.setMaxWidth(320);
                
            }
        }
    }
    
    static Bitmap downloadBitmap(String url) {
        Bitmap tmp = getImage(url);
        if(tmp != null)
        	return tmp;
        
        // AndroidHttpClient is not allowed to be used from the main thread
        final HttpClient client = AndroidHttpClient.newInstance("Android");
        final HttpGet getRequest = new HttpGet(url);

        try {
            HttpResponse response = client.execute(getRequest);
            final int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                Header[] headers = response.getHeaders("Location");
                
                if(headers != null && headers.length != 0){
                	String newUrl = headers[headers.length - 1].getValue();
                	return downloadBitmap(newUrl);
                }
            }

            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = null;
                try {
                    inputStream = entity.getContent();
                    Bitmap pic = BitmapFactory.decodeStream(new FlushedInputStream(inputStream));
                    cacheImage(url, pic);
                    return pic;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (Exception e) {
            getRequest.abort();
            Log.w(LOG_TAG, "Error while retrieving bitmap from " + url, e);
        } finally {
            if ((client instanceof AndroidHttpClient)) {
                ((AndroidHttpClient) client).close();
            }
        }
        return null;

    }
    
    static class FlushedInputStream extends FilterInputStream {
        public FlushedInputStream(InputStream inputStream) {
            super(inputStream);
        }

        @Override
        public long skip(long n) throws IOException {
            long totalBytesSkipped = 0L;
            while (totalBytesSkipped < n) {
                long bytesSkipped = in.skip(n-totalBytesSkipped);
                if (bytesSkipped == 0L) break;
                totalBytesSkipped += bytesSkipped;
            }
            return totalBytesSkipped;
        }
    }
}