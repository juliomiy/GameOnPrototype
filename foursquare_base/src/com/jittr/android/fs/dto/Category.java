package com.jittr.android.fs.dto;

public class Category {
	String id;
	String fullpathName;
	String nodeName;

  
  	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullpathName() {
		return fullpathName;
	}
	public void setFullpathName(String fullpathName) {
		this.fullpathName = fullpathName;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	
	@Override
	public String toString() {
		return "Category [fullpathName=" + fullpathName + ", id=" + id
				+ ", nodeName=" + nodeName + "]";
	}

	
}
