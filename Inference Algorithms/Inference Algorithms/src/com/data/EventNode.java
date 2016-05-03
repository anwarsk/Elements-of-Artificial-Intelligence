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
		if(parentNodes == null) {
			parentNodes = new ArrayList<EventNode>();
		}
		return parentNodes;
	}

	public void setParentNodes(EventNode...parentNodes) {
		Collections.addAll(this.getParentNodes(), parentNodes);
	}

	public void setParentNodes(List<EventNode>parentNodes) {
		this.getParentNodes().addAll(parentNodes);
	}
	
	public List<EventNode> getChildNodes() {
		if(childNodes == null) {
			childNodes = new ArrayList<EventNode>();
		}
		return childNodes;
	}

	public void setChildNodes(EventNode...childNodes) {
		Collections.addAll(this.getChildNodes(), childNodes);
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

	public float getProbabilityValue() {

		return probabilityValue.getProbability(this.getParentNodes());
	}

	public float getProbabilityWithBooleanValue() {

		float value = probabilityValue.getProbability(this.getParentNodes());

		if(!this.value)
		{
			value = 1-value;
		}
		return value;
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

	public void setProbabilityValue(List<Float> values) {

		assert(parentNodes == null || values.size() == Math.pow(2, parentNodes.size())) : "Mismatch number of values and parents (3.1)";
		assert(parentNodes != null || values.size() == 0) : "Mismatch number of values and parents (3.2)";


		this.probabilityValue = new ProbabilityValue(parentNodes, values);
	}

	public float getProbabilityValue(boolean...parentValues)
	{
		assert(parentNodes == null || parentValues.length == Math.pow(2, parentNodes.size())) : "Mismatch number of values and parents (4.1)";
		assert(parentNodes != null || parentValues.length == 0) : "Mismatch number of values and parents (4.2)";

		return probabilityValue.getProbability(parentValues);

	}

	public boolean getBooleanValue() {
		// TODO Auto-generated method stub
		return this.value;
	}
};
