import java.util.*;

public class A3Q1 {
	
    public static int min_moves(int[][] board) {
        int length = board.length - 1;
        int width = board[0].length - 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0}); 
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[length + 1][width + 1];
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int currX = curr[0];
            int currY = curr[1];
            int moves = curr[2];
			int num = board[currX][currY];
            
            if (currX == length && currY == width) {
                return moves;
            }
            for (int[] dir : directions) {
                int newX = currX + dir[0] * num;
                int newY = currY + dir[1] * num;
                if (newX >= 0 && newX <= length && newY >= 0 && newY <= width && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY, moves + 1});
                }
            }
        }
        return -1;
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

