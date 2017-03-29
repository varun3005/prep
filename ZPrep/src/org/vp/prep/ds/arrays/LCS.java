package org.vp.prep.ds.arrays;

/**
 * 
 * @author vpathak
 *  
 * Lowest common subsequence, using recursion, and recursion with memoization and using db
 * Input 2 Arrays 
 * A = 1, 2, 3, 1, 6, 2, 8 
 * B = 2, 1, 3, 5, 6, 2 
 * Output 4 which is length of longest common subsequence   2,3,6,2 / 1,3,6,2
 * 
 * To get Longest Bitonic sequence in A :1,2,3,6,2 i.e. Longest sequence of first increasing and then decreasing numbers 
 * 1) Create B which is A sorted in ascending order 
 * 2) Find LCS of A, B - this gives longest increasing sequence 
 * 3) Create C which is A sorted in desc order 
 * 4) Find LCS of A, C - this is longest decreasing sequence 
 * 5) Longest bitonic sequence = results of 2 + 4 - 1.
 *
 */
public class LCS {

	public static void main(String[] args) {
		int[] a = new int[] { 1, 2, 3, 1, 6, 2, 8 };
		int[] b = new int[] { 2, 1, 3, 5, 6, 2 };
		System.out.println("Length of longest common subsequence:" + lcs(a, b, a.length, b.length));

		int[][] c = new int[a.length + 1][b.length + 1];
		for (int i = 0; i < a.length + 1; i++) {
			for (int j = 0; j < b.length + 1; j++) {
				c[i][j] = -1;
			}
		}
		
		System.out.println("LCS-MEM:" + lcsMem(a, b, c, a.length, b.length));

		System.out.println("Lcs using dp:" + lcsDp("1,2,3,1,6,2,8", "2,1,3,5,6,2"));
	}

	/*
	 * Using recurrence
	 */
	private static int lcs(int[] a, int[] b, int m, int n) {
		if (m == 0 || n == 0) {
			return 0;
		}
		if (a[m - 1] == b[n - 1]) {
			return 1 + lcs(a, b, m - 1, n - 1);
		} else {
			return Math.max(lcs(a, b, m - 1, n), lcs(a, b, m, n - 1));
		}
	}

	/*
	 * Using recurrence and memoization
	 */
	private static int lcsMem(int[] a, int[] b, int[][] c, int m, int n) {
		if (c[m][n] > -1) {
			return c[m][n];
		}
		if (m == 0 || n == 0) {
			c[m][n] = 0;
		} else if (a[m - 1] == b[n - 1]) {
			c[m][n] = 1 + lcsMem(a, b, c, m - 1, n - 1);
		} else {
			c[m][n] = Math.max(lcsMem(a, b, c, m - 1, n), lcsMem(a, b, c, m, n - 1));
		}
		return c[m][n];
	}

	/*
	 * using dynamic programming
	 */
	public static String lcsDp(String a, String b) {
		int[][] c = new int[a.length() + 1][b.length() + 1];

		// row 0 and column 0 are initialized to 0 already

		for (int i = 0; i < a.length(); i++) {
			for (int j = 0; j < b.length(); j++) {
				if (a.charAt(i) == b.charAt(j)) {
					c[i + 1][j + 1] = c[i][j] + 1;
				} else {
					c[i + 1][j + 1] = Math.max(c[i + 1][j], c[i][j + 1]);
				}
			}
		}
		System.out.println(c[a.length()][b.length()]);
		// read the substring out from the matrix
		StringBuffer sb = new StringBuffer();
		for (int x = a.length(), y = b.length(); x != 0 && y != 0;) {
			if (c[x][y] == c[x - 1][y])
				x--;
			else if (c[x][y] == c[x][y - 1])
				y--;
			else {
				assert a.charAt(x - 1) == b.charAt(y - 1);
				sb.append(a.charAt(x - 1));
				x--;
				y--;
			}
		}
		return sb.reverse().toString();
	}

}
