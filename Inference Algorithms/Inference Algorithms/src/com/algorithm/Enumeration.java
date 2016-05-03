/**
 * 
 */
package com.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import com.data.BayesianNetwork;
import com.data.EventNode;
import com.data.ProbabilityValue;

/**
 * @author Anwar
 *
 */
public class Enumeration implements InferenceAlgorithm {

	private Map<EventNode, Float> result;
	private Map<EventNode, Boolean> evidence;
	private List<EventNode> query;
	private BayesianNetwork network;
	List<EventNode> enumeratedNodes;
	List<Factor> factors;

	/* (non-Javadoc)
	 * @see com.algorithm.InferenceAlgorithm#infer(java.util.Map, java.util.List, long, com.data.BayesianNetwork)
	 */
	public Map<EventNode, Float> infer(Map<EventNode, Boolean> evidence, List<EventNode> query, 
			long sampleCount, BayesianNetwork network) {
		// TODO Auto-generated method stub

		// Initialize result map
		result = new HashMap<EventNode, Float>();
		for(EventNode queryNode : query)
		{
			result.put(queryNode, 0f);
		}

		for(EventNode queryNode: query)
		{
			this.evidence = evidence;
			this.query = new ArrayList<EventNode>();
			this.query.add(queryNode);
			this.network = network;

			// 1.Initialize
			this.initialize();

			for(EventNode node : network.getDependencyOrder())
			{
				if(! (evidence.containsKey(node) || query.contains(node)))
				{
					List<EventNode> parentNodes = node.getParentNodes();
					List<EventNode> childNodes = node.getChildNodes();

					List<EventNode> nodesToConsider = new ArrayList<EventNode>();
					nodesToConsider.addAll(parentNodes);
					nodesToConsider.addAll(childNodes);


					List<EventNode> nodesToEnumerate = new ArrayList<EventNode>();
					nodesToEnumerate.add(node);
					nodesToEnumerate.addAll(childNodes);

					List<EventNode> factorParents = new ArrayList<EventNode>();
					factorParents.addAll(parentNodes);

					List<Factor> listOfFactorsToRemove = new ArrayList<Factor>();
					for(Factor factor : this.factors)
					{
						if(factor.getNodesToConsider().contains(node))
						{
							nodesToEnumerate.add(factor);
							nodesToConsider.addAll(factor.getNodesToConsider());
							factorParents.addAll(factor.getNodesToConsider());
							listOfFactorsToRemove.add(factor);
						}
					}

					factors.removeAll(listOfFactorsToRemove);

					nodesToConsider.remove(node);
					nodesToConsider.removeAll(enumeratedNodes);

					nodesToEnumerate.removeAll(enumeratedNodes);

					factorParents.removeAll(evidence.keySet());
					factorParents.remove(node);
					factorParents.removeAll(enumeratedNodes);

					Factor factor = new Factor(nodesToConsider, nodesToEnumerate, factorParents, node);
					factor.enumerate();

					factor.setValue(true);
					this.factors.add(factor);
					this.enumeratedNodes.add(node);

				}

			}

			float resultantProbability = 1;
			queryNode.setValue(true);
			float factorProbability = factors.get(0).getProbabilityValue() * queryNode.getProbabilityWithBooleanValue();
			queryNode.setValue(false);
			float inverseFactorProbability = factors.get(0).getProbabilityValue() * queryNode.getProbabilityWithBooleanValue();
			resultantProbability = factorProbability / (factorProbability + inverseFactorProbability);

			result.put(queryNode, resultantProbability);
		}

		return result;
	}

	private void initialize()
	{
		// Set Evidence Node values
		for(EventNode evidenceNode : evidence.keySet())
		{
			evidenceNode.setValue(evidence.get(evidenceNode));
		}


		enumeratedNodes = new ArrayList<EventNode>();
		factors = new ArrayList<Factor>();
	}
};

class Factor extends EventNode
{
	private List<EventNode> nodesToConsider;
	private List<EventNode> nodesToEnumerate;
	//private ProbabilityValue probabilityValue;
	//private List<EventNode> parentNodes;
	private EventNode currentNode;

	public Factor() 
	{
		super("Factor");
	}

	public Factor(List<EventNode> nodesToConsider, List<EventNode> nodesToEnumerate,  List<EventNode> parents, EventNode currentNode)
	{
		super("Factor");
		this.nodesToConsider = nodesToConsider;
		this.nodesToEnumerate = nodesToEnumerate;

		this.setParentNodes(parents);
		this.currentNode = currentNode;
	}

	public void enumerate()
	{
		List<Float> proabilityValues = new ArrayList<Float>();
		for(int index = (int) (Math.pow(2, this.getParentNodes().size())-1); index >= 0;  index--)
		{
			int number = index;
			int parentIndex = this.getParentNodes().size()-1;
			while(number > 0)
			{
				int r = number%2;
				this.getParentNodes().get(parentIndex).setValue((r!=0));
				number = number/2;
				parentIndex--;
			}

			while(parentIndex >=0)
			{
				this.getParentNodes().get(parentIndex).setValue(false);
				parentIndex -= 1;
			}

			currentNode.setValue(true);
			float firstValue = 1;
			for(EventNode node: nodesToEnumerate)
			{
				firstValue = firstValue * node.getProbabilityWithBooleanValue();
			}

			currentNode.setValue(false);
			float secondValue = 1;
			for(EventNode node: nodesToEnumerate)
			{
				secondValue = secondValue * node.getProbabilityWithBooleanValue();
			}
			System.out.println("First Value :" + firstValue);
			System.out.println("Second Value:" + secondValue);
			proabilityValues.add(firstValue + secondValue);
		}

		this.setProbabilityValue(proabilityValues);
	}

	public List<EventNode> getNodesToConsider()
	{
		return nodesToConsider;
	}


};