package org.vp.prep.javaConcepts;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapExample { 	// Hash map can prevent an object from gc when no other references 
									//exists as its key itself holds a strong ref to that object. WHM avoids that.
	public static void main(String[] args) {
		Map<String, String> weakHashMap = new WeakHashMap<String, String>();
		// Create a key for the map, keep the strong reference
		String storngRefrence = new String("key");
		weakHashMap.put(storngRefrence, "value"); // Run the GC and check if the
													// key is still there.
		System.gc();
		System.out.println(weakHashMap.get("key")); // Now, null out the strong
													// reference and try again
													// the same above.
		storngRefrence = null;
		System.gc();
		System.out.println(weakHashMap.get("key"));
		
		
		Map<String, String> hMap = new HashMap<String, String>();
		// Create a key for the map, keep the strong reference
		String ref = new String("key");
		hMap.put(ref, "value"); // Run the GC and check if the
													// key is still there.
		System.gc();
		System.out.println(hMap.get("key")); // Now, null out the strong
													// reference and try again
													// the same above.
		ref = null;
		System.gc();
		System.out.println(hMap.get("key"));
	}

}
