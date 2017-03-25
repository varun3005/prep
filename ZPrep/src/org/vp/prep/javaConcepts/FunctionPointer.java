package org.vp.prep.javaConcepts;
import java.util.HashMap;
import java.util.Map;

public class FunctionPointer {
	interface func {
		int fun(int x, int y);
	}
	static func add = new func() {
		public int fun(int x, int y) {
			return x + y;
		}
	};
	static func mult = new func() {
		public int fun(int x, int y) {
			return x * y;
		}
	};
	public static void main(String[] args) {
		
		
		Map<String,func> functionMap = new HashMap<String,func>();
		functionMap.put("add", add);
		functionMap.put("mult", mult);
		
		System.out.println(functionMap.get("add").fun(10, 20));
	}
}