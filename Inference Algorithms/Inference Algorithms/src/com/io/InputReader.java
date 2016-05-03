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
 * The Class InputReader.
 *
 * @author Anwar
 */
public class InputReader {

	/** The evidence. */
	private Map<EventNode, Boolean> evidence;
	
	/** The query. */
	private List<EventNode> query;
	
	/** The sample count. */
	private long sampleCount;
	
	/**
	 * Read input.
	 *
	 * @param network the network
	 * @param inputFilePath the input file path
	 * @return true, if successful
	 */
	public boolean readInput(BayesianNetwork network, String inputFilePath)
	{
		return this.readConsoleInput(network);
	}

	/**
	 * Gets the evidence.
	 *
	 * @return the evidence
	 */
	public Map<EventNode, Boolean> getEvidence() {
		if(evidence == null) {
			evidence = new HashMap<EventNode, Boolean>();
		}
		return evidence;
	}

	/**
	 * Gets the query.
	 *
	 * @return the query
	 */
	public List<EventNode> getQuery() {
		if(query == null) {
			query = new ArrayList<EventNode>();
		}
		return query;
	}
	
	/**
	 * Gets the sample count.
	 *
	 * @return the sample count
	 */
	public long getSampleCount() {
		return sampleCount;
	}
	
	/**
	 * Read console input.
	 *
	 * @param network the network
	 * @return true, if successful
	 */
	private boolean readConsoleInput(BayesianNetwork network) {
		boolean isSuccessful = false;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			// read number of evidence & query variables
			String numParams = br.readLine();
			
			int numEvidence = Integer.parseInt(numParams.split(Constant.inputSplitChar)[0]);
			int numQuery = Integer.parseInt(numParams.split(Constant.inputSplitChar)[1]);
			
			// read evidence variables as input
			while(numEvidence-- != 0) {
				String evidenceVars = br.readLine();
				String evidenceName = evidenceVars.split(Constant.inputSplitChar)[0];
				String evidenceValue = evidenceVars.split(Constant.inputSplitChar)[1];
				
				this.getEvidence().put(network.getEventNode(evidenceName), 
										(evidenceValue == "t" || evidenceName== "T"));
			}
			
			// read query variables as input
			while(numQuery-- != 0) {
				String queryVar = br.readLine();
				this.getQuery().add(network.getEventNode(queryVar));
			}
			
			// set iSuccessfull flag
			isSuccessful = true;
		} catch(Exception ex) {
			System.err.println("Exception during input processing: " + ex);
		}
		return isSuccessful;
	}
	
}
