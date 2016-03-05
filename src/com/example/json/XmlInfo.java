package com.example.json;

public class XmlInfo {
public XmlInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
private String name;
private String index;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public XmlInfo(String name, String index) {
	super();
	this.name = name;
	this.index = index;
}
public String getIndex() {
	return index;
}
public void setIndex(String index) {
	this.index = index;
}
@Override
public String toString() {
	return "XmlInfo [name=" + name + ", index=" + index + "]";
}

}
