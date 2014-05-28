package knapsack;

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
	public boolean equals(Object p_object) {
		if(!(p_object instanceof KnapsackItem))
			return false;
		
		KnapsackItem item = (KnapsackItem) p_object;
		
		return weight == item.weight && profit == item.profit;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		
		result = result * 17 + (int) weight;
		result = result * 31 + (int) profit;
		result *= 13;
		
		return result;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.US, "(%.2f,%.2f)", profit, weight);
	}
	public KnapsackItem copy() {
		return new KnapsackItem(weight, profit);
	}
	
}
