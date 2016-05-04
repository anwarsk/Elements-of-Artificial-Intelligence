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

		int index = 0;
		for(EventNode node : resultProbabilities.keySet()) 
		{
			// Done this modification to adhere the output format specially avoiding the new line character by println(..) function
			if(index > 0)
			{
				System.out.print("\n");
			}
			System.out.print(node.getName() + " " + resultProbabilities.get(node));
			index++;
		}
		
		
		// successful = true
		isSuccessful = true;

		return isSuccessful;
	}

}
