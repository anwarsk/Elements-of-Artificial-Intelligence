package com.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.graph.Edge;
import com.graph.Graph;
import com.graph.Node;
import com.graph.Path;

public class BFS implements SearchAlgorithm {

	List<Node> visitedNodes;
	Queue<Edge> edgeQueue = new LinkedList<Edge>();
	public BFS()
	{
		visitedNodes = new ArrayList<Node>();
	}

	@Override
	public Path findPath(Node source, Node destination, Graph graph) {
		// TODO Auto-generated method stub
		Path path = null;
		Node currentNode = source;
		visitedNodes.add(currentNode);
		Map<Node,Edge> nodeToPathEdgeListMap = new HashMap<Node, Edge>();

		//		if(currentNode.equals(destination) == false)
		//		{
		//			for(Edge edge: currentNode.connectedEdges)
		//			{
		//				if(visitedNodes.contains(edge.destination) == false)
		//				{
		//					edgeQueue.add(edge);
		//					visitedNodes.add(edge.destination);
		//				}
		//			}
		//
		//			Edge nextEdge = edgeQueue.peek();
		//			if(nextEdge.source.equals(currentNode))
		//			{
		//				nextEdge = edgeQueue.poll();
		//				path = findPath(nextEdge.destination, destination, graph);
		//
		//				if(path != null)
		//				{
		//					path.addEdge(nextEdge);
		//				}
		//			}
		//		}
		//		else
		//		{
		//			path = new Path();
		//		}



		do
		{
			for(Edge edge: currentNode.connectedEdges)
			{
				if(visitedNodes.contains(edge.destination) == false)
				{
					edgeQueue.add(edge);
					visitedNodes.add(edge.destination);
				}
			}
			if(edgeQueue.isEmpty())
			{
				break;
			}
			Edge nextEdge = edgeQueue.poll();
			currentNode = nextEdge.destination;
			nodeToPathEdgeListMap.put(nextEdge.destination, nextEdge);
		}
		while(currentNode.equals(destination) == false);

		if(currentNode.equals(destination))
		{
			path = new Path();
			Edge currentEdge = null;
			Node sourceNode = currentNode;
			while(sourceNode.equals(source) == false)
			{
				currentEdge = nodeToPathEdgeListMap.get(sourceNode);
				sourceNode = currentEdge.source;
				path.addEdge(currentEdge);
			}

		}

		return path;
	}

}
