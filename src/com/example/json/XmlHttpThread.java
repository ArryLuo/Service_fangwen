package com.example.json;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.Handler;
import android.widget.TextView;

public class XmlHttpThread extends Thread {
	private String url;
	private TextView content;
	private Handler handler;
	
	public XmlHttpThread(String url, TextView content, Handler handler) {
		super();
		this.url = url;
		this.content = content;
		this.handler = handler;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			URL xml_path=new URL(url);
			HttpURLConnection con=(HttpURLConnection) xml_path.openConnection();
			con.setReadTimeout(5000);
			con.setRequestMethod("GET");
			InputStream is=con.getInputStream();
			XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
			XmlPullParser parser=factory.newPullParser();
			parser.setInput(is, "UTF-8");
			//xml���Ƕ���ͨ���¼���������������
			int event_type=parser.getEventType();
			final List<XmlInfo>list=new ArrayList<>();
			XmlInfo info=null;
			//����¼����ͻ�û�н���
			while(event_type!=XmlPullParser.END_DOCUMENT){
			  String data=parser.getName();//��ȡÿһ���ڵ������
			  switch (event_type) {
			case XmlPullParser.START_TAG:
				if("lan".equals(data)){
					  info=new XmlInfo();
				}
				if("name".equals(data)){
					String name=parser.nextText();
					info.setName(name);
				}else if("idex".equals(data)){
					String index=parser.nextText();
					info.setIndex(index);
				}
				break;
			case XmlPullParser.END_TAG:
				if("lan".equals(data)&&info!=null){
					list.add(info);
					System.out.println(list.toString()+"----------");
				}
				break;
			}
				event_type=parser.next();//������һ��ѭ��
			}
			handler.post(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					content.setText(list.toString());
				}
			});
		} catch (MalformedURLException
				e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
