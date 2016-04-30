package com.data;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds all the constants required by program.
 * 
 * @author anwar
 *
 */
public class Constant {

	public final Map<String, String> alogrithms = new HashMap<String, String>(){
		{
			//“e”, “p”, “r”, “l”
			
	        put("e", "Enumeration");
	        put("p", "Prior Sampling");
	        put("r", "Rejection Sampling");
	        put("l", "Likelyhood Weighting");
	}
	};
			
}
