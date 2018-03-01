package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class CompanyMasterGetterSetter {

	ArrayList<String> companyid = new ArrayList<String>();
	ArrayList<String> company = new ArrayList<String>();
	ArrayList<String> competitor = new ArrayList<String>();
	ArrayList<String> nonkeycompetitor = new ArrayList<String>();
	
	
	
	public ArrayList<String> getNonkeycompetitor() {
		return nonkeycompetitor;
	}
	public void setNonkeycompetitor(String nonkeycompetitor) {
		this.nonkeycompetitor.add(nonkeycompetitor);
	}
	public ArrayList<String> getCompetitor() {
		return competitor;
	}
	public void setCompetitor(String competitor) {
		this.competitor.add(competitor);
	}
	public ArrayList<String> getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid.add(companyid);
	}
	public ArrayList<String> getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company.add(company);
	}
	
	

	
}
