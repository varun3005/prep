package org.vp.prep.math;

import java.math.BigDecimal;

public class LongAddition {

	public static void main(String[] args) {
		int a[] = new int[] { 7, 9, 8, 6, 5, 6, 5, 7, 8, 4, 5, 3, 6, 8, 1, 2, 0, 0, 0 };
		int b[] = new int[] { 2, 2, 4, 2, 1, 6, 7, 8, 4, 9, 3, 9, 0, 8, 7, 2, 3, 7, 8 };
		BigDecimal bda = new BigDecimal("7986565784536812000");
		BigDecimal bdb = new BigDecimal("2242167849390872378");

		int c[];
		if (b.length > a.length) {
			c = new int[b.length + 1];
			sum(b, a, c);
		} else {
			c = new int[a.length + 1];
			sum(a, b, c);
		}
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i]);
		}
		System.out.println("\nVerification:\n" + bda.add(bdb));
	}

	private static void sum(int[] a, int[] b, int c[]) {
		int i, j, k, carry = 0;
		for (i = a.length - 1, j = b.length - 1, k = c.length - 1; i > -1 && j > -1; i--, j--, k--) {
			int sum = a[i] + b[j] + carry;
			c[k] = sum % 10;
			carry = sum / 10;
		}
		c[0] = carry;
	}

}
