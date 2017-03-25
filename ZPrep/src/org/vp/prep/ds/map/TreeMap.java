package org.vp.prep.ds.map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author vpathak
 *
 *	Does not use hashCode and equals. Uses Comparable or provided Comparator. 
 *	If 2 different objects are equal as per comparator/comparable, 
 *	and we put them one after other in map, the later will replace the former irrespective of different hashes
 *
 */
public class TreeMap<K,V> implements Map<K,V>{
	private final Comparator<K> comparator;
	
	public TreeMap(){
		this.comparator = null;
	}
	
	public TreeMap(Comparator<K> comparator) {
		super();
		this.comparator = comparator;
	}

	static class Entry<K,V>{
		private K key;
		private V value;
		Entry<K,V> left;
		Entry<K,V> right;
		public Entry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
			this.left = null;
			this.right = null;
		}
		@Override
		public String toString() {
			return "["+key+", "+value+"]";
		}
	}

	Entry<K,V> root = null;
	
	public void put(K key, V value){
		Entry<K,V> entry = new Entry<K,V>(key, value);
		insert(entry);
	}
	
	public V get(K key){
		Entry<K,V> tmp = root;
		while(tmp !=null){
			if(comparator.compare(key, tmp.key)<0){
				tmp = tmp.left;
			}else if(comparator.compare(key, tmp.key)>0){
				tmp = tmp.right;
			}else{
				return tmp.value;
			}
		}
		return null;
	}
	
	private void insert(Entry<K,V> node){
		if(root == null){
			root = node;
			return;
		}
		Entry<K,V> prev = null;
		Entry<K,V> tmp = root;
		while(tmp!=null){
			prev = tmp;
			if(comparator.compare(node.key, tmp.key)<0){
				tmp = tmp.left;
			}else if((comparator.compare(node.key, tmp.key)>0)){
				tmp = tmp.right;
			}else{
				tmp.value = node.value;
				return;
			}
		}
		if(comparator.compare(node.key, prev.key)<0){
			prev.left = node;
		}else{
			prev.right = node;
		}
	}
	
	
	@Override
	public String toString() {
		List<Entry<K,V>> nodes = new ArrayList<>(); 
				traverseInorder(root, nodes);
		return nodes.toString();
	}

	private void traverseInorder(Entry<K,V> node, List<Entry<K,V>> nodes){
		if(node == null){
			return;
		}
		traverseInorder(node.left, nodes);
		nodes.add(node);
		traverseInorder(node.right, nodes);
	}
	
	public static void main(String[] args) {
		Comparator<String> comparator = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};
		Map<String, Integer> tm = new TreeMap<String, Integer>(comparator);
		tm.put("b", 3);
		tm.put("a", 1);
		tm.put("g", 2);
		tm.put("c", 5);
		tm.put("c", 4);
		System.out.println(tm);
		System.out.println(tm.get("c"));
		System.out.println(tm.get("n"));
	}
	
}
