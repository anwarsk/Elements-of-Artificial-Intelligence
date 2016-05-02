/**
 * 
 */
package com.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.data.BayesianNetwork;
import com.data.EventNode;
import com.data.ProbabilityValue;

/**
 * @author Anwar
 *
 */
public class Enumeration implements InferenceAlgorithm {

	private BayesianNetwork network;

	/* (non-Javadoc)
	 * @see com.algorithm.InferenceAlgorithm#infer(java.util.Map, java.util.List, long, com.data.BayesianNetwork)
	 */
	@Override
	public Map<EventNode, Float> infer(Map<EventNode, Boolean> evidence, List<EventNode> query, 
			long sampleCount, BayesianNetwork network) 
	{
		return null;
	}
};