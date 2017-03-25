package org.vp.prep.mt;

class Stack {

	private int size;
	private int[] storage;
	private int top = -1;
	private final Object lock = new Object();
	
	public Stack(int size){
		this.size =size;
		this.storage = new int[size];
	}
	
	public void push(int val){
		synchronized (lock) {
			try{
				if(top==size-1){
					System.out.println("In push:Q full.. hence blocking");
					lock.wait();
				}
			}catch(InterruptedException e){
			}
			top++;
			storage[top]=val;
			System.out.println("Pushed:"+val);
			lock.notifyAll();
		}
	}
	
	public int pop(){
		synchronized (lock) {
			try{
				if(top==-1){
					System.out.println("In pop:Q empty.. hence blocking");
					lock.wait();
				}
			}catch(InterruptedException e){
				
			}
			int val = storage[top];
			top--;
			System.out.println("Popped:"+val);
			lock.notifyAll();
			return val;
		}
	}
}

class StackUser implements Runnable {
	Stack tsq = new Stack(5);
	public void run(){
		for(int i=0;i<20;i++){
			try{
				if(Thread.currentThread().getName().equals("push")){
					tsq.push(i);
				}else{
					tsq.pop();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}

class ThreadSafeStack{
	public static void main(String args[]) {
		try{
			StackUser q = new StackUser();
			Thread pusher = new Thread(q,"push");
			Thread popper  = new Thread(q,"pop");
			System.out.println("Starting threads:");
			popper.start();
			pusher.start();
			popper.join();
			pusher.join();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}