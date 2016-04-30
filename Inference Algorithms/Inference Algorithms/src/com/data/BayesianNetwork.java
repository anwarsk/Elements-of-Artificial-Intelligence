/**
 * 
 */
package com.data;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the Bayesian Network
 * 
 * @author Anwar
 *
 */
public class BayesianNetwork {

	List<EventNode> eventNodes;

	public BayesianNetwork()
	{
		createDefaultBayesianNetwork();
		System.out.println("Network Created.");
	}

	private void createDefaultBayesianNetwork()
	{
		eventNodes = new ArrayList<EventNode>();

		// Creating Node B
		EventNode eventNodeB = new EventNode("B");
		eventNodeB.setProbabilityValue(0.001f);
		
		eventNodes.add(eventNodeB);

		// Creating Node E
		EventNode eventNodeE = new EventNode("E");
		eventNodeE.setProbabilityValue(0.002f);
		
		eventNodes.add(eventNodeE);

		// Creating Node A
		EventNode eventNodeA = new EventNode("A");
		eventNodeA.setParentNodes(eventNodeB, eventNodeE);
		eventNodeA.setProbabilityValue(0.95f, 0.94f, 0.29f, 0.001f);

		eventNodeB.setChildNodes(eventNodeA);
		eventNodeE.setChildNodes(eventNodeA);
		
		eventNodes.add(eventNodeA);


		// Create Node J
		EventNode eventNodeJ = new EventNode("J");
		eventNodeJ.setParentNodes(eventNodeA);
		eventNodeJ.setProbabilityValue(0.90f, 0.05f);

		eventNodeA.setChildNodes(eventNodeJ);
		
		eventNodes.add(eventNodeJ);

		// Create Node M
		EventNode eventNodeM = new EventNode("M");
		eventNodeM.setParentNodes(eventNodeA);
		eventNodeM.setProbabilityValue(0.70f, 0.01f);

		eventNodeA.setChildNodes(eventNodeM);
	
		eventNodes.add(eventNodeM);

	}
}
