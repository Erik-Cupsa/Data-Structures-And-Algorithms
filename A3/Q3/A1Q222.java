import java.util.*;

public class A1Q222 {
    public static long[] num_pieces(long[] pieces, int[][] instructions) {
        int N = pieces.length;
        int M = instructions.length;
        long[] result = new long[N];
        for (int i = 0; i < N; i++) {
            result[i] = 0;
        }
        List<int[]>[] adj = new List[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < M; i++) {
            int u = instructions[i][0];
            int v = instructions[i][1];
            int w = instructions[i][2];
            adj[v].add(new int[]{u, w});
        }
        
        Stack<Integer> stack = topologicalSort(adj, pieces);
        while(!stack.isEmpty()){
            int vertex = stack.pop();
            for (int i = 0; i < M; i++) {
                if(instructions[i][0] == vertex){
                    result[vertex] += result[instructions[i][1]]*instructions[i][2];
                }
            }
            result[vertex] += pieces[vertex];
        }
        return result;
    }
    public static void topologicalSortUtil(int v, Set<Integer> visited, Stack<Integer> stack, List<int[]>[] adj) {
        visited.add(v);
        for (int[] childNode : adj[v]) {
            if (visited.contains(childNode[0])) {
                continue;
            }
            topologicalSortUtil(childNode[0], visited, stack, adj);
        }
        stack.add(v);
    }
    public static Stack<Integer> topologicalSort(List<int[]>[] adj, long[] pieces){
        Stack<Integer> stack = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        int N = pieces.length;;
        for (int i = 0; i < N; i++) {
            if (visited.contains(i)) continue;
            topologicalSortUtil(i, visited, stack, adj);
        }
        return stack;
    }

    public static void main(String[] args) {
        long[] pieces = {0, 0, 0, 0, 3};
        int[][] instructions = {
                {0,1,3},
                {1,4,1},
                {2,4,1},
                {3,4,2}
        };
        long[] idk = num_pieces(pieces, instructions);
        System.out.println(Arrays.toString(idk));
    }
}