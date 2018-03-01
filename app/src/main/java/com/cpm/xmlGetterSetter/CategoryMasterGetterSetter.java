package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class CategoryMasterGetterSetter {

	ArrayList<String> category_id = new ArrayList<String>();
	ArrayList<String> CODE = new ArrayList<String>();
	ArrayList<String> category_name = new ArrayList<String>();
	ArrayList<String> category_seq = new ArrayList<String>();
	
	public ArrayList<String> getCategory_seq() {
		return category_seq;
	}
	public void setCategory_seq(String category_seq) {
		this.category_seq.add(category_seq);
	}
	public ArrayList<String> getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id.add(category_id);
	}
	public ArrayList<String> getCODE() {
		return CODE;
	}
	public void setCODE(String cODE) {
		this.CODE.add(cODE);
	}
	public ArrayList<String> getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name.add(category_name);
	}
	

	
}
