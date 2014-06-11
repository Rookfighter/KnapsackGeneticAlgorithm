package knapsack.algorithm;

import knapsack.algorithm.interfaces.IFitnessCalculator;
import knapsack.container.KnapsackIndividuum;

public class FitnessCalculator implements IFitnessCalculator {

	@Override
	public double getFitness(final KnapsackIndividuum p_individuum) {
		return p_individuum.getTotalProfit();
	}

}
