/**
 * 
 */
package com.io;

import java.util.Map;

import com.data.EventNode;

/**
 * The Class OutputWriter.
 *
 * @author Anwar
 */
public class OutputWriter {

	/**
	 * Write output.
	 *
	 * @param resultProbabilities the result probabilities
	 * @param outputFilePath the output file path
	 * @return true, if successful
	 */
	public boolean writeOutput(Map<EventNode, Float> resultProbabilities, String outputFilePath)
	{
		boolean isSuccessful = false;

		for(EventNode node : resultProbabilities.keySet()) 
		{
			System.out.print(node.getName() + " " + resultProbabilities.get(node));
		}

		// successful = true
		isSuccessful = true;

		return isSuccessful;
	}

}
