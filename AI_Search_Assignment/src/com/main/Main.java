/**
 * 
 */
package com.main;

import com.constant.Constants;
import com.graph.Graph;
import com.graph.GraphBuilder;

/**
 * @author Anwar Shaikh
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GraphBuilder graphBuider = new GraphBuilder(Constants.INPUT_FILENAME);
		Graph graph = graphBuider.build();
	}

}
