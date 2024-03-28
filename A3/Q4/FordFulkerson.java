import java.util.*;
import java.io.File;

public class FordFulkerson {

	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		ArrayList<Integer> path = new ArrayList<Integer>();
		/* YOUR CODE GOES HERE*/

		return path;
	}



	public static String fordfulkerson( WGraph graph){
		String answer="";
		int maxFlow = 0;
		
		/* YOUR CODE GOES HERE		*/

		answer += maxFlow + "\n" + graph.toString();	
		return answer;
	}
	

	 public static void main(String[] args){
		String file = args[0];
		File f = new File(file);
		WGraph g = new WGraph(file);
	    System.out.println(fordfulkerson(g));
	 }
}

