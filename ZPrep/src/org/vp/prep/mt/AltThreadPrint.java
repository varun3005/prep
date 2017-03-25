package org.vp.prep.mt;


class AltTread implements Runnable{
	private final static Object lock = new Object();
	private int nums[];
	private static volatile int flag = 0;
	private int currentThreadIndex=0;
	
	public AltTread(int nums[]){
		this.nums = nums;
	}
	
	@Override
	public void run() {
		while(true && currentThreadIndex<nums.length){
			synchronized(lock){
				int current=-1;
				try{current = Integer.parseInt(Thread.currentThread().getName());
					if(current!=flag){
						lock.wait();
					}
				}catch(InterruptedException e){
				}
				if(current!=flag){
					continue;
				}
				System.out.println(nums[currentThreadIndex]);
				currentThreadIndex++;
				flag=(flag+1)%3;
				lock.notifyAll();
			}
		}
	}
}

public class AltThreadPrint{
	
	public static void main(String args[]) throws InterruptedException{
		int zero[] = new int[]{1,4,7,10};
		int one[] = new int[]{2,5,8,11};
		int two[] = new int[]{3,6,9,12};
		
		Thread t1 = new Thread(new AltTread(zero),"0");
		Thread t2 = new Thread(new AltTread(one),"1");
		Thread t3 = new Thread(new AltTread(two),"2");
		
		t1.start();
		t2.start();
		t3.start();
		t1.join();
		t2.join();
		t3.join();
	}
	
}