package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class VerticalMasterGetterSetter {

	ArrayList<String> verticalid = new ArrayList<String>();
	ArrayList<String> vertical_name = new ArrayList<String>();
	
	public ArrayList<String> getVerticalid() {
		return verticalid;
	}
	public void setVerticalid(String verticalid) {
		this.verticalid.add(verticalid);
	}
	public ArrayList<String> getVertical_name() {
		return vertical_name;
	}
	public void setVertical_name(String vertical_name) {
		this.vertical_name.add(vertical_name);
	}
	
	
	
	
	
	
}
