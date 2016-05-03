/**
 * 
 */
package com.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Class EventNode.
 *
 * @author Anwar
 */
public class EventNode {

	/** The name. */
	private String name;
	
	/** The parent nodes. */
	private List<EventNode> parentNodes;
	
	/** The child nodes. */
	private List<EventNode> childNodes;
	
	/** The value. */
	private boolean value;
	
	/** The probability value. */
	private ProbabilityValue probabilityValue;

	/**
	 * Instantiates a new event node.
	 *
	 * @param name the name
	 */
	public EventNode(String name) {
		this.name = name;
	}

	/**
	 * Gets the parent nodes.
	 *
	 * @return the parent nodes
	 */
	public List<EventNode> getParentNodes() {
		if(parentNodes == null) {
			parentNodes = new ArrayList<EventNode>();
		}
		return parentNodes;
	}

	/**
	 * Sets the parent nodes.
	 *
	 * @param parentNodes the new parent nodes
	 */
	public void setParentNodes(EventNode...parentNodes) {
		Collections.addAll(this.getParentNodes(), parentNodes);
	}

	/**
	 * Sets the parent nodes.
	 *
	 * @param parentNodes the new parent nodes
	 */
	public void setParentNodes(List<EventNode>parentNodes) {
		this.getParentNodes().addAll(parentNodes);
	}
	
	/**
	 * Gets the child nodes.
	 *
	 * @return the child nodes
	 */
	public List<EventNode> getChildNodes() {
		if(childNodes == null) {
			childNodes = new ArrayList<EventNode>();
		}
		return childNodes;
	}

	/**
	 * Sets the child nodes.
	 *
	 * @param childNodes the new child nodes
	 */
	public void setChildNodes(EventNode...childNodes) {
		Collections.addAll(this.getChildNodes(), childNodes);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Checks if is value.
	 *
	 * @return true, if is value
	 */
	public boolean isValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(boolean value) {
		this.value = value;
	}

	/**
	 * Gets the probability value.
	 *
	 * @return the probability value
	 */
	public float getProbabilityValue() {

		return probabilityValue.getProbability(this.getParentNodes());
	}

	/**
	 * Gets the probability with boolean value.
	 *
	 * @return the probability with boolean value
	 */
	public float getProbabilityWithBooleanValue() {

		float value = probabilityValue.getProbability(this.getParentNodes());

		if(!this.value)
		{
			value = 1-value;
		}
		return value;
	}

	/**
	 * Sets the probability value.
	 *
	 * @param probabilityValue the new probability value
	 */
	public void setProbabilityValue(ProbabilityValue probabilityValue) {
		this.probabilityValue = probabilityValue;
	}

	/**
	 * Sets the probability value.
	 *
	 * @param values the new probability value
	 */
	public void setProbabilityValue(Float...values) {

		assert(parentNodes == null || values.length == Math.pow(2, parentNodes.size())) : "Mismatch number of values and parents (3.1)";
		assert(parentNodes != null || values.length == 0) : "Mismatch number of values and parents (3.2)";

		List<Float> valuesList = new ArrayList<Float>();
		Collections.addAll(valuesList, values);

		this.probabilityValue = new ProbabilityValue(parentNodes, valuesList);
	}

	/**
	 * Sets the probability value.
	 *
	 * @param values the new probability value
	 */
	public void setProbabilityValue(List<Float> values) {

		assert(parentNodes == null || values.size() == Math.pow(2, parentNodes.size())) : "Mismatch number of values and parents (3.1)";
		assert(parentNodes != null || values.size() == 0) : "Mismatch number of values and parents (3.2)";


		this.probabilityValue = new ProbabilityValue(parentNodes, values);
	}

	/**
	 * Gets the probability value.
	 *
	 * @param parentValues the parent values
	 * @return the probability value
	 */
	public float getProbabilityValue(boolean...parentValues)
	{
		assert(parentNodes == null || parentValues.length == Math.pow(2, parentNodes.size())) : "Mismatch number of values and parents (4.1)";
		assert(parentNodes != null || parentValues.length == 0) : "Mismatch number of values and parents (4.2)";

		return probabilityValue.getProbability(parentValues);

	}

	/**
	 * Gets the boolean value.
	 *
	 * @return the boolean value
	 */
	public boolean getBooleanValue() {
		// TODO Auto-generated method stub
		return this.value;
	}
};
