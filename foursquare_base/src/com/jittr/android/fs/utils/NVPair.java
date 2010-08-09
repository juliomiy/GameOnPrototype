package com.jittr.android.fs.utils;

public class NVPair {

	String name;
	String value;
	
	public NVPair(String aname,String avalue) {
		this.name = aname;
		this.value = avalue;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
