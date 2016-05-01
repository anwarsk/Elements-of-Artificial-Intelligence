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
public interface InferenceAlgorithm {

	abstract public Map<String, Float> infer(Map<String, Float> evidence, List<String> query, 
			long sampleCount, BayesianNetwork network);
}
