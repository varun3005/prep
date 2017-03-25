package org.vp.prep.misc;

public class NQueen {
	final int numQueen = 8;
	int board[][] = new int[numQueen][numQueen];

	public void display() {
		for (int i = 0; i < numQueen; i++) {
			System.out.print("");
			for (int j = 0; j < numQueen; j++) {
				System.out.print(board[i][j] + "|");
			}
			System.out.println();
		}
	}

	public void scanAndPlaceQueen(int row, int col) {
		if (row == numQueen) {
			if (col == 0) {
				display();
				System.out.println("\n---------");
			}
			return;
		}

		int i, j;
		for (j = 0; j < col; j++) { // Horizontal check
			if (board[row][j] == 1) {
				return;
			}
		}

		for (i = 0; i < row; i++) { // vertical check
			if (board[i][col] == 1) {
				return;
			}
		}

		i = row;
		j = col;
		do { // top left to middle diagnal check
			if (board[i--][j--] == 1) {
				return;
			}
		} while (i > -1 && j > -1);

		i = row;
		j = col; // top right to middle diagnal check
		do {// System.out.println(i+" - "+j);
			if (board[i--][j++] == 1) {
				return;
			}
		} while (i > -1 && j < numQueen - 1);

		board[row][col] = 1;
		for (j = 0; j < numQueen; j++) {
			scanAndPlaceQueen((row + 1), j);
		}
		board[row][col] = 0; // Rollback

	}

	public static void main(String args[]) {
		NQueen q = new NQueen();
		if (q.numQueen < 4) {
			System.out.println("No Solution..!!");
			return;
		}
		for (int i = 0; i < q.numQueen; i++) {
			q.scanAndPlaceQueen(0, i);
		}
	}
}
