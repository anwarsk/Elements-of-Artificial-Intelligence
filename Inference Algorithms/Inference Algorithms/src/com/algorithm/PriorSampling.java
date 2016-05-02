/**
 * 
 */
package com.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import com.data.BayesianNetwork;
import com.data.EventNode;

/**
 * @author Anwar
 *
 */
public class PriorSampling implements InferenceAlgorithm {


	private ArrayListValuedHashMap<EventNode, Boolean> samplingValues;
	private Map<EventNode, Float> result;
	private Map<EventNode, Boolean> evidence;
	private List<EventNode> query;
	private BayesianNetwork network;
	private long sampleCount;
	
	/* (non-Javadoc)
	 * @see com.algorithm.InferenceAlgorithm#infer(java.util.Map, java.util.List, long, com.data.BayesianNetwork)
	 */
	public Map<EventNode, Float> infer(Map<EventNode, Boolean> evidence, List<EventNode> query, 
			long sampleCount, BayesianNetwork network) {
		// TODO Auto-generated method stub
		
		this.evidence = evidence;
		this.query = query;
		this.sampleCount = sampleCount;
		this.network = network;
		
		// 1. Initialize
		this.initialize();

		// 2. Sample in reverse order of dependency
		this.sampleData();

		// 3. Count the number of instances where evidence is true and query is true
		this.generateResult();

		return result;
	}

	private void initialize()
	{
		samplingValues  = new ArrayListValuedHashMap<EventNode, Boolean>();

		// Initialize result map
		result = new HashMap<EventNode, Float>();
		for(EventNode queryNode : query)
		{
			result.put(queryNode, 0f);
		}
	}
	
	private void sampleData()
	{
		// Sample the data in reverse order of the dependency
		EventNode[] dependencyOrder = network.getDependencyOrder();
		for(int sampleIndex = 0; sampleIndex < sampleCount; sampleIndex++)
		{
			for(int index = dependencyOrder.length-1 ; index >= 0; index--)
			{
				EventNode eventNode = dependencyOrder[index];
				float probability = eventNode.getProbabilityValue();
				double randomValue = Math.random();

				if(randomValue <= probability)
				{
					eventNode.setValue(true);
				}
				else
				{
					eventNode.setValue(false);
				}

				samplingValues.put(eventNode, eventNode.getBooleanValue());
			}
		}
	}
	
	private void generateResult()
	{
		int evidenceCount = 0;

		// Iterate over sampled data
		for(int sampleIndex = 0; sampleIndex < sampleCount; sampleIndex++)
		{
			boolean isSatisfyingEvidence =  true;
			for(EventNode evidenceNode : evidence.keySet())
			{
				if(samplingValues.get(evidenceNode).get(sampleIndex) != (evidence.get(evidenceNode)))
				{
					isSatisfyingEvidence = false;
					break;
				}
			}

			if(isSatisfyingEvidence)
			{
				evidenceCount++;
				//  Count the number of instances where evidence is true and query is also true
				for(EventNode queryNode : query)
				{
					if(samplingValues.get(queryNode).get(sampleIndex))
					{
						result.put(queryNode, result.get(queryNode)+1);
					}
				}
			}
		}

		//  Convert each count into probability
		for(EventNode resultNode : result.keySet())
		{
			result.put(resultNode, result.get(resultNode)/evidenceCount);
		}
	}
}

