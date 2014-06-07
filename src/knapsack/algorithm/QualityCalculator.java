package knapsack.algorithm;

import knapsack.algorithm.interfaces.IQualityCalculator;
import knapsack.container.KnapsackIndividuum;

public class QualityCalculator implements IQualityCalculator {

	@Override
	public int getQuality(final KnapsackIndividuum p_individuum) {
		return p_individuum.getTotalProfit();
	}

}
