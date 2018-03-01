package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class CategoryVerticalMappingGetterSetter {

	ArrayList<String> category_id = new ArrayList<String>();
	ArrayList<String> vertical_id = new ArrayList<String>();
	ArrayList<String> category_name = new ArrayList<String>();
	public ArrayList<String> getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id.add(category_id);
	}
	public ArrayList<String> getVertical_id() {
		return vertical_id;
	}
	public void setVertical_id(String vertical_id) {
		this.vertical_id.add(vertical_id);
	}
	public ArrayList<String> getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name.add(category_name);
	}
	

	

	
}
