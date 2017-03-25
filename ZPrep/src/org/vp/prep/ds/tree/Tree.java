package org.vp.prep.ds.tree;

import java.io.Serializable;

/**
 * 
 * @author vpathak
 * 
 * Includes following
 * 1) Get mirror image of binary tree
 * 2) Search
 * 3) Print nth smallest node in the tree
 * 4) Print Nth largest node in the tree
 * 5) Find common ancestor for 2 nodes
 * 6) Print all leaf paths
 * 7) Is tree a BST
 * 8) If a path exist where sum of all nodes in path=x
 * 9) BFS traversal
 */

public class Tree implements Serializable{
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
	
	public void mirror(Node n){				// Transform tree into its mirror image
		if(n!=null){
			mirror(n.left);
			mirror(n.right);
			Node tmp = n.left;
			n.left = n.right;
			n.right = tmp;
		}
	}
	
	
	
	public boolean search(Node n,int key){		// Search for a node in tree
		while(n!=null){
			if(n.data==key){
				return true;
			}else if(key<n.data){
				n = n.left;
			}else{
				n = n.right;
			}
		}
		return false;
	}
	
	public void findNthNode(Node n, IntHolder k){ // Print nth smallest node in the tree
		if(n==null)
			return ;
		findNthNode(n.left, k);
		k.val--;
		if(k.val==0){
			System.out.println(n.data);
		}
		findNthNode(n.right, k);
	}
	
	public void findNthLargestNode(Node n, IntHolder k){ // Print Nth largest node in the tree
		if(n==null)
			return ;
		findNthLargestNode(n.right, k);
		k.val--;
		if(k.val==0){
			System.out.println(n.data);
		}
		findNthLargestNode(n.left, k);
	}
	
	public Node ntLargesNode(Node n,IntHolder k){	// return Nth largest node in the tree
		if(n==null){
			return null;
		}
		Node temp = ntLargesNode(n.right,k);
		if(temp!=null){
			return temp;
		}
		k.val = k.val-1;
		if(k.val==0){
			return n;
		}
		return ntLargesNode(n.left,k);
	}
	
	public void findCommonAncesstor(int n1, int n2){ // Find Common ancestor of 2 nodes
		
		StringBuilder n1Path = findNodePath(n1,root,new StringBuilder(""));
		StringBuilder n2Path = findNodePath(n2,root, new StringBuilder(""));
		//System.out.println(n1Path + " - "+n2Path);
		int minLen = n1Path.length()<n2Path.length()?n1Path.length():n2Path.length();
		char ancesstor = '0';
		for(int i=0;i<minLen;i++){
			if(n1Path.charAt(i)==n2Path.charAt(i)){
				ancesstor = n1Path.charAt(i);
			}
		}
		System.out.println("Common Ancesstor for "+n1+" and "+n2+" = "+ancesstor);
	}
	
	private StringBuilder findNodePath(int n, Node root,StringBuilder path) {
		if(root==null)
			return path;
		path=path.append(root.data);
		if(n < root.data){
			findNodePath(n, root.left, path);
		}else if(n > root.data){
			findNodePath(n, root.right, path);
		}
		return path;
	}
	
	public void printAllLeafPaths(Node n, int path[],int pathlen){ // Print all paths from root to each of the leaf
		if(n==null){
			return;
		}
		path[pathlen++] = n.data;
		if(n.left==null && n.right==null){ //  n is leaf
			for(int i=0;i<pathlen;i++){
				System.out.print(path[i]+" , ");
			}
			System.out.println();
		}
		printAllLeafPaths(n.left, path, pathlen);
		printAllLeafPaths(n.right, path, pathlen);
	}

	
	boolean isBST(Node n, IntHolder prev) { // To test weather a tree is a Binary Search Tree
		if (n == null)
			return true;
		if (isBST(n.left, prev)) {
			if (n.data > prev.val) {
				prev.val = n.data;
				return isBST(n.right, prev);
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	boolean hasSum(Node n, int sum){ // To test weather a path exist from root to leaf that has sum equal to passed value
		if(n==null){
			return (sum==0);
		}
		sum = sum - n.data;
		return (hasSum(n.left,sum)||hasSum(n.right,sum));
	}
	
	public void printBfs(){
		if(root==null){
			return;
		}
		Queue q = new Queue();
		q.queue(root);
		while(!q.isEmpty()){
			Node tmp = q.dequeue();
			System.out.print(tmp.data+" ");
			if(tmp.left!=null){
				q.queue(tmp.left);
			}
			if(tmp.right!=null){
				q.queue(tmp.right);
			}
		}
	}
	
	
	public void printSpiral(Node n,int level){
		if(root==null){
			return;
		}
		Queue q = new Queue();
		q.queue(root);
		while(!q.isEmpty()){
			Node tmp = q.dequeue();
			System.out.print(tmp.data+" ");
			if(level%2==0){
				if(tmp.right!=null){
					q.queue(tmp.right);
				}
				if(tmp.left!=null){
					q.queue(tmp.left);
				}
			}else{
				if(tmp.left!=null){
					q.queue(tmp.left);
				}
				if(tmp.right!=null){
					q.queue(tmp.right);
				}
			}
			level ++;
		}
	}
	
	public static void main (String args[]){
		Tree tree = new Tree();
		tree.insertMulti(new int[]{5,3,4,2,9,6,7,11});
//		tree.insertMulti(new int[]{7,4,10,2,5,3,8,12,11,15});
//							5
//						3  		9
//					  2	  4	  6	  11
//		 					   7	
//		tree.printBfs();
//		tree.inorder(tree.root);System.out.println();
//		System.out.println("\n"+tree.search(tree.root, 8));
//		tree.mirror(tree.root);tree.inorder(tree.root);
		tree.inorder(tree.root);
		System.out.println();
		tree.findNthNode(tree.root, new IntHolder(2));
		tree.findNthLargestNode(tree.root, new IntHolder(2));
		Node node = tree.ntLargesNode(tree.root, new IntHolder(3));
		System.out.println(node);
//		IntHolder rank = new IntHolder(5); // since int cannot be passed by ref and Integer is immutable
//		System.out.println("\n"+rank.val+"th rank node:");
//		tree.findNthNode(tree.root, rank);
//		System.out.println("\nNth largest node:");
//		tree.findNthNode(tree.root, new IntHolder(5));
//		tree.findCommonAncesstor(11, 7);
//		System.out.println("\nPrinting all leaf paths of tree\n");
//		tree.printAllLeafPaths(tree.root, new int[20], 0);
//		System.out.println("\nIsBST:"+tree.isBSTInOrder(tree.root, new IntHolder(-1)));
//		System.out.println("\nHas sum:"+tree.hasSum(tree.root, 27));
//		Node n = tree.ntLargesNode(tree.root, new IntHolder(2));
//		System.out.println("\n:"+n.info);
//		tree.printSpiral(tree.root, 0);
		
	}
}
