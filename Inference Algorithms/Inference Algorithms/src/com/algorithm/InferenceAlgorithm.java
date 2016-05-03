/**
 * 
 */
package com.algorithm;

import java.util.List;
import java.util.Map;

import com.data.BayesianNetwork;
import com.data.EventNode;

/**
 * The Interface InferenceAlgorithm.
 *
 * @author Anwar
 */
public interface InferenceAlgorithm {

	/**
	 * Infer.
	 *
	 * @param evidence the evidence
	 * @param query the query
	 * @param sampleCount the sample count
	 * @param network the network
	 * @return the map
	 */
	abstract public Map<EventNode, Float> infer(Map<EventNode, Boolean> evidence, List<EventNode> query, 
			long sampleCount, BayesianNetwork network);
};
