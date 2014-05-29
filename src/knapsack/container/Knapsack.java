package knapsack.container;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Knapsack {

	public float maxWeight;
	private float currentWeight;
	private float currentProfit;
	
	private List<KnapsackItem> items;
	
	public Knapsack() {
		maxWeight = 0;
		currentWeight = 0;
		currentProfit = 0;
		items = new LinkedList<KnapsackItem>();
	}
	
	public Knapsack(final float p_maxWeight) {
		maxWeight = p_maxWeight;
		currentWeight = 0;
		currentProfit = 0;
		items = new LinkedList<KnapsackItem>();
	}
	
	public int count() {
		return items.size();
	}
	
	public float getTotalProfit() {
		return currentProfit;
	}
	
	public float getTotalWeight() {
		return currentWeight;
	}
	
	public boolean overLimit() {
		return currentWeight > maxWeight;
	}
	
	public boolean containsItem(KnapsackItem p_item) {
		return items.contains(p_item);
	}
	
	public void add(KnapsackItem p_item) {
		if(items.add(p_item)) {
			currentWeight += p_item.weight;
			currentProfit += p_item.profit;
		}
	}
	
	public void remove(KnapsackItem p_item) {
		if(items.remove(p_item)) {
			currentWeight -= p_item.weight;
			currentProfit -= p_item.profit;
		}
	}
	
	public void remove(final int p_index) {
		KnapsackItem removed = items.remove(p_index);
		if(removed != null) {
			currentWeight -= removed.weight;
			currentProfit -= removed.profit;
		}
	}
	
	public void removeItems(List<KnapsackItem> p_items) {
		for(KnapsackItem item : p_items)
			remove(item);
	}
	
	public List<KnapsackItem> getItems() {
		return items;
	}
	
	public void removeLowestProfitItem() {
		if(items.isEmpty())
			return;
		
		KnapsackItem toRemove = items.get(0);
		for(KnapsackItem item : items)
			if(item.profit < toRemove.profit)
				toRemove = item;
		
		items.remove(toRemove);
	}
	
	public void removeHighestWeightItem() {
		if(items.isEmpty())
			return;
		
		KnapsackItem toRemove = items.get(0);
		for(KnapsackItem item : items)
			if(item.weight > toRemove.weight)
				toRemove = item;
		
		items.remove(toRemove);
	}
	
	public KnapsackItem getItem(final int p_idx) {
		return items.get(p_idx);
	}
	
	public Knapsack copy() {
		Knapsack result = new Knapsack(maxWeight);
		for(KnapsackItem item : items)
			result.add(item.copy());
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format(Locale.US, "(%.2f/%.2f,%.2f):", currentWeight, maxWeight, currentProfit));
		
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
