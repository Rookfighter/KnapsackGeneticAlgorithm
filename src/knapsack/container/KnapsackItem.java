package knapsack.container;

import java.util.Locale;

public class KnapsackItem {

	public float weight;
	public float profit;
	
	public KnapsackItem() {
		weight = 0;
		profit = 0;
	}
	
	public KnapsackItem(final float p_weight, final float p_value) {
		weight = p_weight;
		profit = p_value;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.US, "(%.2f,%.2f)", profit, weight);
	}
	public KnapsackItem copy() {
		return new KnapsackItem(weight, profit);
	}
	
}
