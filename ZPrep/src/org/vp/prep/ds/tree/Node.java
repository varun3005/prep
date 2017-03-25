package org.vp.prep.ds.tree;

public class Node{
	int data;
	Node left;
	Node right;
	
	Node(int info){
		this.data = info;
		this.left = null;
		this.right = null;
	}
	
	public Node(int data, Node left, Node right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}



	@Override
	public String toString() {
		return "Node [info=" + data + "]";
	}
	
	
}
