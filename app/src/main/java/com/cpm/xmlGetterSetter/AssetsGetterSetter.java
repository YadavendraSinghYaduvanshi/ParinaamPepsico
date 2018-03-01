package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class AssetsGetterSetter {

	ArrayList<String> ASSET_ID = new ArrayList<String>();
	ArrayList<String> ASSET = new ArrayList<String>();
	
	public ArrayList<String> getASSET_ID() {
		return ASSET_ID;
	}
	public void setASSET_ID(String aSSET_ID) {
		this.ASSET_ID.add(aSSET_ID);
	}
	public ArrayList<String> getASSET() {
		return ASSET;
	}
	public void setASSET(String aSSET) {
		this.ASSET.add(aSSET);
	}

	
	
	
	
	

	
}
