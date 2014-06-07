package knapsack.algorithm.interfaces;

import knapsack.container.KnapsackProblem;
import knapsack.container.Population;

public interface IPopulationFactory {

	Population generatePopulation(final int p_size, KnapsackProblem p_problem);
}
