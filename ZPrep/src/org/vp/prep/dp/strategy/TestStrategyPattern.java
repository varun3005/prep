package org.vp.prep.dp.strategy;

public class TestStrategyPattern {

	public static void main(String[] args) {
		Cart cart = new Cart();
		cart.addItem(new Item("1", 20));
		cart.addItem(new Item("2", 30));
		
		cart.pay(new CreditCardPayment("Varun Pathak", "1232234356758908", "10/12", "123"));
		cart.pay(new NetBankingPayment("ICICI", "vpathak", "pass"));
	}
}
