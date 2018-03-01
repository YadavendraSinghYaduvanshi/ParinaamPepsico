package com.cpm.delegates;


public class LocationBean {
	
	private String visitdate,time,latitude,longitude,network_status;
	int id;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVisitDate() {
		return visitdate;
	}
	public void setVisitDate(String visitdate) {
		this.visitdate = visitdate;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time= time;
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitide) {
		this.latitude = latitide;
	}
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getNetworkStatus() {
		return network_status;
	}
	public void setNetworkStatus(String networkstatus) {
		this.network_status = networkstatus;
	}

	
	
	

}
