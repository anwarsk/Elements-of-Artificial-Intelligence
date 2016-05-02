/**
 * 
 */
package com.algorithm;

import java.util.List;
import java.util.Map;

import com.data.BayesianNetwork;
import com.data.EventNode;

/**
 * @author Anwar
 *
 */
public interface InferenceAlgorithm {

	abstract public Map<EventNode, Float> infer(Map<EventNode, Boolean> evidence, List<EventNode> query, 
			long sampleCount, BayesianNetwork network);
};
