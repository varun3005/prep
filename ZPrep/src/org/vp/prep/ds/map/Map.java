package org.vp.prep.ds.map;

public interface Map<K,V> {
	public V get(K key);
	public void put(K key, V value);
}
