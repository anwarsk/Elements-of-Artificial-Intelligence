/**
 * 
 */
package com.search;

import com.graph.Graph;
import com.graph.Node;
import com.graph.Path;

/**
 * @author Anwar Shaikh
 *
 */
public interface SearchAlgorithm {

	abstract public Path findPath(Node source, Node destination, Graph graph);
	
}
