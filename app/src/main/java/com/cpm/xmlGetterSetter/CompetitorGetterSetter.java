package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class CompetitorGetterSetter {

	ArrayList<String> brandid = new ArrayList<String>();

	ArrayList<String> competitor_brandid = new ArrayList<String>();
	
	
	

	public ArrayList<String> getCompetitor_brandid() {
		return competitor_brandid;
	}
	public void setCompetitor_brandid(String competitor_brandid) {
		this.competitor_brandid.add(competitor_brandid);
	}
	public ArrayList<String> getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid.add(brandid);
	}

	

	
}
