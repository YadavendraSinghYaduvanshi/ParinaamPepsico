package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class SkuTargetGetterSetter {

	ArrayList<String> STORE_ID = new ArrayList<String>();
	ArrayList<String> SKU_ID = new ArrayList<String>();
	ArrayList<String> posm_id = new ArrayList<String>();
	ArrayList<String> target = new ArrayList<String>();
	
	public ArrayList<String> getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target.add(target);
	}
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
	public ArrayList<String> getPosm_id() {
		return posm_id;
	}
	public void setPosm_id(String posm_id) {
		this.posm_id.add(posm_id);
	}
	
	

	

	
}
