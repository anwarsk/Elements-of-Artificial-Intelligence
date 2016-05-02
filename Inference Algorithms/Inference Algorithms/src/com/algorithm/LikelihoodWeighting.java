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

/**
 * @author Anwar
 *
 */
public class LikelihoodWeighting implements InferenceAlgorithm {


	private ArrayListValuedHashMap<EventNode, Boolean> samplingValues;
	private Map<EventNode, Float> result;
	private Map<EventNode, Boolean> evidence;
	private List<EventNode> query;
	private BayesianNetwork network;
	private long sampleCount;
	private List<Float> sampleWeights;
	private float totalSampleWeight;

	@Override
	public Map<EventNode, Float> infer(Map<EventNode, Boolean> evidence, List<EventNode> query, long sampleCount,
			BayesianNetwork network) {
		// TODO Auto-generated method stub

		this.evidence = evidence;
		this.query = query;
		this.sampleCount = sampleCount;
		this.network = network;
		
		// 1. Initialize
		this.initialize();

		// 2. Sample the data
		this.sampleData();

		// 3. Generate results
		this.generateResult();

		return result;
	}

	private void initialize()
	{
		samplingValues  = new ArrayListValuedHashMap<EventNode, Boolean>();
		sampleWeights = new ArrayList<Float>();

		// Set Evidence Node values
		for(EventNode evidenceNode : evidence.keySet())
		{
			evidenceNode.setValue(evidence.get(evidenceNode));
		}

		// Initialize result map
		result = new HashMap<EventNode, Float>();
		for(EventNode queryNode : query)
		{
			result.put(queryNode, 0f);
		}
	}

	private void sampleData()
	{
		totalSampleWeight = 0;
		// Sample the data in reverse order of the dependency
		EventNode[] dependencyOrder = network.getDependencyOrder();
		for(int sampleIndex = 0; sampleIndex < sampleCount; sampleIndex++)
		{
			float sampleWeight = 1;
			for(int index = dependencyOrder.length-1 ; index >= 0; index--)
			{
				EventNode eventNode = dependencyOrder[index];
				float probability = eventNode.getProbabilityValue();
				if(evidence.containsKey(eventNode))
				{
					// If evidence node then calculate weight and proceed
					sampleWeight = sampleWeight * probability;
				}
				else
				{
					// Sample only if does not contain in evidence node
					double randomValue = Math.random();

					if(randomValue <= probability)
					{
						eventNode.setValue(true);
					}
					else
					{
						eventNode.setValue(false);
					}
				}

				samplingValues.put(eventNode, eventNode.getBooleanValue());
				sampleWeights.add(sampleWeight);
				totalSampleWeight += sampleWeight;
			}
		}
	}

	private void generateResult()
	{
		int evidenceCount = 0;

		// Iterate over sampled data
		for(int sampleIndex = 0; sampleIndex < sampleCount; sampleIndex++)
		{

			//  Count the number of instances where evidence is true and query is also true
			for(EventNode queryNode : query)
			{
				if(samplingValues.get(queryNode).get(sampleIndex))
				{
					result.put(queryNode, result.get(queryNode) + sampleWeights.get(sampleIndex));
				}
			}
		}

		//  Convert each count into probability
		for(EventNode resultNode : result.keySet())
		{
			result.put(resultNode, result.get(resultNode)/totalSampleWeight);
		}
	}
}
