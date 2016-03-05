package com.example.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.moder.Person;
import com.example.moder.SchooleInfo;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

public class ParsexJsonHttpThread extends Thread {
	private String url;
	private ListView listview;
	private MyApdate apdate;
	private Handler handler;
	private Context context;

	public ParsexJsonHttpThread( String url, ListView listview,
			MyApdate apdate, Handler handler) {
		super();
		this.url = url;
		this.listview = listview;
		this.apdate = apdate;
		this.handler = handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		// 获取json
		try {
			URL json_path = new URL(url);
			HttpURLConnection con = (HttpURLConnection) json_path
					.openConnection();
			con.setReadTimeout(5000);
			con.setRequestMethod("GET");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String len;
			while ((len = br.readLine()) != null) {
				sb.append(len);
			}
			System.out.println(sb.toString()+"没有数据");
			final List<Person>person_data=getPerson(sb.toString());
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					apdate.getList(person_data);
					listview.setAdapter(apdate);
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

	private List<Person> getPerson(String json) {
		try {
			List<Person> list = new ArrayList<>();
			JSONObject object = new JSONObject(json);
			int result = object.getInt("result");
			if (result == 1) {
				Person person = null;
				JSONArray person_array = object.getJSONArray("persons");
				for (int i = 0; i < person_array.length(); i++) {
					//person的节点
					JSONObject person_object = person_array.getJSONObject(i);
					//节点里面的值
					String name = person_object.getString("name");
					String age = person_object.getString("age");
					String url=person_object.getString("url");
					person=new Person(name, age,url);
					//学校的集合
					List<SchooleInfo>schools=new ArrayList<>();
					JSONArray school_array=person_object.getJSONArray("schoolInfo");
					for (int j = 0; j < school_array.length(); j++) {
						SchooleInfo info=new SchooleInfo();
						JSONObject school_object=school_array.getJSONObject(j);
						String school_name=school_object.getString("school_name");
						info.setSchool_name(school_name);
						schools.add(info);
					   person.setSchooleInfos(schools);
					}
					list.add(person);
				}
				return list;
			} else {
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
