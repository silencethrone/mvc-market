package com.xx.model;

public class Files {
	private int id;
	private String filename;
	private String settime;
	private String url;
	private String owner;
	
	public Files(int id, String filename, String settime, String url, String owner) {
		super();
		this.id = id;
		this.filename = filename;
		this.settime = settime;
		this.url = url;
		this.owner = owner;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getSettime() {
		return settime;
	}
	public void setSettime(String settime) {
		this.settime = settime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	
}
