/**
 * 
 */
package com.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.data.EventNode;

/**
 * @author Anwar
 *
 */
public class OutputWriter {
	
	public boolean writeOutput(Map<EventNode, Float> resultProbabilities, String outputFilePath)
	{
		boolean isSuccessful = false;
		BufferedWriter bw = null;
		try {
			File file = new File(outputFilePath);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			for(EventNode node : resultProbabilities.keySet()) {
				bw.write(node.getName() + "\t" + resultProbabilities.get(node) + "\n");
				System.out.println("NODE: " + node.getName() + "\t" + resultProbabilities.get(node));
			}
			
			// successful = true
			isSuccessful = true;
		} catch (IOException ex) {
			System.err.println("Exception writing output to file: " + ex);
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException ex) {
					System.err.println("Exception closing output stream: " + ex);
				}
			}
		}
		return isSuccessful;
	}

}
