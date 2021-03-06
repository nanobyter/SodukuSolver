package logic;

import gui.GamePanel;
import gui.Window;

/**
 * @author {nanobyter}
 * 
 *         created: 2022-06-20
 */
public class Solver {

	public static final int GRID_SIZE = 9;
	private static int markedSquare;
	
	/* @formatter:off */
	private static int[][] board = {{7, 0, 2, 0, 5, 0, 6, 0, 0},
							 		{0, 0, 0, 0, 0, 3, 0, 0, 0},
									{1, 0, 0, 0, 0, 9, 5, 0, 0},
									{8, 0, 0, 0, 0, 0, 0, 9, 0},
									{0, 4, 3, 0, 0, 0, 7, 5, 0},
									{0, 9, 0, 0, 0, 0, 0, 0, 8},
									{0, 0, 9, 7, 0, 0, 0, 0, 5},
									{0, 0, 0, 2, 0, 0, 0, 0, 0},
									{0, 0, 7, 0, 4, 0, 2, 0, 3}};
	/* @formatter:on */

	
	public static boolean solveBoard(int[][] board) {

		for (int row = 0; row < GRID_SIZE; row++) {
			for (int col = 0; col < GRID_SIZE; col++) {
				if (board[row][col] == 0) {

					for (int numberToTry = 1; numberToTry <= 9; numberToTry++) {
						if (isValidPlacement(board, numberToTry, row, col)) {
							board[row][col] = numberToTry;

							if (solveBoard(board)) {
								return true;
							}
							else
								board[row][col] = 0;
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isNumberInRow(int[][] board, int number, int row) {

		for (int col = 0; col < GRID_SIZE; col++) {
			if (board[row][col] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean isNumberInColumn(int[][] board, int number, int col) {

		for (int row = 0; row < GRID_SIZE; row++) {
			if (board[row][col] == number) {
				return true;
			}
		}
		return false;
	}

	private static boolean isNumberInBox(int[][] board, int number, int row, int col) {

		// Get the top left corner (local 0,0) in the box
		int localRow = row - (row % 3);
		int localCol = col - (col % 3);

		for (int i = localRow; i < localRow + 3; i++) {
			for (int j = localCol; j < localCol + 3; j++) {

				if (board[i][j] == number) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isValidPlacement(int[][] board, int number, int row, int col) {

		return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, col)
				&& !isNumberInBox(board, number, row, col);
	}
	
//	private static void printBoard(int[][] board) {
//		for (int row = 0; row < GRID_SIZE; row++) {
//			for (int col = 0; col < GRID_SIZE; col++) {
//				System.out.print(board[row][col]);
//			}
//			System.out.println();
//		}
//	}
	
	
	public static int getBoardValue(int index1D) {
		int row = index1D / Solver.GRID_SIZE;
		int col = index1D % Solver.GRID_SIZE;
		
		return board[row][col];
	}
	
	public static void setBoardValue(int index1D, int value) {
		int row = index1D / Solver.GRID_SIZE;
		int col = index1D % Solver.GRID_SIZE;
		
		board[row][col] = value;
		
		GamePanel.updateBoard();
		markedSquare = -1;
	}
	
	public static int[][] getBoard(){
		return board;
	}

	public static int getMarkedSquare() {
		return markedSquare;
	}

	public static void setMarkedSquare(int index) {
		markedSquare = index;
	}
	
	
	/**
	 * To be able to run as an applet
	 */
	public void init() {
		new Window();
	}

//	public static void main(String[] args) {
//		
//		new Window();
//	}
}
