package com.graph;

public class Edge{

	public Node source;
	public Node destination;
	public float distance;
	
	public Edge(Node source, Node destination, float distance)
	{
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean isEqual = false;
		Edge edge = (Edge) obj;
		if(this.source.equals(edge.source) && this.destination.equals(edge.destination) || 
				this.destination.equals(edge.source) && this.source.equals(edge.destination))
		{
			isEqual = true;
		}
		
		return isEqual;
	}
}
