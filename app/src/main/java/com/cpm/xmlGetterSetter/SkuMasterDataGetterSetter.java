package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class SkuMasterDataGetterSetter {

	ArrayList<String> sku_id = new ArrayList<String>();
	ArrayList<String> sku_name = new ArrayList<String>();
	ArrayList<String> brand_id = new ArrayList<String>();
	ArrayList<String> skuseq = new ArrayList<String>();
	
	public ArrayList<String> getSkuseq() {
		return skuseq;
	}
	public void setSkuseq(String skuseq) {
		this.skuseq.add(skuseq);
	}
	public ArrayList<String> getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(String brand_id) {
		this.brand_id.add(brand_id);
	}
	public ArrayList<String> getSku_id() {
		return sku_id;
	}
	public void setSku_id(String sku_id) {
		this.sku_id.add(sku_id);
	}
	public ArrayList<String> getSku_name() {
		return sku_name;
	}
	public void setSku_name(String sku_name) {
		this.sku_name.add(sku_name);
	}
	

	
}
