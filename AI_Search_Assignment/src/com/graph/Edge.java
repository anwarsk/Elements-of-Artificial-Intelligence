package com.graph;

public class Edge {

	public Node source;
	public Node destination;
	public float distance;
	
	public Edge(Node source, Node destination, float distance)
	{
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}
}
