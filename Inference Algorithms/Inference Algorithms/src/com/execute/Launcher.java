package com.execute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.algorithm.InferenceAlgorithm;
import com.algorithm.InferenceAlogrithmFactory;
import com.data.BayesianNetwork;
import com.data.Constant;
import com.data.EventNode;

/**
 * This class responsible for launching the program and validating input 
 * parameters.
 * 
 * @author Anwar
 *
 */
public class Launcher {

	public static void main(String[] args) {
		/*
		 * INPUT FORMAT: java my_class r<Algorithm_name> 100<No_of_samples>
		 */
		assert(args.length > 3): "Invalid number of input parameters";
		
		String errorMessage = "";
		String algorithm = args[1];
		algorithm = "r";
		if(Constant.alogrithms.containsKey(algorithm))
		{
			BayesianNetwork network = new BayesianNetwork();
			InferenceAlgorithm inferenceAlgorithm  = InferenceAlogrithmFactory.getAlgorithm(algorithm);
			/**
			 *  TO-TEST
			 */
			Map<EventNode, Boolean> evidence = new HashMap<EventNode, Boolean>();
			evidence.put(network.getEventNode("E"), true);
			evidence.put(network.getEventNode("J"), false);
			List<EventNode> query = new ArrayList<>();
			query.add(network.getEventNode("M"));
			query.add(network.getEventNode("A"));
			long sampleCount = 1000;
			
			Map<EventNode, Float> result = inferenceAlgorithm.infer(evidence, query, sampleCount, network);
			
			/**
			 * TO-TEST
			 */
			
			for(EventNode node : result.keySet())
			{
				System.out.println("NODE: " + node.getName() + "\t" + result.get(node));
			}
		}
		else
		{
			errorMessage = "Invalid algorithm specified.";
		}
		
		System.out.println(errorMessage);

	}

}
