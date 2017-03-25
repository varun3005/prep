package org.vp.prep.ds;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author vpathak
 *
 */

public class Trie {
	class Node{
		private char letter;
		private boolean isWord;
		private List<Node> children;
		
		public Node(char c,boolean isWord){
			letter = c;
			this.isWord = isWord;
			children = new LinkedList<Node>();
		}

		@Override
		public String toString() {
			return letter+"-"+isWord+" ";
		}

	}
	
	Node root;
	
	Trie(){
		root = new Node('#',false);
	}
	
	public boolean containsNode(List<Node> list,char c){
		Iterator<Node> it = list.iterator();
		while(it.hasNext()){
			Node current = it.next();
			if(current.letter==c){
				return true;
			}
		}
		return false;
	}
	
	public Node getNode(Node node,char c){
		List<Node> list = node.children;
		Iterator<Node> it = list.iterator();
		while(it.hasNext()){
			Node current = it.next();
			if(current.letter==c){
				return current;
			}
		}
		return null;
	}
	
	public void addWord(Node root,char[] word){
		Node tmp = null;
		if(!containsNode(root.children,word[0])){
			tmp = new Node(word[0],word.length==0);
			root.children.add(tmp);
		}else{
			tmp = getNode(root,word[0]);
		}
		if(word.length>1){
			for(int i=1;i<word.length;i++){
				if(!containsNode(tmp.children,word[i])){
					Node childNode = new Node(word[i],word.length ==(i+1));
					tmp.children.add(childNode);
					tmp = childNode;
				}else{
					tmp = getNode(tmp,word[i]);
				}
			}
		}
	}
	
	public void display(Node root) { // DFS
		Node tmp = root;
		if (tmp.children == null) {
			return;
		}
		for (Node a : tmp.children) {
			System.out.println(a);
			display(a);
		}
	}
	
	public void displayWords(Node root, char[] word, int depth, char[] prefix) {
		Node tmp = root;
		if (tmp.children == null) {
			return;
		}
		for (Node a : tmp.children) {
			word[depth] = a.letter;
			if (a.isWord) {
				for (char c : prefix) {
					System.out.print(c);
				}
				for (char c : word) {
					if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
						System.out.print(c);
					}
				}
				System.out.println("");
			}
			displayWords(a, word, depth + 1, prefix);
		}
	}
	
	public void displayWordsWithPrefix(Node root, char[] prefix, int depth) {
		Node tmp = root;
		if (tmp.children == null) {
			return;
		}
		for (Node a : tmp.children) {
			if (depth < prefix.length) {
				if (a.letter == prefix[depth]) {
					displayWordsWithPrefix(a, prefix, depth + 1);
				}
			} else {
				displayWords(tmp, new char[10], 0, prefix);
				break;
			}
		}
	}
	
	
	public static void main(String args[]){
		Trie trie = new Trie();
		trie.addWord(trie.root, new char[]{'a','n'});
		trie.addWord(trie.root, new char[]{'a','n','t'});
		trie.addWord(trie.root, new char[]{'a','n','d'});
		trie.addWord(trie.root, new char[]{'a','n','d','y'});
		trie.addWord(trie.root, new char[]{'a','x','e'});
		trie.addWord(trie.root, new char[]{'c','e','l','l'});
		trie.addWord(trie.root, new char[]{'c','e','n','t'});
		trie.display(trie.root);
		System.out.println("\nDisplaying words\n");
		trie.displayWords(trie.root, new char[10], 0, new char[]{});
		System.out.println("\n Prefix Search \n");
		trie.displayWordsWithPrefix(trie.root, new char[]{'a','n'}, 0);
		
	}
}
