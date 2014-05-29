package knapsack.algorithm.interfaces;

import java.util.List;

import knapsack.container.Knapsack;
import knapsack.container.Population;

public interface IToDieSelector {

	List<Knapsack> selectToDie(Population p_population, final int p_toDieCount);
	
}
