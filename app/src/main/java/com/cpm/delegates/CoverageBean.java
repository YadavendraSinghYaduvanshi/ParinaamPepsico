package com.cpm.delegates;

public class CoverageBean
{
	protected int MID;
	
	protected String storeId;
	
	protected String userId;
	
	protected String inTime;
	
	protected String outTime;
	
	protected String visitDate,otherreson="";
	
	public String getOtherreson() {
		return otherreson;
	}

	public void setOtherreson(String otherreson) {
		this.otherreson = otherreson;
	}

	protected String keycontactId="";
	
	String storeinfo ="";
	public String getStoreinfo() {
		return storeinfo;
	}

	public void setStoreinfo(String storeinfo) {
		this.storeinfo = storeinfo;
	}

	protected String isdDeploy;
	
	protected String uploadStatus;
	
	private String latitude;
	
	private String longitude;
	
	private String reasonid="";
	
	private String reason="";
	
	private String status="N";
	
	private String image="";
	private String remarks="";
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMID() {
		return MID;
	}

	public void setMID(int mID) {
		MID = mID;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getKeycontactId() {
		return keycontactId;
	}

	public void setKeycontactId(String keycontactId) {
		this.keycontactId = keycontactId;
	}

	public String getIsdDeploy() {
		return isdDeploy;
	}

	public void setIsdDeploy(String isdDeploy) {
		this.isdDeploy = isdDeploy;
	}

	public String getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(String uploadStatus) {
		this.uploadStatus = uploadStatus;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getReasonid() {
		return reasonid;
	}

	public void setReasonid(String reasonid) {
		this.reasonid = reasonid;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
