package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class BrandMasterGetterSetter {

	ArrayList<String> brandid = new ArrayList<String>();
	ArrayList<String> brand = new ArrayList<String>();
	ArrayList<String> categoryid = new ArrayList<String>();
	ArrayList<String> BRANDSEQ = new ArrayList<String>();
	ArrayList<String> company_id = new ArrayList<String>();
	
	
	
	public ArrayList<String> getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id.add(company_id);
	}
	public ArrayList<String> getBRANDSEQ() {
		return BRANDSEQ;
	}
	public void setBRANDSEQ(String bRANDSEQ) {
		this.BRANDSEQ.add(bRANDSEQ);
	}
	public ArrayList<String> getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid.add(categoryid);
	}
	public ArrayList<String> getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid.add(brandid);
	}
	public ArrayList<String> getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand.add(brand);
	}
	

	
}
