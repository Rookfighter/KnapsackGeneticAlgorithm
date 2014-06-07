package knapsack.algorithm.interfaces;

import java.util.List;

import knapsack.container.KnapsackIndividuum;
import knapsack.container.Population;

public interface ICrossover {
	List<KnapsackIndividuum> crossover(List<Integer> p_parents, Population p_population);
}
