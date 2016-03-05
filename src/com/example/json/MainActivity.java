package com.example.json;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView listview;
	private String url="http://192.168.56.1:8080/D/MyJson";
	private Handler handler=new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listview=(ListView) findViewById(R.id.listview);
		MyApdate apdate=new MyApdate(this);
		new ParsexJsonHttpThread(url, listview, apdate, handler).start();
	}
}
