package org.vp.prep.dp.strategy;

public class NetBankingPayment implements Payment{
	private String bank;
	private String userName;
	private String password;
	private int amount;
	
	public NetBankingPayment(String bank, String userName, String password) {
		super();
		this.bank = bank;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public boolean pay(int amount) {
		System.out.println("Paying "+amount+" using netBanking with bank:"+bank+" user:"+userName);
		return true;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
