package com.search;

import com.constant.Constants;

public class SearchAlgorithmFactory {
	
	public static SearchAlgorithm getSearchAlgorithm(String name)
	{
		SearchAlgorithm searchAlgorithm =  null;
		
		if(name.equalsIgnoreCase("DFS"))
		{
			searchAlgorithm = new DFS();
		}
		else if(name.equalsIgnoreCase("BFS"))
		{
			searchAlgorithm = new BFS();
		}
		else if(name.equalsIgnoreCase("IDDFS"))
		{
			searchAlgorithm = new IterativeDeepening(Constants.ITERATIVE_DEEPENING_STEP);
		}
		
		return searchAlgorithm;
	}

}
