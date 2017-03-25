package org.vp.prep.mt;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {
	int num;
	
	CallableTask(int num){
		this.num = num;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println(Thread.currentThread().getName()+" Task:" +num+ " sleeping for "+(5-num)+" seconds");
		Thread.sleep((5-num)*1000);
		System.out.println(Thread.currentThread().getName()+" Task:" +num+" done sleeping for "+(5-num)+" seconds");
		return num;
	}

}
