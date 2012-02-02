package com.taimur38.hello;

import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;
import java.lang.ref.WeakReference;

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


/**
 * This helper class download images from the Internet and binds those with the provided ImageView.
 *
 * <p>It requires the INTERNET permission, which should be added to your application's manifest
 * file.</p>
 *
 * A local cache of downloaded images is maintained internally to improve performance.
 */
public class ImageDownloader {
    private static final String LOG_TAG = "ImageDownloader";
    
    public void download(String url, ImageView imageView) {
        
    	Bitmap bitmap = Session.getImage(url);
        
        if (bitmap == null) 
            forceDownload(url, imageView);
         else 
        	 imageView.setImageBitmap(bitmap);
        
    }
    
    private void forceDownload(String url, ImageView imageView) 
    {
    	BitmapDownloaderTask task = new BitmapDownloaderTask(imageView);
    	
    	task.execute(url); 
    }
    
    class BitmapDownloaderTask extends AsyncTask<String, Void, Bitmap> 
    {
        private String url;
        private final WeakReference<ImageView> imageViewReference;

        public BitmapDownloaderTask(ImageView imageView) 
        {
            imageViewReference = new WeakReference<ImageView>(imageView);
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
                //BitmapDownloaderTask bitmapDownloaderTask = new BitmapDownloaderTask(imageView);
            
                imageView.setImageBitmap(bitmap);
                
            }
        }
    }
    
    Bitmap downloadBitmap(String url) {
        final int IO_BUFFER_SIZE = 4 * 1024;
        
        Bitmap tmp = Session.getImage(url);
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
                    Session.cacheImage(url, pic);
                    return pic;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (IOException e) {
            getRequest.abort();
            Log.w(LOG_TAG, "I/O error while retrieving bitmap from " + url, e);
        } catch (IllegalStateException e) {
            getRequest.abort();
            Log.w(LOG_TAG, "Incorrect URL: " + url);
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