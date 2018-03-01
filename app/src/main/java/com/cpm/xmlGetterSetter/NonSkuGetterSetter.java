package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class NonSkuGetterSetter {
	
	
	ArrayList<String> stockreasonid = new ArrayList<String>();
	ArrayList<String> stockreason = new ArrayList<String>();
	
	public ArrayList<String> getStockreasonid() {
		return stockreasonid;
	}

	public void setStockreasonid(String stockreasonid) {
		this.stockreasonid.add(stockreasonid);
	}
	public ArrayList<String> getStockreason() {
		return stockreason;
	}
	public void setStockreason(String stockreason) {
		this.stockreason.add(stockreason);
	}

}
