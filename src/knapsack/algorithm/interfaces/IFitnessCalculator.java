package knapsack.algorithm.interfaces;

import knapsack.container.KnapsackIndividuum;

public interface IFitnessCalculator {

	float getFitness(final KnapsackIndividuum p_individuum);
	
}
