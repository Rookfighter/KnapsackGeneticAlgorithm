package knapsack.algorithm.interfaces;

import knapsack.container.KnapsackIndividuum;

public interface IFitnessCalculator {

	double getFitness(final KnapsackIndividuum p_individuum);
	
}
