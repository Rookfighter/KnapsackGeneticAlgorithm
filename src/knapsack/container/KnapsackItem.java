package knapsack.container;

import java.util.Locale;

public class KnapsackItem {

	public double weight;
	public double profit;
	
	public KnapsackItem() {
		weight = 0;
		profit = 0;
	}
	
	public KnapsackItem(final double p_weight, final double p_value) {
		weight = p_weight;
		profit = p_value;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.US, "(%.2f,%.2f)", profit, weight);
	}
	
}
