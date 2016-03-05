package com.example.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

public class DownloadImgHttpThread extends Thread {
	private ImageView img;
	private String url;
	private Handler handler;
	public DownloadImgHttpThread(ImageView img, String url, Handler handler) {
		super();
		this.img = img;
		this.url = url;
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
			con.setDoInput(true);
			con.setDoOutput(true);
			InputStream is=con.getInputStream();
			//内存卡的路径
			File path=Environment.getExternalStorageDirectory();
			//获取后缀名
			String str=url.substring(url.length()-4, url.length());
			//名字
			String name=String.valueOf(System.currentTimeMillis()+str);
			final File imgPath=new File(path, name);
			FileOutputStream fos=new FileOutputStream(imgPath);
			byte[]bt=new byte[1024*4];
			int len;
			while((len=is.read(bt))!=-1){
				fos.write(bt, 0, len);
			}
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					final Bitmap bitmap=BitmapFactory.decodeFile(imgPath.getAbsolutePath()+"");
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
