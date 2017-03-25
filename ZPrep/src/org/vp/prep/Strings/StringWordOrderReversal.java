package org.vp.prep.Strings;
/**
 * 
 * @author vpathak
 * Reverse the order of all words in string without reversing individual words.
 * Approach : Reverse each word, then reverse the full sentence
 *
 */
public class StringWordOrderReversal {

	public static void main(String args[]){
		StringBuffer sb = new StringBuffer("A quick little fox jumped over the lazy dog");
		StringWordOrderReversal as = new StringWordOrderReversal();
		as.reverse(sb);
		System.out.println(sb);
	}
	
	public void reverse(StringBuffer a){
		int start=0,end=0;
		for(int i=0;i<a.length();i++){
			if(a.charAt(i)==' ' || a.charAt(i)=='\n' || i==a.length()-1){
				if(i == a.length()-1){
					end = i;
				}else{
					end =i-1;
				}
				reverseWord(a,start,end);
				start = i+1;
			}
		}
		reverseWord(a, 0, a.length()-1);
	}

	private void reverseWord(StringBuffer a, int start, int end) {
		for(int i=start,j=end;i<j;i++,j--){
			char temp = a.charAt(i);
			a.setCharAt(i, a.charAt(j));
			a.setCharAt(j, temp);
		}
	}
	
}
