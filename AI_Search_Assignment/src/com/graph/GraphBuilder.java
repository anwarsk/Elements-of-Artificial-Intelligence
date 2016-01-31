package com.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GraphBuilder {

	private String inputFile;
	private Graph graph;

	public GraphBuilder(String inputFile)
	{
		this.inputFile = inputFile;
	}

	public void addEdge(String[] data)
	{
		String source = data[0];
		String destination = data[1];
		float distance = Float.parseFloat(data[2]);
		
		Node sourceNode = graph.getNode(source);
		if(sourceNode == null)
		{
			sourceNode = new Node(source);
			graph.addNode(sourceNode);
		}
		
		Node destinationNode = graph.getNode(destination);
		if(destinationNode == null)
		{
			destinationNode = new Node(destination);
			graph.addNode(destinationNode);
		}
		
		sourceNode.addConnectingEdge(destinationNode, distance);
		destinationNode.addConnectingEdge(sourceNode, distance);
		
		graph.addEdge(new Edge(sourceNode, destinationNode, distance));
	}
	
	public Graph build()
	{
		graph = null;
		
		try 
		{
			graph = new Graph();
			FileReader fileReader = new FileReader(inputFile);
			BufferedReader buffReader = new BufferedReader(fileReader);
			String line = null;

			while((line = buffReader.readLine()) != null)
			{
				String[]lineContents = line.split(",");
				this.addEdge(lineContents);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error in reading file. Unable to create graph.");
		}
		
		return graph;
	}
}
