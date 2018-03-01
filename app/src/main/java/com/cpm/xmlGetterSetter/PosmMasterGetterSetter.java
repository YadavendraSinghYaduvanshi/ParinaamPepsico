package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class PosmMasterGetterSetter {

	
	ArrayList<String> posmid = new ArrayList<String>();

	ArrayList<String> posmname = new ArrayList<String>();

	public ArrayList<String> getPosmid() {
		return posmid;
	}
	public void setPosmid(String posmid) {
		this.posmid.add(posmid);
	}
	
	public ArrayList<String> getPosmname() {
		return posmname;
	}
	public void setPosmname(String posmname) {
		this.posmname.add(posmname);
	}
	

	
}
