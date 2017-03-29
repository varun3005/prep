package org.vp.prep.math;

import java.util.Arrays;

/**
 * 
 * for Explanation
 * :http://exceptional-code.blogspot.in/2012/09/generating-all-permutations.html
 *
 */

public class Permutations {
	
	public static void main(String[] args) {
		char[] arr = new char[]{'A', 'B', 'C'};
		int n = arr.length;
		boolean[] visited = new boolean[n];
		char[] branch = new char[n];
		Permutations p = new Permutations();
		p.generatePermutations(arr, n, branch, -1, visited);
		
		System.out.println("\n Generating all combinations - Power set");
		for (int i = 0; i < arr.length; ++i)
	    {
	        branch = new char[i];
	        p.combine(arr, i, 0, branch, 0);
	    }   
	}

	void generatePermutations(char[] arr, int size, char[] branch, int level, boolean[] visited) {
		if (level >= size - 1) {
			System.out.println(branch);
			return;
		}
		for (int i = 0; i < size; i++) {
			if (!visited[i]) {
				branch[++level] = arr[i];
				visited[i] = true;
				generatePermutations(arr, size, branch, level, visited);
				visited[i] = false;
				level--;
			}
		}
	}
	
	void combine(char[] arr, int k, int startId, char[] branch, int numElem)
	{
	    if (numElem == k)
	    {
	        System.out.println(Arrays.toString(branch));
	        return;
	    }
	   
	    for (int i = startId; i < arr.length; ++i)
	    {
	        branch[numElem++] = arr[i];
	        combine(arr, k, ++startId, branch, numElem);
	        --numElem;
	    }
	}

}
