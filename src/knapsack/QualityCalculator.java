package knapsack;

public class QualityCalculator implements IQualityCalculator {

	@Override
	public float getQuality(final Knapsack p_knapsack) {
		return p_knapsack.getTotalProfit();
	}

}
