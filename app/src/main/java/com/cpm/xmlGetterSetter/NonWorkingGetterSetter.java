package com.cpm.xmlGetterSetter;

import java.util.ArrayList;

public class NonWorkingGetterSetter {

	ArrayList<String> reason_id = new ArrayList<String>();
	ArrayList<String> reason = new ArrayList<String>();
	ArrayList<String> entry = new ArrayList<String>();
	ArrayList<String> image = new ArrayList<String>();
	ArrayList<String> other = new ArrayList<String>();

	public ArrayList<String> getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other.add(other);
	}

	ArrayList<String> mreason_id = new ArrayList<String>();
	ArrayList<String> mreason = new ArrayList<String>();
	ArrayList<String> keyid = new ArrayList<String>();
	ArrayList<String> keyacunt = new ArrayList<String>();

	ArrayList<String> informedto = new ArrayList<String>();
	ArrayList<String> storeinfo = new ArrayList<String>();

	public ArrayList<String> getMreason_id() {
		return mreason_id;
	}

	public void setMreason_id(String mreason_id) {
		this.mreason_id.add(mreason_id);
	}

	public ArrayList<String> getMreason() {
		return mreason;
	}

	public void setMreason(String mreason) {
		this.mreason.add(mreason);
	}

	public ArrayList<String> getKeyid() {
		return keyid;
	}

	public void setKeyid(String keyid) {
		this.keyid.add(keyid);
	}

	public ArrayList<String> getKeyacunt() {
		return keyacunt;
	}

	public void setKeyacunt(String keyacunt) {
		this.keyacunt.add(keyacunt);
	}

	public ArrayList<String> getInformedto() {
		return informedto;
	}

	public void setInformedto(String informedto) {
		this.informedto.add(informedto);
	}

	public ArrayList<String> getStoreinfo() {
		return storeinfo;
	}

	public void setStoreinfo(String storeinfo) {
		this.storeinfo.add(storeinfo);
	}

	public ArrayList<String> getReason_id() {
		return reason_id;
	}

	public void setReason_id(String reason_id) {
		this.reason_id.add(reason_id);
	}

	public ArrayList<String> getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason.add(reason);
	}

	public ArrayList<String> getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry.add(entry);
	}

	public ArrayList<String> getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image.add(image);
	}

}
