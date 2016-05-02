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

		switch (algorithmName)
		{
		case "e":
			algorithm = new Enumeration();
			break;

		case "p":
			algorithm = new PriorSampling();
			break;

		case "r":
			algorithm = new RejectionSampling();
			break;

		case "l":
			algorithm = new LikelihoodWeighting();
			break;

		}

		return algorithm;
	}
}
