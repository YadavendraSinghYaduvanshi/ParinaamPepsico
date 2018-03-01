package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class KpiReportGetterSetter {
	
	
	ArrayList<String>storeid = new ArrayList<String>();
	ArrayList<String>vertical_Type = new ArrayList<String>();
	ArrayList<String>KPI = new ArrayList<String>();
	
	ArrayList<String>Kpi_Value = new ArrayList<String>();
	ArrayList<String>SR_NO = new ArrayList<String>();
	
	
	public ArrayList<String> getSR_NO() {
		return SR_NO;
	}
	public void setSR_NO(String sR_NO) {
		this.SR_NO.add(sR_NO);
	}
	public ArrayList<String> getStoreid() {
		return storeid;
	}
	public void setStoreid(String storeid) {
		this.storeid.add(storeid);
	}
	public ArrayList<String> getVertical_Type() {
		return vertical_Type;
	}
	public void setVertical_Type(String vertical_Type) {
		this.vertical_Type.add(vertical_Type);
	}
	public ArrayList<String> getKPI() {
		return KPI;
	}
	public void setKPI(String sOS) {
		this.KPI.add(sOS);
	}
	public ArrayList<String> getKpi_Value() {
		return Kpi_Value;
	}
	public void setKpi_Value(String kpi_Value) {
		this.Kpi_Value.add(kpi_Value);
	}
	
	

}
