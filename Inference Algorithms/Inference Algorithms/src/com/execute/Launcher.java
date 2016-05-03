package com.execute;

import java.util.Map;

import com.algorithm.InferenceAlgorithm;
import com.algorithm.InferenceAlogrithmFactory;
import com.data.BayesianNetwork;
import com.data.Constant;
import com.data.EventNode;
import com.io.InputReader;
import com.io.OutputWriter;

/**
 * This class responsible for launching the program and validating input 
 * parameters.
 * 
 * @author Anwar
 *
 */
public class Launcher {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		/*
		 * INPUT FORMAT: java my_class r<Algorithm_name> 100<No_of_samples>
		 */
		assert(args.length > 3): "Invalid number of input parameters";
		
		String errorMessage = "";
		String algorithm = args[0];
		long sampleCount = Long.parseLong(args[1]);
		
		if(Constant.alogrithms.containsKey(algorithm))
		{
			BayesianNetwork network = new BayesianNetwork();
			InferenceAlgorithm inferenceAlgorithm  = InferenceAlogrithmFactory.getAlgorithm(algorithm);
			
			
			/**
			 *  TO-TEST
			 */
//			algorithm = "e";
//		    long sampleCount = 1000;
//			Map<EventNode, Boolean> evidence = new HashMap<EventNode, Boolean>();
//			evidence.put(network.getEventNode("J"), true);
//			evidence.put(network.getEventNode("E"), false);
////			evidence.put(network.getEventNode("B"), false);
//			List<EventNode> query = new ArrayList<>();
//			query.add(network.getEventNode("B"));
//			query.add(network.getEventNode("M"));
			
			InputReader inputReader = new InputReader();
			if(inputReader.readInput(network, Constant.inputFilePath)) {
				Map<EventNode, Float> result = inferenceAlgorithm.infer(inputReader.getEvidence(),
															inputReader.getQuery(), sampleCount, network);
				
				OutputWriter outputWriter = new OutputWriter();
				outputWriter.writeOutput(result, Constant.outputFilePath);
			}
			
			/**
			 * TO-TEST
			 */
//			InferenceAlgorithm inferenceAlgorithm  = InferenceAlogrithmFactory.getAlgorithm(algorithm);
//			Map<EventNode, Float> result = inferenceAlgorithm.infer(evidence, query, sampleCount, network);
//			
//			System.out.println("******* SAMPLE SIZE : " + sample +" *******");
//			for(EventNode node : result.keySet())
//			{
//				System.out.println("NODE: " + node.getName() + "\t" + result.get(node));
//			}
//			InferenceAlgorithm inferenceAlgorithm  = InferenceAlogrithmFactory.getAlgorithm(algorithm);
//			Map<EventNode, Float> result = inferenceAlgorithm.infer(evidence, query, sampleCount, network);
//			
//				for(EventNode node : result.keySet())
//			{
//				System.out.println("NODE: " + node.getName() + "\t" + result.get(node));
//			}
		}
		else
		{
			errorMessage = "Invalid algorithm specified.";
		}
		
		System.out.println(errorMessage);
	}

}
