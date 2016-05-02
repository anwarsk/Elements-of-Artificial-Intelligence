/**
 * 
 */
package com.data;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds the Bayesian Network
 * 
 * @author Anwar
 *
 */
public class BayesianNetwork {

	Map<String, EventNode> eventNodes;
	String[] summationOrder;
	EventNode[] dependencyOrder;

	public BayesianNetwork()
	{
		createDefaultBayesianNetwork();
		System.out.println("Network Created.");
	}

	private void createDefaultBayesianNetwork()
	{
		eventNodes = new HashMap<String,EventNode>();

		// Creating Node B
		EventNode eventNodeB = new EventNode("B");
		eventNodeB.setProbabilityValue(0.001f);
		
		eventNodes.put("B", eventNodeB);

		// Creating Node E
		EventNode eventNodeE = new EventNode("E");
		eventNodeE.setProbabilityValue(0.002f);
		
		eventNodes.put("E", eventNodeE);

		// Creating Node A
		EventNode eventNodeA = new EventNode("A");
		eventNodeA.setParentNodes(eventNodeB, eventNodeE);
		eventNodeA.setProbabilityValue(0.95f, 0.94f, 0.29f, 0.001f);

		eventNodeB.setChildNodes(eventNodeA);
		eventNodeE.setChildNodes(eventNodeA);
		
		eventNodes.put("A", eventNodeA);


		// Create Node J
		EventNode eventNodeJ = new EventNode("J");
		eventNodeJ.setParentNodes(eventNodeA);
		eventNodeJ.setProbabilityValue(0.90f, 0.05f);

		eventNodeA.setChildNodes(eventNodeJ);
		
		eventNodes.put("J", eventNodeJ);

		// Create Node M
		EventNode eventNodeM = new EventNode("M");
		eventNodeM.setParentNodes(eventNodeA);
		eventNodeM.setProbabilityValue(0.70f, 0.01f);

		eventNodeA.setChildNodes(eventNodeM);
	
		eventNodes.put("M", eventNodeM);
		
		summationOrder = new String[]{"M", "J", "A", "E", "B"};
		dependencyOrder = new EventNode[]{eventNodeM, eventNodeJ, eventNodeA, eventNodeE, eventNodeB};

	}
	
	public String[] getSummationOrder() {
		return summationOrder;
	}
	
	public EventNode[] getDependencyOrder()
	{
		return dependencyOrder;
	}

	public EventNode getEventNode(String eventName)
	{
		assert(eventNodes.containsKey(eventName)): "Invalid Event Name";
		
		return eventNodes.get(eventName);
	}
}
