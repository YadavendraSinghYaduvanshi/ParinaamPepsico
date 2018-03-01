package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class CategoryMappingGetterSetter {

	ArrayList<String> category_id = new ArrayList<String>();
	ArrayList<String> sku_id = new ArrayList<String>();
	ArrayList<String> sku_name = new ArrayList<String>();
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
	public ArrayList<String> getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id.add(category_id);
	}

	

	
}
