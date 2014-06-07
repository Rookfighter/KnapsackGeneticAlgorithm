package knapsack.algorithm.interfaces;

import java.util.List;

import knapsack.container.Population;

public interface IToDieSelector {

	List<Integer> selectToDie(Population p_population, final int p_toDieCount);
	
}
