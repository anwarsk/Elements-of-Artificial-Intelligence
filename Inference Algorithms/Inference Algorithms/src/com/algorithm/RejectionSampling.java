package com.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import com.data.BayesianNetwork;
import com.data.EventNode;

/**
 * The Class RejectionSampling.
 */
public class RejectionSampling implements InferenceAlgorithm {

	/** The sampling values. */
	private ArrayListValuedHashMap<EventNode, Boolean> samplingValues;
	
	/** The result. */
	private Map<EventNode, Float> result;
	
	/** The evidence. */
	private Map<EventNode, Boolean> evidence;
	
	/** The query. */
	private List<EventNode> query;
	
	/** The network. */
	private BayesianNetwork network;
	
	/** The sample count. */
	private long sampleCount;
	
	/** The actual sample count. */
	private long actualSampleCount;

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

	/**
	 * Initialize.
	 */
	private void initialize()
	{
		samplingValues  = new ArrayListValuedHashMap<EventNode, Boolean>();
		actualSampleCount = 0;
		
		// Initialize result map
		result = new HashMap<EventNode, Float>();
		for(EventNode queryNode : query)
		{
			result.put(queryNode, 0f);
		}
	}

	/**
	 * Sample data.
	 */
	private void sampleData()
	{
		// Sample the data in reverse order of the dependency
		EventNode[] dependencyOrder = network.getDependencyOrder();
		for(int sampleIndex = 0; actualSampleCount < sampleCount; sampleIndex++ )
		{
			ArrayListValuedHashMap<EventNode, Boolean> samplingValuesAtIndex = new ArrayListValuedHashMap<EventNode, Boolean>();
			boolean isEvidenceSatisfied = true;
			
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
	
				// If evidence node sample only if matches with evidence
				if(evidence.containsKey(eventNode) && eventNode.isValue() != evidence.get(eventNode))
				{
					isEvidenceSatisfied = false;
					break;
				}
				
				samplingValuesAtIndex.put(eventNode, eventNode.isValue());
			}
			
			if(isEvidenceSatisfied)
			{
				actualSampleCount++;
				samplingValues.putAll(samplingValuesAtIndex);
			}
		}
	}

	/**
	 * Generate result.
	 */
	private void generateResult()
	{
		//int evidenceCount = 0;

		// Iterate over sampled data
		for(int sampleIndex = 0; sampleIndex < actualSampleCount; sampleIndex++)
		{
			//  Count the number of instances where evidence is true and query is also true
			for(EventNode queryNode : query)
			{
				if(samplingValues.get(queryNode).get(sampleIndex))
				{
					result.put(queryNode, result.get(queryNode)+1);
				}
			}

		}

		//  Convert each count into probability
		for(EventNode resultNode : result.keySet())
		{
			result.put(resultNode, result.get(resultNode)/sampleCount);
		}
	}

}
