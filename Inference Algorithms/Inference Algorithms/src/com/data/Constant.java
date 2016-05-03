package com.data;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds all the constants required by program.
 * 
 * @author Anwar
 *
 */
public class Constant {

	/** The Constant alogrithms. */
	public static final Map<String, String> alogrithms = new HashMap<String, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
	        put("e", "Enumeration");
	        put("p", "Prior Sampling");
	        put("r", "Rejection Sampling");
	        put("l", "Likelihood Weighting");
	}
	};
	
	/** The input file path. */
	public static String inputFilePath = "./input.txt";
	
	/** The output file path. */
	public static String outputFilePath = "./output.txt";
	
	/** The input split char. */
	public static String inputSplitChar = " ";
			
}
