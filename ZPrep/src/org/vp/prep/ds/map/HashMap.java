package org.vp.prep.ds.map;

public class HashMap<K,V> implements Map<K,V>{
	
	public int capacity = 16;

	static class Entry<K,V>{
		private K key;
		private V value;
		Entry<K,V> next;
		public Entry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
			this.next = null;
		}
		
	}
	
	private Entry<K,V>[] map = new Entry[capacity]; 
	
	public V get(K key){
		int index = key.hashCode()%capacity;
		if(map[index] == null){
			return null;
		}
		Entry<K,V> entry = map[index];
		if(entry.key.equals(key)){
			return entry.value;
		}
		 while (entry.next != null) {
			entry = entry.next;
			if (entry.key.equals(key)) {
				return entry.value;
			}
			
		}
		return null;
	}
	
	public void put (K key, V value){
		int index = key.hashCode()%capacity;
		if(map[index]==null){
			map[index] = new Entry<K,V>(key,value);
			return;
		}
		Entry<K,V> entry = map[index];
		while(entry.next!=null){
			if(entry.key.equals(key)){
				entry.value = value;
				return;
			}
			entry = entry.next;
		}
		entry.next = new Entry<K,V>(key, value);
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i <map.length;i++){
			Entry<K,V> temp = map[i];
			while(temp!=null){
				sb.append("["+temp.key+", "+temp.value+"], ");
				temp = temp.next;
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("A", "B");
		map.put("B", "C");
		map.put("C", "D");
		map.put("1", "B");
		map.put("2", "C");
		map.put("3", "D");
		map.put("33", "D");
		map.put("a", "D");
		map.put("bb", "D");
		System.out.println(map);
		System.out.println(map.get("a"));
		System.out.println(map.get("abc"));
	}
}
