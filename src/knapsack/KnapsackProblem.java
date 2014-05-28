package knapsack;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem {
	
	private List<KnapsackTask> tasks;
	
	public KnapsackProblem(final int p_taskCount) {
		tasks = new ArrayList<KnapsackTask>(p_taskCount);
	}
	
	public void addTask(KnapsackTask p_task) {
		tasks.add(p_task);
	}
	
	public KnapsackTask getTask(final int p_idx) {
		return tasks.get(p_idx);
	}
	
	public int taskCount() {
		return tasks.size();
	}
	
	public KnapsackProblem copy() {
		KnapsackProblem result = new KnapsackProblem(taskCount());
		
		for(KnapsackTask task: tasks)
			result.addTask(task.copy());
		
		return result;
	}
	
}
