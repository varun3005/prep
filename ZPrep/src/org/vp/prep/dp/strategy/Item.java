package org.vp.prep.dp.strategy;

public class Item {

	private String code;
	private int price;

	public Item(String code, int price) {
		super();
		this.code = code;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
