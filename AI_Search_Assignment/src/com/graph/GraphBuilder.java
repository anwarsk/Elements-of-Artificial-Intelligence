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
		
		Node sourceNode = new Node(source);
		Node destinationNode = new Node(destination);
		
		graph.addNode(sourceNode);
		graph.addNode(destinationNode);
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
