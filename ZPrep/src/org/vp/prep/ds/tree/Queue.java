package org.vp.prep.ds.tree;

/**
 * 
 * @author vpathak
 *
 */

public class Queue {
	
	private static final int MAX = 100;
	private Node[] info = new Node[100];
	private int end = 0;
	private int start = 0;
	
	public boolean queue(Node i){
		if(end==MAX){
			System.out.println("Overflow");
			return false;
		}
		info[end++]=i;
		return true;
	}
	
	public Node dequeue(){
		if(start == end){
			System.out.println("Underflow");
			if(start!=0){
				start = 0;
				end = 0;
			}
			return null;
		}
		return info[start++];
	}
	
	public void print(){
		for(int i=start;i<end;i++){
			System.out.print(info[i].data+",");
		}
		System.out.println();
	}
	
	public boolean isEmpty(){
		if(start==end){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Queue q = new Queue();
		q.queue(new Node(1));
		q.queue(new Node(2));
		q.print();
		q.dequeue();
		q.print();
		System.out.println(q.isEmpty());
		q.dequeue();
		System.out.println(q.isEmpty());
	}

}
