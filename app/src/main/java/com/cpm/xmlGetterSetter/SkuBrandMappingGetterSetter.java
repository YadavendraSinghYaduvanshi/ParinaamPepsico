package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class SkuBrandMappingGetterSetter {
	
	ArrayList<String> skuid = new ArrayList<String>();
	ArrayList<String> brandid = new ArrayList<String>();
	ArrayList<String> skuname = new ArrayList<String>();
	public ArrayList<String> getSkuid() {
		return skuid;
	}
	public void setSkuid(String skuid) {
		this.skuid.add(skuid);
	}
	public ArrayList<String> getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid.add(brandid);
	}
	public ArrayList<String> getSkuname() {
		return skuname;
	}
	public void setSkuname(String skuname) {
		this.skuname.add(skuname);
	}
	
	
	

}
