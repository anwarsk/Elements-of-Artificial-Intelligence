/**
 * 
 */
package com.main;


import java.util.Scanner;

import com.constant.Constants;
import com.graph.Graph;
import com.graph.GraphBuilder;
import com.graph.Node;
import com.graph.Path;
import com.search.BFS;
import com.search.DFS;
import com.search.IterativeDeepening;
import com.search.SearchAlgorithm;
import com.search.SearchAlgorithmFactory;

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
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter 'Source','Destination','Algorithm': ");
		String inputLine = scanner.nextLine();
		scanner.close();
		String[]input = inputLine.split(",");
//		String sourceName = "Arad";
//		String destinationName = "Bucharest";
		
		String sourceName = input[0];
		String destinationName = input[1];
		String algorithm = input[2];
		
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
		
		SearchAlgorithm searchAlgorihtm = SearchAlgorithmFactory.getSearchAlgorithm(algorithm);
		
		if(searchAlgorihtm == null)
		{
			System.out.println("Invalid algorithm specified. Please retry.");
			System.exit(1);
		}
			
		Path path = searchAlgorihtm.findPath(source, destination, graph);
		
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
