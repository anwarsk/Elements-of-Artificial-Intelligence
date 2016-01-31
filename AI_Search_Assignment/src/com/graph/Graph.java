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
		nodes.add(node);
	}
	
	public void addEdge(Edge edge)
	{
		edges.add(edge);
	}
}
