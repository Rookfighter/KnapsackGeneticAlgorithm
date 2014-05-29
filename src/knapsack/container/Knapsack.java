package knapsack.container;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Knapsack {

	public float maxWeight;
	
	private List<KnapsackItem> items;
	
	public Knapsack() {
		maxWeight = 0;
		items = new LinkedList<KnapsackItem>();
	}
	
	public Knapsack(final float p_maxWeight) {
		maxWeight = p_maxWeight;
		items = new LinkedList<KnapsackItem>();
	}
	
	public List<KnapsackItem> items() {
		return items;
	}
	
	public float getTotalProfit() {
		float totalProfit = 0;
		for(KnapsackItem item: items)
			totalProfit += item.profit;
		
		return totalProfit;
	}
	
	public float getTotalWeight() {
		float totalWeight = 0;
		for(KnapsackItem item: items)
			totalWeight += item.weight;
		
		return totalWeight;
	}
	
	public boolean overLimit() {
		return getTotalWeight() > maxWeight;
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

	public Knapsack copy() {
		Knapsack result = new Knapsack(maxWeight);
		for(KnapsackItem item : items)
			result.items.add(item.copy());
		
		return result;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format(Locale.US, "(%.2f/%.2f,%.2f):", getTotalWeight(), maxWeight, getTotalProfit()));
		
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
