/**
 * 
 */
package com.io;

import java.util.List;
import java.util.Map;

import com.data.BayesianNetwork;
import com.data.Constant;
import com.data.EventNode;

/**
 * @author Anwar
 *
 */
public class InputReader {

	private Map<EventNode, Boolean> evidence;
	private List<EventNode> Query;
	
	public boolean readInput(BayesianNetwork network, String inputFilePath)
	{
		boolean isSuccessful = false;
		
	
		
		return isSuccessful;
	}

	public Map<EventNode, Boolean> getEvidence() {
		return evidence;
	}

	public List<EventNode> getQuery() {
		return Query;
	}
	
	
	
}
