package org.vp.prep.ds.set;

public class HashSet<T> implements Set<T>{
	
	private Entry<T> buckets[] = null;
	private int capacity;
	
	public HashSet(int capacity) {
		this.capacity = capacity;
		buckets = new Entry[capacity];
	}
	
	public void add(T element) {
		int idx = element.hashCode() % capacity;
		Entry<T> entry = new Entry(element);
		if(buckets[idx] == null) {
			buckets[idx] = entry;
			return;
		}
		Entry<T> tmp = buckets[idx];
		if(tmp.val.equals(element)) {
			return;
		}
		while(tmp.next !=null) {
			if(tmp.val.equals(element)) {
				return;	// No need to insert if 
			}
			tmp = tmp.next;
		}
		tmp.next = entry;
	}
	
	public boolean contains(T element) {
		int idx = element.hashCode() % capacity;
		Entry<T> tmp = buckets[idx];
		if(tmp == null) {
			return false;
		}
		if(tmp.val.equals(element)) {
			return true;
		}
		while(tmp.next !=null) {
			if(tmp.val.equals(element)) {
				return true;
			}
			tmp = tmp.next;
		}
		return false;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<capacity;i++) {
			Entry<T> tmp = buckets[i];
			while(tmp != null) {
				sb.append(tmp.val.toString()).append(" ,");
				tmp = tmp.next;
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		HashSet<Integer> set = new HashSet<>(10);
		set.add(3);
		set.add(4);
		set.add(1);
		set.add(15);
		set.add(11);
		set.add(3);
		System.out.println(set);
		System.out.println(set.contains(15)+" - "+set.contains(9));
		
	}
 
}
