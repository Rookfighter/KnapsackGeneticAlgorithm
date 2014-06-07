package knapsack.algorithm.interfaces;

import knapsack.container.KnapsackIndividuum;

public interface IQualityCalculator {

	int getQuality(final KnapsackIndividuum p_individuum);
	
}
