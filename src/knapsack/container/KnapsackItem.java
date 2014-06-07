package knapsack.container;

import java.util.Locale;

public class KnapsackItem {

	public int weight;
	public int profit;
	
	public KnapsackItem() {
		weight = 0;
		profit = 0;
	}
	
	public KnapsackItem(final int p_weight, final int p_value) {
		weight = p_weight;
		profit = p_value;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.US, "(%.2f,%.2f)", profit, weight);
	}
	
}
