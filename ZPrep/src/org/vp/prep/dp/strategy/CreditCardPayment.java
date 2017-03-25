package org.vp.prep.dp.strategy;

public class CreditCardPayment implements Payment{
	private String name;
	private String cardNumber;
	private String expiry;
	private String cvv;
	
	public CreditCardPayment(String name, String cardNumber, String expiry, String cvv) {
		super();
		this.name = name;
		this.cardNumber = cardNumber;
		this.expiry = expiry;
		this.cvv = cvv;
	}

	@Override
	public boolean pay(int amount) {
		System.out.println("Paying "+amount+" via credit card:"+cardNumber);
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	
}
