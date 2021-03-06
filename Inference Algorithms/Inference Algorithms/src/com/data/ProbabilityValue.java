/**
 * 
 */
package com.data;

import java.util.List;

/**
 * The Class ProbabilityValue.
 *
 * @author Anwar
 */
public class ProbabilityValue {

	/** The values. */
	List<Float> values;

	/**
	 * Instantiates a new probability value.
	 *
	 * @param parentNodes the parent nodes
	 * @param values the values
	 */
	ProbabilityValue(List<EventNode> parentNodes, List<Float> values)
	{
		assert(parentNodes == null || values.size() == Math.pow(2, parentNodes.size())) : "Mismatch number of values and parents (1.1)";
		assert(parentNodes != null || values.size() == 0) : "Mismatch number of values and parents (1.2)";

		this.values = values;
	}

	/**
	 * Gets the probability.
	 *
	 * @param parentNodes the parent nodes
	 * @return the probability
	 */
	public float getProbability(List<EventNode> parentNodes)
	{
		assert(parentNodes == null || values.size() == Math.pow(2, parentNodes.size())) : "Mismatch number of values and parents (2.1)";
		assert(parentNodes != null || values.size() == 0) : "Mismatch number of values and parents (2.2)";

		float probability = 0;
		int listIndex = 0;

		// Generating list of the index to get the probability value
		if(parentNodes != null)
		{
			for(EventNode event : parentNodes)
			{
				listIndex = (listIndex << 1) | (event.isValue() ? 0 : 1);
			}
		}

		probability = values.get(listIndex);

		return probability;
	}

	/**
	 * Gets the probability.
	 *
	 * @param parentValues the parent values
	 * @return the probability
	 */
	public float getProbability(boolean...parentValues)
	{
		assert(parentValues == null || values.size() == Math.pow(2, parentValues.length)) : "Mismatch number of values and parents (4.1)";
		assert(parentValues != null || values.size() == 0) : "Mismatch number of values and parents (4.2)";

		float probability = 0;
		int listIndex = 0;

		// Generating list of the index to get the probability value

		for(boolean parentValue : parentValues)
		{
			listIndex = (listIndex << 1) | (parentValue ? 0 : 1);
		}


		probability = values.get(listIndex);

		return probability;
	}
}
