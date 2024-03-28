import java.util.*;

public class A3Q1 {
	
	public static int min_moves(int[][] board) {
		return 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num_rows = sc.nextInt();
		int num_columns = sc.nextInt();
		int [][]board = new int[num_rows][num_columns];
		for (int i=0; i<num_rows; i++) {
			char[] line = sc.next().toCharArray();
			for (int j=0; j<num_columns; j++) {
				board[i][j] = (int) (line[j] - '0');
			}
		}
		int answer = min_moves(board);
		System.out.println(answer);
	}
}

