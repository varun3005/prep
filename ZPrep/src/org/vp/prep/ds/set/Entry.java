package org.vp.prep.ds.set;

public class Entry<T> {
	T val;
	Entry next;
	
	public Entry(T val) {
		this.val = val;
		this.next = null;
	}
}
