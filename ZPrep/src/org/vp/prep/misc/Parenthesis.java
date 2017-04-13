package org.vp.prep.misc;

public class Parenthesis {
	static int a = 0;

	public static void main(String[] args) {
		int parenNum = 3;
		char str[] = new char[2*parenNum];
		printPar(parenNum, parenNum, str, 0);
	}

	public static void printPar(int l, int r, char[] str, int count) {
		if (l < 0 || r < l)
			return; // invalid state
		if (l == 0 && r == 0) {
			System.out.println(str); // found one, so print it
		} else {
			if (l > 0) { // try a left paren, if there are some available
				str[count] = '(';
				printPar(l - 1, r, str, count + 1);
			}
			if (r > l) { // try a right paren, if there's a matching left
				str[count] = ')';
				printPar(l, r - 1, str, count + 1);
			}
		}
	}

}
