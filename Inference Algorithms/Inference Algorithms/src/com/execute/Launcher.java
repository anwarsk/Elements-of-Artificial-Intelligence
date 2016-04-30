package com.execute;

import com.data.Constant;

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
		
		if(Constant.alogrithms.containsKey(algorithm))
		{
			
		}
		else
		{
			errorMessage = "Invalid algorithm specified.";
		}

	}

}
