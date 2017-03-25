package org.vp.prep.math;

/**
 * 
 * @author vpathak
 * For each index, generate a random index and swap value with that.
 * Tn = O(n)
 *
 */

public class Shuffle {
	public static void main(String args[]){
		int a[] = new int[]{1,2,3,4,5,6,7,8,9,10};
		Shuffle s = new Shuffle();
		s.shuffle(a);
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+", ");
		}
	}

	private void shuffle(int[] a) {
		for(int i=0;i<a.length;i++){
			int rand = (int) (Math.random()*100)%(a.length-1);
			int temp = a[i];
			a[i]= a[rand];
			a[rand] = temp;
		}
		
	}
	
	
}
