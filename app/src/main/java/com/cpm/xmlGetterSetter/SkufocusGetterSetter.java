package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class SkufocusGetterSetter {

	ArrayList<String> STORE_ID = new ArrayList<String>();
	ArrayList<String> SKU_ID = new ArrayList<String>();
	ArrayList<String> FOCUS = new ArrayList<String>();
	public ArrayList<String> getSTORE_ID() {
		return STORE_ID;
	}
	public void setSTORE_ID(String sTORE_ID) {
		this.STORE_ID.add(sTORE_ID);
	}
	public ArrayList<String> getSKU_ID() {
		return SKU_ID;
	}
	public void setSKU_ID(String sKU_ID) {
		this.SKU_ID.add(sKU_ID);
	}
	
	public ArrayList<String> getFOCUS() {
		return FOCUS;
	}
	public void setFOCUS(String fOCUS) {
		this.FOCUS.add(fOCUS);
	}
	

	

	
}
