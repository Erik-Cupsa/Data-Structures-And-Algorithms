import java.util.*; 

public class A3Q2 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int num_of_vertices = sc.nextInt();
		Graph g = new Graph(num_of_vertices);
		LinkedList<Integer> buffer;
		for (int i=0; i<num_of_vertices; i++) {
			int id = sc.nextInt();
			g.addNode(id);
			int neighbors = sc.nextInt();
			int neighbor = 0;
			buffer = new LinkedList<Integer>();
			for (int j=0; j<neighbors; j++) {
				neighbor = sc.nextInt();
				buffer.add(neighbor);
			}
			g.addEdges(id, buffer);
		}
		sc.close();
		F1(g);
		System.out.println(Arrays.toString(g.issue1.toArray()));
		System.out.println(Arrays.toString(g.issue2.toArray()));
	}

	// public static HashSet<Integer> dfs(Graph g, int start) {
	// 	HashSet<Integer> visited = new HashSet<>();
	// 	Stack<Integer> stack = new Stack<>();
	// 	stack.push(start);
	// 	while (!stack.isEmpty()){
	// 		int current = stack.pop();
	// 		if (!visited.contains(current)) {
	// 			visited.add(current);
	// 			for (int neighbour : g.adj.get(current)) {
	// 				stack.push(neighbour);
	// 			}
	// 		}
	// 	}
	// 	return visited;
	// }
public static void dfs(Hashtable<Integer, LinkedList<Integer>> adj, int node, HashSet<Integer> visited) {
		if (!visited.contains(node)) {
			visited.add(node);
			processNeighbors(adj, node, visited);
		}
	}

	private static void processNeighbors(Hashtable<Integer, LinkedList<Integer>> adj, int node, HashSet<Integer> visited) {
		if (adj.get(node) == null) {
			return;
		}
		else{
			for (int neighbor : adj.get(node)) if (!visited.contains(neighbor))dfs(adj, neighbor, visited);
		}
	}
	
	public static Graph transpose(Graph g) {
		Graph transposed = new Graph(g.num_nodes);
		for (int vertex : g.adj.keySet()) {
			LinkedList<Integer> neighbours = g.adj.get(vertex);
			for (int neighbour : neighbours) {
				LinkedList<Integer> list = transposed.adj.computeIfAbsent(neighbour, k -> new LinkedList<>());
				list.add(vertex);
			}
		}
		return transposed;
	}

	public static void F1(Graph g){
		HashSet<Integer> reachableFromIsland = new HashSet<>();
		HashSet<Integer> canReachIsland = new HashSet<>();

		dfs(g.adj, 0, reachableFromIsland);
		Graph transposed = transpose(g);
		dfs(transposed.adj, 0, canReachIsland);

		for (Integer point : g.order) {
			if (!reachableFromIsland.contains(point)) {
				g.issue2.add(point);
			}
		}
		for (Integer point : g.order) {
			if (!canReachIsland.contains(point)) {
				g.issue1.add(point);
			}
		}
	}
}

class Graph{
	public int num_nodes;
	public Hashtable<Integer, LinkedList<Integer>> adj;
	public LinkedList<Integer> order;
	public LinkedList<Integer> issue1;
	public LinkedList<Integer> issue2;
	
	public Graph(int num_vertices) {
		this.num_nodes = num_vertices;
		adj = new Hashtable<Integer, LinkedList<Integer>>();
		order = new LinkedList<Integer>();
		issue1 = new LinkedList<Integer>();
		issue2 = new LinkedList<Integer>();
	}
	public void addEdges(int u, LinkedList<Integer> vs) {
		this.adj.put(u, vs);
	}
	public void addNode(int u) {
		order.add(u);
		adj.put(u, new LinkedList<Integer>());
	}
	public LinkedList<Integer> get_Order(){
		return this.order;
	}	
}