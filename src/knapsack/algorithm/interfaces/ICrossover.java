package knapsack.algorithm.interfaces;

import java.util.List;

import knapsack.container.Knapsack;

public interface ICrossover {
	List<Knapsack> crossover(List<Knapsack> p_parents);
}
