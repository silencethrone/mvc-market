package com.xx.model;

public class Goods {
	private int id;
	private String goodsname;
	private String description;
	private float price;
	private int stock;
	public Goods(int id, String goodsname, String description, float price, int stock) {
		super();
		this.id = id;
		this.goodsname = goodsname;
		this.description = description;
		this.price = price;
		this.stock = stock;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
