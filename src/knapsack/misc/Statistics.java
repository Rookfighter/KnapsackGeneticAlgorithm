package knapsack.misc;

import java.util.Deque;
import java.util.LinkedList;

import knapsack.container.KnapsackProblem;
import knapsack.container.KnapsackTask;
import knapsack.container.Population;

public class Statistics {

	private Deque<StatisticProblemElement> problems;
	
	public Statistics() {
		problems = new LinkedList<StatisticProblemElement>();
	}
	
	public void nextProblem(KnapsackProblem p_problem) {
		problems.add(new StatisticProblemElement(p_problem));
	}
	
	public void nextTask(KnapsackTask p_task) {
		problems.getLast().tasks().add(new StatisticTaskElement(p_task));
	}
	
	public void nextGeneration(Population p_population) {
		problems.getLast().tasks().getLast().generations().add(new StatisticGenerationElement(p_population));
	}
	
	
	public void saveGnuPlotFile(String p_path) {
		
	}
	
}
