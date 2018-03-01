package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class AssetsTargetGetterSetter {

	ArrayList<String> ASSET_ID = new ArrayList<String>();
	ArrayList<String> STORE_ID = new ArrayList<String>();
	ArrayList<String> SKU_ID = new ArrayList<String>();
	ArrayList<String> TARGETQTY = new ArrayList<String>();
	ArrayList<String> verticalid = new ArrayList<String>();
	ArrayList<String> ASSET_PURE = new ArrayList<String>();
	
	ArrayList<String> BRAND_ID = new ArrayList<String>();
	public ArrayList<String> getBRAND_ID() {
		return BRAND_ID;
	}
	public void setBRAND_ID(String bRAND_ID) {
		this.BRAND_ID.add(bRAND_ID);
	}
	public ArrayList<String> getBRAND_NAME() {
		return BRAND_NAME;
	}
	public void setBRAND_NAME(String bRAND_NAME) {
		this.BRAND_NAME.add(bRAND_NAME);
	}
	ArrayList<String> BRAND_NAME = new ArrayList<String>();
	
	public ArrayList<String> getASSET_PURE() {
		return ASSET_PURE;
	}
	public void setASSET_PURE(String aSSET_PURE) {
		this.ASSET_PURE.add(aSSET_PURE);
	}
	public ArrayList<String> getVerticalid() {
		return verticalid;
	}
	public void setVerticalid(String verticalid) {
		this.verticalid.add(verticalid);
	}
	public ArrayList<String> getASSET_ID() {
		return ASSET_ID;
	}
	public void setASSET_ID(String aSSET_ID) {
		this.ASSET_ID.add(aSSET_ID);
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
	public ArrayList<String> getTARGETQTY() {
		return TARGETQTY;
	}
	public void setTARGETQTY(String tARGETQTY) {
		this.TARGETQTY.add(tARGETQTY);
	}
	

	
	
	
	
	

	
}
