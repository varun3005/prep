package org.vp.prep.dp.strategy;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private List<Item> items;
	
	public Cart() {
		super();
		items = new ArrayList<>();
	}
	
	public void addItem(Item item){
		if(item == null){
			throw new NullPointerException("Item cannot be null");
		}
		items.add(item);
	}
	
	public void removeItem(Item item){
		if(item == null){
			throw new NullPointerException("Item cannot be null");
		}
		items.remove(item);
	}
	
	public void clearCart(){
		items.clear();
	}
	
	public int getTotal(){
		int total = 0;
		for(Item item:items){
			total+=item.getPrice();
		}
		return total;
	}
	
	public boolean pay(Payment paymentMethod){
		int total = getTotal();
		paymentMethod.pay(total);
		return true;
	}
	
}
