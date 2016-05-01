/**
 * 
 */
package com.algorithm;

import java.util.List;
import java.util.Map;

import com.data.BayesianNetwork;

/**
 * @author Anwar
 *
 */
public class Enumeration implements InferenceAlgorithm {

	/* (non-Javadoc)
	 * @see com.algorithm.InferenceAlgorithm#infer(java.util.Map, java.util.List, long, com.data.BayesianNetwork)
	 */
	@Override
	public Map<String, Float> infer(Map<String, Float> evidence, List<String> query, long sampleCount,
			BayesianNetwork network) {
		// TODO Auto-generated method stub
		return null;
	}

}
