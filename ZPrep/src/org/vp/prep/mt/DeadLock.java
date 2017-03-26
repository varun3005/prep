package org.vp.prep.mt;

public class DeadLock {
	public static void main(String args[]) {
		String s1 = "Dead";
		String s2 = "Lock";
		MyThread1DL m = new MyThread1DL(s1, s2);
		MyThread2DL m1 = new MyThread2DL(s1, s2);
	}
}

class MyThread1DL extends Thread {
	String s1;
	String s2;

	MyThread1DL(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
		start();
	}

	public void run() {
		while (true) {
			synchronized (s1) {
				synchronized (s2) {
					System.out.println(s1 + s2);
				}
			}
		}
	}
}

class MyThread2DL extends Thread {
	String s1;
	String s2;

	MyThread2DL(String s1, String s2) {
		this.s1 = s1;
		this.s2 = s2;
		start();
	}

	public void run() {
		while (true) {
			synchronized (s2) {
				synchronized (s1) {
					System.out.println(s2 + s1);
				}
			}
		}
	}
}