package knapsack.misc;

import java.util.Deque;
import java.util.LinkedList;

import knapsack.container.KnapsackProblem;

public class StatisticProblemElement {
	
	private Deque<StatisticGenerationElement> generations;
	
	public StatisticProblemElement(KnapsackProblem p_problem) {
		generations = new LinkedList<StatisticGenerationElement>();
	}
	
	public Deque<StatisticGenerationElement> generations() {
		return generations;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(StatisticGenerationElement gen: generations) {
			sb.append(gen.toString()).append("\n");
		}
		
		return sb.toString().trim();
	}
}
