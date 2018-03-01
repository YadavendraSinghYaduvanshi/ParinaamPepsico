package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class PromotionGetterSetter {

	ArrayList<String> storeid = new ArrayList<String>();
	ArrayList<String> PROMO_ID = new ArrayList<String>();
	ArrayList<String> PROMOTYPE_ID = new ArrayList<String>();
	ArrayList<String> PROMO = new ArrayList<String>();
	ArrayList<String> PROMO_TYPE = new ArrayList<String>();
	ArrayList<String> Vertical_id = new ArrayList<String>();
	public ArrayList<String> getVertical_id() {
		return Vertical_id;
	}
	public void setVertical_id(String vertical_id) {
		Vertical_id.add(vertical_id);
	}
	public ArrayList<String> getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid.add(storeid);
	}
	public ArrayList<String> getPROMO_ID() {
		return PROMO_ID;
	}
	public void setPROMO_ID(String pROMO_ID) {
		this.PROMO_ID.add(pROMO_ID);
	}
	public ArrayList<String> getPROMOTYPE_ID() {
		return PROMOTYPE_ID;
	}
	public void setPROMOTYPE_ID(String pROMOTYPE_ID) {
		this.PROMOTYPE_ID.add(pROMOTYPE_ID);
	}
	public ArrayList<String> getPROMO() {
		return PROMO;
	}
	public void setPROMO(String pROMO) {
		this.PROMO.add(pROMO);
	}
	public ArrayList<String> getPROMO_TYPE() {
		return PROMO_TYPE;
	}
	public void setPROMO_TYPE(String pROMO_TYPE) {
		this.PROMO_TYPE.add(pROMO_TYPE);
	}
	
	
	
	
	
	
	

	
}
