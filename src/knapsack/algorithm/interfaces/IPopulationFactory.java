package knapsack.algorithm.interfaces;

import knapsack.container.KnapsackTask;
import knapsack.container.Population;

public interface IPopulationFactory {

	Population generatePopulation(KnapsackTask p_task, final int p_size);
}
