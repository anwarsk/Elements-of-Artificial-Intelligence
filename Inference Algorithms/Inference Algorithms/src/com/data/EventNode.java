/**
 * 
 */
package com.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Anwar
 *
 */
public class EventNode {

	private String name;
	private List<EventNode> parentNodes;
	private List<EventNode> childNodes;
	private boolean value;
	private ProbabilityValue probabilityValue;
	
	public EventNode(String name) {
		this.name = name;
	}
	
	public List<EventNode> getParentNodes() {
		return parentNodes;
	}
	
	public void setParentNodes(EventNode...parentNodes) {
		this.parentNodes = new ArrayList<EventNode>();
		Collections.addAll(this.parentNodes, parentNodes);
	}
	
	public List<EventNode> getChildNodes() {
		return childNodes;
	}
	
	public void setChildNodes(EventNode...childNodes) {
		this.childNodes = new ArrayList<EventNode>();
		Collections.addAll(this.childNodes, childNodes);
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public ProbabilityValue getProbabilityValue() {
		return probabilityValue;
	}

	public void setProbabilityValue(ProbabilityValue probabilityValue) {
		this.probabilityValue = probabilityValue;
	}
	
	public void setProbabilityValue(Float...values) {
		
		assert(parentNodes == null || values.length == Math.pow(2, parentNodes.size())) : "Mismatch number of values and parents (3.1)";
		assert(parentNodes != null || values.length == 0) : "Mismatch number of values and parents (3.2)";
		
		List<Float> valuesList = new ArrayList<Float>();
		Collections.addAll(valuesList, values);
		
		this.probabilityValue = new ProbabilityValue(parentNodes, valuesList);
	}	
}
