package knapsack.container;

import java.util.ArrayList;
import java.util.List;

public class KnapsackTask {

	private Knapsack knapsack;
	private List<KnapsackItem> items;
	
	
	public KnapsackTask(final int p_itemCount) {
		knapsack = new Knapsack();
		items = new ArrayList<KnapsackItem>(p_itemCount);
	}
	
	public void setKnapsack(Knapsack p_knapsack) {
		knapsack = p_knapsack;
	}
	
	public List<KnapsackItem> items() {
		return items;
	}
	
	public Knapsack getKnapsack() {
		return knapsack;
	}
	
	public KnapsackTask copy() {
		KnapsackTask result = new KnapsackTask(items.size());
		
		result.setKnapsack(knapsack.copy());
		for(KnapsackItem item : items)
			result.items.add(item.copy());
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(knapsack.toString()).append("##");
		
		sb.append("{");
		for(KnapsackItem item : items) {
			sb.append(item.toString()).append(",");
		}
		if(sb.charAt(sb.length() - 1) == ',')	
			sb.deleteCharAt(sb.length() - 1);
		sb.append("}");
		
		return sb.toString();	
	}
}
