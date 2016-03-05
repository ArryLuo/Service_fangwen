package com.example.moder;

import java.util.List;

public class Person {
private String name;
private String url;
private String age;

public Person() {
	super();
	// TODO Auto-generated constructor stub
}

public Person(String name, String age,String url) {
	super();
	this.name = name;
	this.age = age;
	this.url=url;
}

public Person(String name, String url, String age,
		List<SchooleInfo> schooleInfos) {
	super();
	this.name = name;
	this.url = url;
	this.age = age;
	this.schooleInfos = schooleInfos;
}
private List<SchooleInfo>schooleInfos;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public List<SchooleInfo> getSchooleInfos() {
	return schooleInfos;
}
public void setSchooleInfos(List<SchooleInfo> schooleInfos) {
	this.schooleInfos = schooleInfos;
}

}
