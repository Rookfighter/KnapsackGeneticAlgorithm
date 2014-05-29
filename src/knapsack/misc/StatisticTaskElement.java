package knapsack.misc;

import java.util.Deque;
import java.util.LinkedList;

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
	
}
