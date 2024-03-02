import java.util.*;
import java.lang.*;
import java.io.*;


public class Game {
	
	public Board sudoku;
	
	public class Cell{
		private int row = 0;
		private int column = 0;
		
		public Cell(int row, int column) {
			this.row = row;
			this.column = column;
		}
		public int getRow() {
			return row;
		}
		public int getColumn() {
			return column;
		}
	}
	
	public class Region{
		private Cell[] matrix;
		private int num_cells;
		public Region(int num_cells) {
			this.matrix = new Cell[num_cells];
			this.num_cells = num_cells;
		}
		public Cell[] getCells() {
			return matrix;
		}
		public void setCell(int pos, Cell element){
			matrix[pos] = element;
		}
		
	}
	
	public class Board{
		private int[][] board_values;
		private Region[] board_regions;
		private int num_rows;
		private int num_columns;
		private int num_regions;
		
		public Board(int num_rows,int num_columns, int num_regions){
			this.board_values = new int[num_rows][num_columns];
			this.board_regions = new Region[num_regions];
			this.num_rows = num_rows;
			this.num_columns = num_columns;
			this.num_regions = num_regions;
		}
		
		public int[][] getValues(){
			return board_values;
		}
		public int getValue(int row, int column) {
			return board_values[row][column];
		}
		public Region getRegion(int index) {
			return board_regions[index];
		}
		public Region[] getRegions(){
			return board_regions;
		}
		public void setValue(int row, int column, int value){
			board_values[row][column] = value;
		}
		public void setRegion(int index, Region initial_region) {
			board_regions[index] = initial_region;
		}	
		public void setValues(int[][] values) {
			board_values = values;
		}
	}
	
	public int[][] solver() {
		//creating copy of the board to store solutions
		int[][] solution = sudoku.getValues();
		solveSudoku(0, 0, solution);
		sudoku.setValues(solution);
		return sudoku.getValues();
	}

	private boolean solveSudoku(int regionIndex, int position, int[][] solution) {
		if (regionIndex == sudoku.num_regions) {
			return true;
		}
		Region currentRegion = sudoku.getRegion(regionIndex);
		// col, row, solution
		// for num in range region
		//is valid and is iin region
		//update position
		//recursively call solve sudoku
		// after recursive call clear value
		//return false after loop

		//is valid = is the number in region already, and touching
		Cell cell = currentRegion.getCells()[position];
		int row = cell.getRow();
		int col = cell.getColumn();
		if (sudoku.getValue(row, col) != -1){
			int nextRegion = (position == currentRegion.num_cells - 1) ? regionIndex + 1 : regionIndex;
				int nextPosition = (position == currentRegion.num_cells -1) ? 0 : position + 1;

				if (solveSudoku(nextRegion, nextPosition, solution)){
					return true;
				}
		}
		for(int i = 1; i <= currentRegion.num_cells; i++){
			if (sudoku.getValue(row, col) == -1 && isValidMove(row, col, i, currentRegion)){
				solution[row][col] = i;
				int nextRegion = (position == currentRegion.num_cells - 1) ? regionIndex + 1 : regionIndex;
				int nextPosition = (position == currentRegion.num_cells -1) ? 0 : position + 1;

				if (solveSudoku(nextRegion, nextPosition, solution)){
					return true;
				}
	
				solution[row][col] = -1;
			}
		}
		return false;
	}

	private boolean isValidMove(int row, int col, int num, Region currentRegion){
		//checking if number in region already
		for(Cell cell: currentRegion.getCells()){
			if(sudoku.board_values[cell.row][cell.column] == num){
				return false;
			}
		}

		//checking if touching
		int[] changeRow = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] changeColumn = {-1, 0, 1, -1, 1, -1, 0, 1};
		for (int i = 0; i < changeRow.length; i++) {
			int currRow = row + changeRow[i];
			int currColumn = col + changeColumn[i];
			if(currRow >= 0 && currColumn >= 0 && currRow < sudoku.num_rows && currColumn < sudoku.num_columns && sudoku.board_values[currRow][currColumn]==num){
				return false;
			}
		}
		

		//passes everything
		return true;
	}

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int rows = sc.nextInt();
		int columns = sc.nextInt();
		int[][] board = new int[rows][columns];
		//Reading the board
		for (int i=0; i<rows; i++){
			for (int j=0; j<columns; j++){
				String value = sc.next();
				if (value.equals("-")) {
					board[i][j] = -1;
				}else {
					try {
						board[i][j] = Integer.valueOf(value);
					}catch(Exception e) {
						System.out.println("Ups, something went wrong");
					}
				}	
			}
		}
		int regions = sc.nextInt();
		Game game = new Game();
	    game.sudoku = game.new Board(rows, columns, regions);
		game.sudoku.setValues(board);
		for (int i=0; i< regions;i++) {
			int num_cells = sc.nextInt();
			Game.Region new_region = game.new Region(num_cells);
			for (int j=0; j< num_cells; j++) {
				String cell = sc.next();
				String value1 = cell.substring(cell.indexOf("(") + 1, cell.indexOf(","));
				String value2 = cell.substring(cell.indexOf(",") + 1, cell.indexOf(")"));
				Game.Cell new_cell = game.new Cell(Integer.valueOf(value1)-1,Integer.valueOf(value2)-1);
				new_region.setCell(j, new_cell);
			}
			game.sudoku.setRegion(i, new_region);
		}
		int[][] answer = game.solver();
		for (int i=0; i<answer.length;i++) {
			for (int j=0; j<answer[0].length; j++) {
				System.out.print(answer[i][j]);
				if (j<answer[0].length -1) {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
	


}


