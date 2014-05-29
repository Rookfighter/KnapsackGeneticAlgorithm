package knapsack.algorithm.interfaces;

import knapsack.container.KnapsackTask;
import knapsack.container.Population;

public interface IMutator {

	void mutate(Population p_population, KnapsackTask p_task);
}
