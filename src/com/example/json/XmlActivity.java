package com.example.json;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class XmlActivity extends Activity {
	private TextView content;
	private String url="http://192.168.56.1:8080/D/languaes.xml";
	private Handler handler=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xml);
		content=(TextView) findViewById(R.id.content);
		new XmlHttpThread(url, content, handler).start();
		findViewById(R.id.send).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(XmlActivity.this, DownlodActivity.class);
				startActivity(intent);
			}
		});
	}
}
