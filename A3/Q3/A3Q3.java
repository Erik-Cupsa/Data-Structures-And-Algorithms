import java.util.*;

public class A3Q3 {
	public static void topologicalSort(int v, Map<Integer, List<int[]>> adj, boolean[] visited, Stack<Integer> stack) {
		visited[v] = true;
		List<int[]> dependencies;
		if (adj.containsKey(v)) {
			dependencies = adj.get(v);
		} else {
			dependencies = Collections.emptyList();
		}
		for (int[] dependency : dependencies) {
			int neighbor = dependency[0];
			if (!visited[neighbor]) {
				topologicalSort(neighbor, adj, visited, stack);
			}
		}
		stack.push(v);
	}
	public static long[] num_pieces(long[] pieces, int[][] instructions) {
		Map<Integer, List<int[]>> adj = new HashMap<>();
		for (int[] instruction : instructions) {
			int u = instruction[0];
			int v = instruction[1];
			int w = instruction[2];

			if(!adj.containsKey(v)) {
				adj.put(v, new ArrayList<>());
			}

			adj.get(v).add(new int[]{u, w});
		}

		long[] total_pieces = new long[pieces.length];
		for (int i = 0; i < pieces.length; i++){
			total_pieces[i] = 0;
		}
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[pieces.length];
		for (int i = 0; i < pieces.length; i ++) {
			if (!visited[i]) {
				topologicalSort(i, adj, visited, stack);
			}
		}
		System.out.println(stack);
		while(!stack.isEmpty()){
            int module = stack.pop();
            for (int[] instruction : instructions) {
				int currModule = instruction[0];
				int target = instruction[1];
				int quantity = instruction[2];
                if(currModule == module){
                    total_pieces[module] += total_pieces[target]*quantity;
                }
            }
            total_pieces[module] += pieces[module];
        }
		return total_pieces;
	}
	public static void main(String[] args) {
        long[] pieces = {0, 0, 0, 0, 3};
        int[][] instructions = {
                {0,1,3},
                {1,4,1},
                {2,4,1},
                {3,4,2}
        };
        long[] testing = num_pieces(pieces, instructions);
        System.out.println(Arrays.toString(testing));
    }
}