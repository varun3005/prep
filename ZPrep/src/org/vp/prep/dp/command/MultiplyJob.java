package org.vp.prep.dp.command;

public class MultiplyJob implements Job {
	private int a;
	private int b;

	public MultiplyJob(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public boolean execute() {

		System.out.println(a+" * "+b+" = " + a * b);
		return true;
	}

}
