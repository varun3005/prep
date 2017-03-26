package org.vp.prep.mt;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// A correct implementation of a producer and consumer.
class ProducerConsumerMulti {
	private int max = 10; // min value
	Queue<String> queue;
	public ProducerConsumerMulti(int max) {
		this.max = max>this.max?max:this.max;
		queue = new ArrayDeque<>(max);
	}
	
	public synchronized void put(String n){
		if(queue.size()== max){
			try{
				wait();
			}catch(InterruptedException e){
				
			}
		}
		queue.add(n);
		System.out.println("Added:"+n);
		notifyAll();
	}
	
	public synchronized String get(){
		if(queue.isEmpty()){
			try{
				wait();
			}catch(InterruptedException e){
				
			}
		}
		String n = queue.poll();
		System.out.println("Got:"+n);
		notifyAll();
		return n;
	}
	
	public static void main(String args[]) {
		ProducerConsumerMulti pcm = new ProducerConsumerMulti(10);
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i=1;i<=5;i++){
			Thread p = new Thread(new ProducerM(pcm), "Producer"+i);
			Thread c = new Thread(new ConsumerM(pcm), "Consumer"+i);
			es.submit(p);
			es.submit(c);
		}
		es.shutdown();
		try {
			es.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			if (!es.isTerminated()) {
				System.out.println("Killing all now");
				es.shutdownNow();
			}
		}
	}
}

class ProducerM implements Runnable{
	ProducerConsumerMulti pcm;
	
	public ProducerM (ProducerConsumerMulti pcm) {
		this.pcm = pcm;
	}

	@Override
	public void run() {
		int i=1;
		while(true){
			if(i%100==0){
				i=1;
			}
			pcm.put(Thread.currentThread().getName()+":"+i++);
		}
	}
}

class ConsumerM implements Runnable{
	ProducerConsumerMulti pcm;
	
	public ConsumerM (ProducerConsumerMulti pcm) {
		this.pcm = pcm;
	}

	@Override
	public void run() {
		while(true){
			pcm.get();
		}
	}
}


