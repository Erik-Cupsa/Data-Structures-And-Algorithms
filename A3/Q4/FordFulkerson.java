import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		boolean[] visited = new boolean[graph.getNbNodes()];
		dfs(source, graph.getDestination(), graph, visited, path);
		if(!path.contains(destination)){return new ArrayList<>();}
		else{
			return reversePath(path);
		}
	}

	private static ArrayList<Integer> reversePath(ArrayList<Integer> path) {
		ArrayList<Integer> reversedPath = new ArrayList<>();
		for (int i = path.size() - 1; i >= 0; i--) {
			reversedPath.add(path.get(i));
		}
		return reversedPath;
	}

	private static boolean dfs(Integer current, Integer destination, WGraph graph, boolean[] visited, ArrayList<Integer> path) {
		if(visited[current]) return false;
		visited[current] = true;
		if (current.equals(destination)) {
			path.add(current);
			return true;
		}
		for (Edge edge: graph.getEdges()) {
			Integer a = edge.nodes[0];
			Integer b = edge.nodes[1];
			if (a == current) {
				if(edge.weight > 0 && dfs(b, destination, graph, visited, path)) {
					path.add(current);
					return true;
				}
			}
		}
		// path.remove(path.size() - 1);
		return false;
	}



	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;
		WGraph resGraph = new WGraph(graph);
		Integer source = graph.getSource();
		Integer destination = graph.getDestination();

		while (true) {
			ArrayList<Integer> path = pathDFS(source, destination, resGraph);
			if (path.isEmpty()) {
				break;
			}
			int minCapacity = Integer.MAX_VALUE;
			for (int i = 0; i < path.size() - 1; i++) {
				Edge edge = resGraph.getEdge(path.get(i), path.get(i+1));
				minCapacity = Math.min(minCapacity, edge.weight);
			}
			for (int i=0; i < path.size() - 1; i++) {
				Edge edge = resGraph.getEdge(path.get(i), path.get(i+1));
				edge.weight -= minCapacity;
			}
		}
		for(Edge edge : graph.getEdges()) {
			Integer a = edge.nodes[0];
			Integer b = edge.nodes[1];
			Edge curr = resGraph.getEdge(a, b);
			edge.weight -= curr.weight;
			if (b == destination){
				maxFlow += edge.weight;
			}
		}

		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}
	

	 public static void main(String[] args){
		WGraph g = new WGraph();
		g.setSource(0);
		g.setDestination(5);
		Edge[] edges = new Edge[] {
				new Edge(0, 1, 10),
				new Edge(0, 2, 5),
				new Edge(2, 4, 5),
				new Edge(1, 3, 10),
				new Edge(1, 6, 5),
				new Edge(3, 5, 10),
				new Edge(2, 5, 5)
		};
		Arrays.stream(edges).forEach(e->g.addEdge(e));
		ArrayList<Integer> path = FordFulkerson.pathDFS(0, 5, g);
		for(int element : path ){
			System.out.println(element);
		}
	}
}

