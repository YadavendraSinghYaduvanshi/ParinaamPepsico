package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class VerticalBrandMappingGetterSetter {

	ArrayList<String> verticalid = new ArrayList<String>();
	ArrayList<String> brandid = new ArrayList<String>();
	ArrayList<String> brandname = new ArrayList<String>();
	public ArrayList<String> getVerticalid() {
		return verticalid;
	}
	public void setVerticalid(String verticalid) {
		this.verticalid.add(verticalid);
	}
	public ArrayList<String> getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid.add(brandid);
	}
	public ArrayList<String> getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname.add(brandname);
	}

	
}
