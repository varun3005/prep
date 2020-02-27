package org.vp.prep.misc;

public class NQueen {
	static final int NUM_QUEEN = 8;
	int board[][] = new int[NUM_QUEEN][NUM_QUEEN];

	public void display() {
		for (int i = 0; i < NUM_QUEEN; i++) {
			System.out.print("");
			for (int j = 0; j < NUM_QUEEN; j++) {
				System.out.print(board[i][j] + "|");
			}
			System.out.println();
		}
	}

	public void scanAndPlaceQueen(int row, int col) {
		if (row == NUM_QUEEN) {
			display();
			System.out.println("\n---------");
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
		do { // top left to middle diagonal check
			if (board[i--][j--] == 1) {
				return;
			}
		} while (i > -1 && j > -1);

		i = row;
		j = col; // top right to middle diagonal check
		do {
			if (board[i--][j++] == 1) {
				return;
			}
		} while (i > -1 && j < NUM_QUEEN - 1);

		board[row][col] = 1;
		for (j = 0; j < NUM_QUEEN; j++) {
			scanAndPlaceQueen((row + 1), j);
		}
		board[row][col] = 0; // Rollback

	}

	public static void main(String args[]) {
		NQueen q = new NQueen();
		if (q.NUM_QUEEN < 4) {
			System.out.println("No Solution..!!");
			return;
		}
		for (int i = 0; i < q.NUM_QUEEN; i++) {
			q.scanAndPlaceQueen(0, i);
		}
	}
}
