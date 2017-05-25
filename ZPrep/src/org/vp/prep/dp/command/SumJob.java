package org.vp.prep.dp.command;

public class SumJob implements Job{
	
	private int a;
	private int b;
	
	public SumJob(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public boolean execute() {
		System.out.println(a+" + "+b+" = "+(a+b));
		return true;
	}

}
