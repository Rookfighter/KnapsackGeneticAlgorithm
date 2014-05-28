package knapsack;

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
	
	public void addItem(KnapsackItem p_item) {
		items.add(p_item);
	}
	
	public KnapsackItem getItem(final int p_idx) {
		return items.get(p_idx);
	}
	
	public int itemCount() {
		return items.size();
	}
	
	public Knapsack getKnapsack() {
		return knapsack;
	}
	
	public KnapsackTask copy() {
		KnapsackTask result = new KnapsackTask(itemCount());
		
		result.setKnapsack(knapsack.copy());
		for(KnapsackItem item : items)
			result.addItem(item.copy());
		
		return result;
	}
	
	public List<KnapsackItem> createItemList() {
		List<KnapsackItem> result = new ArrayList<KnapsackItem>(items.size());
		for(KnapsackItem item : items)
			result.add(item.copy());
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
