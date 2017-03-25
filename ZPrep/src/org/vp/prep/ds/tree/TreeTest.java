package org.vp.prep.ds.tree;

/**
 * 
 * @author vpathak
 *
 */

//
//5
//3  8
//1 4 7 9


public class TreeTest {
	
	Node root = null;
	
	public void insertMulti(int[] a){
		for(int el:a){
			insertNode(el);
		}
	}

	public void insertNode(int n){
		if(root == null ){
			root = new Node(n, null, null);
			return;
		}
		Node tmp = root;
		Node prv = null;
		while(tmp!=null){
			prv = tmp;
			if(n<tmp.data){
				tmp = tmp.left;
			}else{
				tmp = tmp.right;
			}
		}
		if(n<prv.data){
			prv.left = new Node(n,null,null);
		}else{
			prv.right = new Node(n,null,null);
		}
	}
	
	public void inorder(Node n){
		if(n!=null){
			inorder(n.left);
			System.out.print(n.data+",");
			inorder(n.right);
		}
	}
	
	public void preorder(Node n){
		if(n!=null){
			System.out.print(n.data+",");
			preorder(n.left);
			preorder(n.right);
		}
	}
	
	public void postorder(Node n){
		if(n!=null){
			postorder(n.left);
			postorder(n.right);
			System.out.print(n.data+",");
		}
	}
	
	public boolean search(int n){
		if(root == null){
			return false;
		}
		Node tmp = root;
		while(tmp!=null){
			if(tmp.data==n){
				return true;
			}else if(n<tmp.data){
				tmp = tmp.left;
			}else{
				tmp = tmp.right;
			}
		}
		return false;
	}
	
	public static int maxDepth(Node n){
		if(n==null){
			return 0;
		}
		int l = maxDepth(n.left);
		int r = maxDepth(n.right);
		if(l>r){
			return l+1;
		}else{
			return r+1;
		}
	}
	
	static int diameterOfTree(Node root) {
		int l=0,r=0;
		if(root.left!=null){
			l= maxDepth(root.left);
		}
		if(root.right!=null){
			r= maxDepth(root.right);
		}
		return l+r+1;
	}
	
	public static void main(String[] args) {
		TreeTest tt = new TreeTest();
//		  5
//	  3  		9
// 2	 4	  6	  11
//			   7	
		int[] a = {5,3,4,2,9,6,7,11};
		tt.insertMulti(a);
//		tt.inorder(tt.root);
//		System.out.println("Max Depth:"+tt.maxDepth(tt.root));
		System.out.println(diameterOfTree(tt.root));
//		System.out.println(tt.search(5));
	}
}
