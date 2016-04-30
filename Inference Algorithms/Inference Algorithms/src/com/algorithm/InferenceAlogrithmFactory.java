/**
 * 
 */
package com.algorithm;

import com.data.Constant;

/**
 * @author Anwar
 *
 */
public class InferenceAlogrithmFactory {
	
	public static InferenceAlgorithm getAlgorithm(String algorithmName)
	{
		assert(Constant.alogrithms.containsKey(algorithmName)) : "Invalid algorithm specified.";
		
		InferenceAlgorithm algorithm = null;
		
		return algorithm;
	}
}
