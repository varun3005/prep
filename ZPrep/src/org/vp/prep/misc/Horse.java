package org.vp.prep.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Horse {
	int n;
	int board[][];

	public Horse(int n) {
		super();
		this.n = n;
		board = new int[n][n];
	}

	public static void main(String[] args) {
		Horse h = new Horse(8);
		List<Move> result = new ArrayList<>();
		for (int i = 0; i < h.n/2; i++) {			// since board is symmetric, only test 1st quarter
			for (int j = 0; j < h.n/2; i++) {
					h.checkBoard(i, j, result);
			}
		}
	}

	private void checkBoard(int x, int y, List<Move> result) {
		if(result.size()>=n*n-1){
			checkAndPrintBoard(result);
			return;				// dont go beyond 64 moves, backtrack
		}
		board[x][y] = 1;
		Move move = new Move(x,y);
		result.add(move);
		Queue<Move> movesNew = getMoves(x, y);
		if(movesNew.size()==0){ //if stuck backtrack
			return;
		}
		for(Move mv : movesNew){
			checkBoard(mv.x, mv.y, result);
			board[mv.x][mv.y] = 0;
			result.remove(mv);
		}
	}
	

	private void checkAndPrintBoard(List<Move> result) {
		int count=0;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(board[i][j] == 1){
					count++;
				}
			}
		}
		if(count<n*n){
			System.out.println("Fail :"+count);
			System.out.println(result);
			return;
		}
		System.out.println("Success :"+result );
		System.exit(0);
	}

	private Queue<Move> getMoves(int x, int y) {
		Queue<Move> moves = new LinkedBlockingQueue<>();
		if (isValid(x+1, y+2)) {
			moves.add(new Move(x + 1, y + 2));
		}
		if (isValid(x+1, y-2)) {
			moves.add(new Move(x + 1, y - 2));
		}
		if (isValid(x+2, y+1)) {
			moves.add(new Move(x + 2, y + 1));
		}
		if (isValid(x+2, y-1)) {
			moves.add(new Move(x + 2, y - 1));
		}
		if (isValid(x-1, y-2)) {
			moves.add(new Move(x - 1, y - 2));
		}
		if (isValid(x-1, y+2)) {
			moves.add(new Move(x - 1, y + 2));
		}
		if (isValid(x-2, y-1)) {
			moves.add(new Move(x - 2, y - 1));
		}
		if (isValid(x-2, y+1)) {
			moves.add(new Move(x - 2, y + 1));
		}
		return moves;

	}
	
	public boolean isValid(int x, int y){
		return x<n && y<n && x>=0 && y>=0 && board[x][y]==0;
	}

}
