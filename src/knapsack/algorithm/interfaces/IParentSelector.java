package knapsack.algorithm.interfaces;

import java.util.List;

import knapsack.container.Knapsack;
import knapsack.container.Population;

public interface IParentSelector {
	
	List<Knapsack> selectParents(Population p_population);
	
}
