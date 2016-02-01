/**
 * 
 */
package com.main;


import com.constant.Constants;
import com.graph.Graph;
import com.graph.GraphBuilder;
import com.graph.Node;
import com.graph.Path;
import com.search.BFS;
import com.search.DFS;

/**
 * @author Anwar Shaikh
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//assert false;
		
		GraphBuilder graphBuider = new GraphBuilder(Constants.INPUT_FILENAME);
		Graph graph = graphBuider.build();
		
		String sourceName = "Arad";
		String destinationName = "Bucharest";
		
		Node source = graph.getNode(sourceName);
		Node destination = graph.getNode(destinationName);
		
		if(source == null)
		{
			System.out.println("Node with name '" + sourceName + "' does not exists!");
			System.exit(1);
		}
		
		if(destination == null)
		{
			System.out.println("Node with name '" + sourceName + "' does not exists!");
			System.exit(1);
		}
		
		BFS dfs = new BFS();
		Path path = dfs.findPath(source, destination, graph);
		
		if(path == null)
		{
			System.out.println("Path from '" + sourceName + "' to '"+ destinationName +"' does not exists!");
			System.exit(0);
		}
		
		String pathString = path.printPath();
		System.out.println("*** Path from '" + sourceName + "' to '" + destinationName + "' ***");
		System.out.println(pathString);
		
	}

}
