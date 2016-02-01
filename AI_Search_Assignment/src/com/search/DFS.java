package com.search;

import java.util.ArrayList;
import java.util.List;

import com.graph.Edge;
import com.graph.Graph;
import com.graph.Node;
import com.graph.Path;

public class DFS implements SearchAlgorithm {

	List<Node> visitedNodes;
	
	public DFS()
	{
		visitedNodes = new ArrayList<Node>();
	}
	
	@Override
	public Path findPath(Node source, Node destination, Graph graph) {
		// TODO Auto-generated method stub
		Path path = null;
		Node currentNode = source;
		
		visitedNodes.add(currentNode);
		if(currentNode.equals(destination) == false)
		{
			for(Edge edge: currentNode.connectedEdges)
			{
				if(visitedNodes.contains(edge.destination) == false)
				{
					path = findPath(edge.destination, destination, graph);
					if(path != null)
					{
						path.addEdge(edge);
						break;
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
