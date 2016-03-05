package com.example.json;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class DownlodActivity extends Activity {
	private ImageView img;
	private Handler handler=new Handler();
	private String url="http://192.168.56.1:8080/Download/sd.mp3";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_downlod);
		img=(ImageView) findViewById(R.id.img);
		findViewById(R.id.btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new DownloadImgHttpThread(img, url, handler).start();
			}
		});
	}

}
