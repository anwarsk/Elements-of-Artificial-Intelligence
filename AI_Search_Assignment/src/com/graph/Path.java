/**
 * 
 */
package com.graph;

import java.util.LinkedList;

/**
 * @author anwar
 *
 */
public class Path {
	
	LinkedList<Edge> edgeList;
	
	public Path()
	{
		edgeList = new LinkedList<Edge>();
	}
	
	public float getCost()
	{
		return 0;
	}
	
	public void addEdge(Edge edge)
	{
		assert (edgeList.contains(edge) == false);
		edgeList.add(edge);
	}
	
	public String printPath()
	{
		String pathString = "";
		float cost = 0;
		if(edgeList.size() > 0)
		{
			Edge firstEdge = edgeList.getFirst();
			pathString = "" + firstEdge.destination.name;
			
			for(Edge edge: edgeList)
			{
				pathString = edge.source.name + " > " + pathString;
				cost = cost + edge.distance;
			}
			pathString = pathString + "\nTotal Cost: " + cost; 
		}
		return pathString;
	}

}
