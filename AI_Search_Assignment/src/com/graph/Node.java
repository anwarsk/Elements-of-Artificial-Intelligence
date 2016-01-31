package com.graph;

import java.util.ArrayList;
import java.util.List;

public class Node{
	
	public String name;
	public List<Edge> connectedEdges;

	public Node(String name)
	{
		this.name = name;
		connectedEdges = new ArrayList<Edge>();
	}

	@Override
	public boolean equals(Object node) {
		// TODO Auto-generated method stub
		boolean isEqual = false;
		if(this.name.equalsIgnoreCase(((Node)node).name))
		{
			isEqual = true;
		}
		return isEqual;
	}
	
	public void addConnectingEdge(Node destinationNode, float distance)
	{
		Edge edge = new Edge(this, destinationNode, distance);
		
		assert (connectedEdges.contains(edge) == false);
		
		connectedEdges.add(edge);
	}
	
	
}
