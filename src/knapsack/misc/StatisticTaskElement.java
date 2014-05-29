package knapsack.misc;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;

import knapsack.container.KnapsackTask;

public class StatisticTaskElement {

	private Deque<StatisticGenerationElement> generations;
	private float maxSackWeight;
	
	public StatisticTaskElement(KnapsackTask p_task) {
		generations = new LinkedList<StatisticGenerationElement>();
		maxSackWeight = p_task.getKnapsack().maxWeight;
	}
	
	public Deque<StatisticGenerationElement> generations() {
		return generations;
	}
	
	public float maxSackWeight() {
		return maxSackWeight;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(String.format(Locale.US, "[%.2f]", maxSackWeight));
		
		int i = 0;
		for(StatisticGenerationElement generation: generations) {
			sb.append(String.format("G-%03d:", i)).append(generation.toString()).append(",");
			i++;
		}
		
		int index = sb.length() - 1;
		if(sb.charAt(index) == ',')
			sb.deleteCharAt(index);
		
		return sb.toString();
	}
	
}
