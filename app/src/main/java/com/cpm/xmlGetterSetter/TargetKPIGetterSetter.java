package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class TargetKPIGetterSetter {
	ArrayList<String> kpi = new ArrayList<String>();
	ArrayList<String> food = new ArrayList<String>();
	ArrayList<String> bev = new ArrayList<String>();
	public ArrayList<String> getkpi() {
		return kpi;
	}

	public void setkpi(String kpi) {
		this.kpi.add(kpi);
	}

	public ArrayList<String> getfood() {
		return food;
	}

	public void setfood(String food) {
		this.food.add(food);
	}

	public ArrayList<String> getbev() {
		return bev;
	}

	public void setbev(String bev) {
		this.bev.add(bev);
	}
}
