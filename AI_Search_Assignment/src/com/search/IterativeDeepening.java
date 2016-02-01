/**
 * 
 */
package com.search;

import java.util.ArrayList;
import java.util.List;

import com.graph.Edge;
import com.graph.Graph;
import com.graph.Node;
import com.graph.Path;

/**
 * @author Anwar Shaikh
 *
 */
public class IterativeDeepening implements SearchAlgorithm {

	int depthStep;
	List<Node> visitedNodes;
	
	public IterativeDeepening(int depthStep) {
		this.depthStep = depthStep;
		this.visitedNodes = new ArrayList<Node>();
	}
	
	/*
	 * 
	 */
	@Override
	public Path findPath(Node source, Node destination, Graph graph) {
		
		Path path = null;
		
		for(int depth = depthStep; depth < 1000; depth+=this.depthStep)
		{
			visitedNodes.clear();
			path = this.DFS(source, destination, graph, depth);
			if(path != null)
			{
				break;
			}
		}
		
		return path;
	}
	
	private Path DFS(Node source, Node destination, Graph graph, int depth)
	{
		Path path = null;
		Node currentNode = source;
		
		visitedNodes.add(currentNode);
		if(currentNode.equals(destination) == false)
		{
			if(depth > 0)
			{
				for(Edge edge: currentNode.connectedEdges)
				{
					if(visitedNodes.contains(edge.destination) == false)
					{
						path = DFS(edge.destination, destination, graph, depth-1);
						if(path != null)
						{
							path.addEdge(edge);
							break;
						}
					}
				}
			}
		}
		else
		{
			return new Path();
		}
		
		return path;
	}

}
