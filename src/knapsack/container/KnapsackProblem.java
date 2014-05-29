package knapsack.container;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem {
	
	private List<KnapsackTask> tasks;
	
	public KnapsackProblem(final int p_taskCount) {
		tasks = new ArrayList<KnapsackTask>(p_taskCount);
	}
	
	public List<KnapsackTask> tasks() {
		return tasks;
	}
	
	public KnapsackProblem copy() {
		KnapsackProblem result = new KnapsackProblem(tasks.size());
		
		for(KnapsackTask task: tasks)
			result.tasks.add(task.copy());
		
		return result;
	}
	
}
