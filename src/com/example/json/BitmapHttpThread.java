package com.example.json;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

public class BitmapHttpThread extends Thread {
	private String url;
	private ImageView img;
	private Handler handler;
	public BitmapHttpThread(String url, ImageView img, Handler handler) {
		super();
		this.url = url;
		this.img = img;
		this.handler = handler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			URL img_path=new URL(url);
			HttpURLConnection con=(HttpURLConnection) img_path.openConnection();
			con.setReadTimeout(5000);
			con.setRequestMethod("GET");
			final Bitmap bitmap=BitmapFactory.decodeStream(con.getInputStream());
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					img.setImageBitmap(bitmap);
				}
			});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
