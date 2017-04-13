package org.vp.prep.ds.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author vpathak
 *
 * 1) Path and distance between 2 nodes
 * 2) Get all nodes without a sibling
 * 3) Print width of the tree - Max path between two nodes
 */

public class Tree2 implements Serializable{
	private static final long serialVersionUID = 1L;
	Node root = null;
	
	@Override
	public String toString() {
		return "Tree [root=" + root + "]";
	}

	public void insert(int info){
		if(root == null){
			root = new Node(info);
		}else{
			Node tmp = root;
			Node prev = null;
			while(tmp!=null){
				prev = tmp;
				if(info < tmp.data){
					tmp = tmp.left;
				}else {
					tmp = tmp.right;
				}
			}
			if(info<prev.data){
				prev.left = new Node(info);
			}else{
				prev.right = new Node(info);
			}
		}
	}
	
	public void insertMulti(int[] array){
		for(int i =0;i<array.length;i++){
			insert(array[i]);
		}
	}
	
	public void inorder(Node n){
		if(n == null){
			return;
		}else{
			inorder(n.left);
			System.out.print(+n.data+",");
			inorder(n.right);
		}
	}
	
	public void nodeWithoutSiblings(Node n,  List<Node> nodes){
		if(n == null){
			return;
		}else{
			nodeWithoutSiblings(n.left, nodes);
			if(n.left== null && n.right!=null){
				 nodes.add(n.right);
			}
			if(n.left!= null && n.right==null){
				 nodes.add(n.left);
			}
			nodeWithoutSiblings(n.right, nodes);
		}
	}
	
	private List<Integer> findNodePath(int n, Node root,List<Integer> path) {
		if(root==null)
			return path;
		path.add(root.data);
		if(n < root.data){
			findNodePath(n, root.left, path);
		}else if(n > root.data){
			findNodePath(n, root.right, path);
		}
		return path;
	}
	
	// Print all nodes distance k away from leaf node
	public void nodesFromLeaf(Node n, int k){
		
	}
	
	public void pathBetweenTwoNodes(int n1, int n2){ // Find Common ancestor of 2 nodes
		
		List<Integer> n1Path = findNodePath(n1,root,new ArrayList<>());
		List<Integer> n2Path = findNodePath(n2,root, new ArrayList<>());
		//System.out.println(n1Path + " - "+n2Path);
		int minLen = n1Path.size()<n2Path.size()?n1Path.size():n2Path.size();
		int ancesstor = -1;
		for(int i=0;i<minLen;i++){
			if(n1Path.get(i)==n2Path.get(i)){
				ancesstor = n1Path.get(i);
			}
		}
		System.out.println("\nCommon Ancesstor for "+n1+" and "+n2+" = "+ancesstor);
		int len1 = n1Path.size()-1 -(n1Path.indexOf(ancesstor));
		int len2 = n2Path.size()-1 -(n2Path.indexOf(ancesstor));
		System.out.println("Node distance between 2 nodes:"+(len1+len2+1));
	}
	
	static class HW{
		int height;
		int width;
		public HW(int h, int w){
			height = h;
			width = w;
		}
		@Override
		public String toString() {
			return "HW [height=" + height + ", width=" + width + "]";
		}
	}
	
	/**
	 * 
	 * @param a
	 * @return
	 * 
	 * At each node return its height = max(leftHeight, rightHeight) + 1
	 * Use that to evaluate width = leftHeigt + rightHeight +1 
	 */
	public HW treeWidth(Node a){
		if(a==null){
			return new HW(0,0);
		}
		HW lHW = treeWidth(a.left);
		HW rHW = treeWidth(a.right);
		int height = Math.max(lHW.height, rHW.height)+1;
		int width = lHW.height+rHW.height+1;
		return new HW(height, width);
	}
	
	public void updateNodeInfo(Node a, int current, int newVal) {
		if (a == null) {
			return;
		}
		if (a.data == current) {
			a.data = newVal;
			return;
		}
		if (current < a.data) {
			updateNodeInfo(a.left, current, newVal);
		} else {
			updateNodeInfo(a.right, current, newVal);
		}
	}
	
	boolean isBsT(Node node, int min, int max) {
		/* an empty tree is BST */
		if (node == null)
			return true;

		/* false if this node violates the min/max constraints */
		if (node.data < min || node.data > max)
			return false;

		/*
		 * otherwise check the subtrees recursively tightening the min/max
		 * constraints
		 */
		// Allow only distinct values
		return (isBsT(node.left, min, node.data - 1) && isBsT(node.right, node.data + 1, max));
	}
		
	public static void main (String args[]){
		System.out.println("Test");
		Tree2 tree = new Tree2();
		tree.insertMulti(new int[]{5,3,4,2,9,6,7,11});
//		5
//	3  		9
//2	  4	  6	  11
//	  	    7	
		tree.inorder(tree.root);
		tree.pathBetweenTwoNodes(2, 7);
		System.out.println("\nPath:"+tree.findNodePath(7, tree.root,new ArrayList<>()));
		List<Node> nodes =  new ArrayList<>();
		tree.nodeWithoutSiblings(tree.root,nodes);
		System.out.println("Nodes wihtout sibling : "+nodes);
		System.out.println(tree.treeWidth(tree.root));
		tree.updateNodeInfo(tree.root, 7, 50);
		tree.inorder(tree.root);
		System.out.println("\nIsBst:"+tree.isBsT(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE));
	}
}
