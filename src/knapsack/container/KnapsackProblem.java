package knapsack.container;

import java.util.Locale;


public class KnapsackProblem {
	
	private KnapsackItem[] items;
	private float[] maxConstraints;
	
	public KnapsackProblem(final int p_constraintCount, final int p_itemCount) {
		items = new KnapsackItem[p_itemCount];
		for(int i = 0; i < items.length; ++i)
			items[i] = new KnapsackItem(p_constraintCount);
		
		maxConstraints = new float[p_constraintCount];
	}
	
	public KnapsackItem[] items() {
		return items;
	}
	
	public float[] maxConstraints() {
		return maxConstraints;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < maxConstraints.length; ++i) {
			sb.append(String.format(Locale.US,"%.2f:", maxConstraints[i]));
			
			for(int j = 0; j < items.length; ++j)
				sb.append(items[j].toString(i));
			
			sb.append("\n");
		}
		
		return sb.toString().trim();
	}
	
}
