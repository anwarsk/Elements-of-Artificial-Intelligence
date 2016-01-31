package com.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	List<Node> nodes;
	List<Edge> edges;
	
	public Graph()
	{
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}
	
	public void addNode(Node node)
	{
		assert(isNodeExists(node) == false);
		
		nodes.add(node);
	}
	
	public void addEdge(Edge edge)
	{
		assert(isEdgeExists(edge) == false);
		
		edges.add(edge);
	}
	
	boolean isNodeExists(Node node)
	{
		boolean nodeExists = false;
		for(Node indexNode : nodes)
		{
			if(indexNode.equals(node))
			{
				nodeExists = true;
				break;
			}
		}

		return nodeExists;
	}
	
	boolean isEdgeExists(Edge edge)
	{
		boolean edgeExists = false;
		for(Edge indexEdge : edges)
		{
			if(indexEdge.equals(edge))
			{
				edgeExists = true;
				break;
			}
		}

		return edgeExists;
	}
	
	public Node getNode(String name)
	{
		Node requestedNode = null;
		
		for(Node node: nodes)
		{
			if(name.equalsIgnoreCase(node.name))
			{
				requestedNode = node;
				break;
			}
		}
		
		return requestedNode;
	}
}
