package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class TargetBrandWiseGetterSetter {
	String meta_data;
	
	ArrayList<String> verticalId = new ArrayList<String>();
	ArrayList<String> brandid = new ArrayList<String>();
	ArrayList<String> target = new ArrayList<String>();

	public ArrayList<String> getVerticalId() {
		return verticalId;
	}

	public void setVerticalId(String verticalId) {
		this.verticalId.add(verticalId);
	}

	public ArrayList<String> getBrandid() {
		return brandid;
	}

	public void setBrandid(String brandid) {
		this.brandid.add(brandid);
	}

	public ArrayList<String> getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target.add(target);
	}

	public String getMeta_data() {
		return meta_data;
	}

	public void setMeta_data(String meta_data) {
		this.meta_data = meta_data;
	}
}
