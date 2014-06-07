package knapsack.container;

import java.util.Locale;

public class KnapsackPartProblem {

	private int knappSackSize;
	private KnapsackItem[] items;
	
	public KnapsackPartProblem(final int p_size) {
		knappSackSize = 0;
		items = new KnapsackItem[p_size];
		for(int i = 0; i < items.length; ++i)
			items[i] = new KnapsackItem();
	}
	
	public int knappsackSize() {
		return knappSackSize;
	}
	
	public void setKnappsackSize(final int p_knapsackSize) {
		knappSackSize = p_knapsackSize;
	}
	
	public KnapsackItem[] items() {
		return items;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(Locale.US, "%.2f:", knappSackSize));
		
		for(KnapsackItem item : items)
			sb.append(item).append(",");
		
		if(sb.charAt(sb.length() - 1) == ',')
			sb.deleteCharAt(sb.length() - 1);
		
		return sb.toString();
	}
}
