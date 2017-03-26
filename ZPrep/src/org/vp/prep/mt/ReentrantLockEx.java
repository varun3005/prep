package org.vp.prep.mt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		ReentrantLock lock = new ReentrantLock();
		
		
		executor.submit(() -> {
		    lock.lock();
		    try {
					Thread.sleep(2000);
		    } catch(Exception e){
		    	
		    }finally {
		        lock.unlock();
		    }
		});

		executor.submit(() -> {
		    System.out.println("Locked: " + lock.isLocked());
		    lock.lock(); //Block till lock is acquired
		    System.out.println("Held by me: " + lock.isHeldByCurrentThread());
		    boolean locked = lock.tryLock();
		    System.out.println("Lock acquired: " + locked);
		});

		executor.shutdown();
	}
}
