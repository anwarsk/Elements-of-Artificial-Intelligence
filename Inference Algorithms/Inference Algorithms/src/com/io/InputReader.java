/**
 * 
 */
package com.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
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
	private List<EventNode> query;
	private long sampleCount;
	
	public boolean readInput(BayesianNetwork network, String inputFilePath)
	{
		return this.readConsoleInput(network);
	}

	public Map<EventNode, Boolean> getEvidence() {
		if(evidence == null) {
			evidence = new HashMap<EventNode, Boolean>();
		}
		return evidence;
	}

	public List<EventNode> getQuery() {
		if(query == null) {
			query = new ArrayList<EventNode>();
		}
		return query;
	}
	
	public long getSampleCount() {
		return sampleCount;
	}
	
	private boolean readConsoleInput(BayesianNetwork network) {
		boolean isSuccessful = false;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// read number of evidence & query variables
			System.out.println("Enter the number of evidence & query variables. Eg: 2 1");
			String numParams = br.readLine();
			
			int numEvidence = Integer.parseInt(numParams.split(Constant.inputSplitChar)[0]);
			int numQuery = Integer.parseInt(numParams.split(Constant.inputSplitChar)[1]);
			
			// read evidence variables as input
			System.out.println("Enter the evidence variables, each on new line (Eg: E t).");
			while(numEvidence-- != 0) {
				String evidenceVars = br.readLine();
				String evidenceName = evidenceVars.split(Constant.inputSplitChar)[0];
				String evidenceValue = evidenceVars.split(Constant.inputSplitChar)[1];
				
				this.getEvidence().put(network.getEventNode(evidenceName), 
										(evidenceValue == "t" || evidenceName== "T"));
			}
			
			// read query variables as input
			System.out.println("Enter the query variables, each on new line (Eg: J).");
			while(numQuery-- != 0) {
				String queryVar = br.readLine();
				this.getQuery().add(network.getEventNode(queryVar));
			}
			
			// successfull = true
			isSuccessful = true;
		} catch(Exception ex) {
			System.err.println("Exception during input processing: " + ex);
		}
		return isSuccessful;
	}
	
}
