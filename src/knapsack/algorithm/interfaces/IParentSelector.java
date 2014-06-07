package knapsack.algorithm.interfaces;

import java.util.List;

import knapsack.container.Population;

public interface IParentSelector {
	
	List<Integer> selectParents(Population p_population);
	
}
