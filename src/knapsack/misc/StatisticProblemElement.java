package knapsack.misc;

import java.util.Deque;
import java.util.LinkedList;

import knapsack.container.KnapsackProblem;

public class StatisticProblemElement {
	
	private Deque<StatisticTaskElement> tasks;
	
	public StatisticProblemElement(KnapsackProblem p_problem) {
		tasks = new LinkedList<StatisticTaskElement>();
	}
	
	public Deque<StatisticTaskElement> tasks() {
		return tasks;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(StatisticTaskElement task: tasks) {
			sb.append(task.toString()).append("\n");
		}
		
		return sb.toString().trim();
	}
}
