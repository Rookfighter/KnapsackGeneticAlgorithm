package knapsack.container;

public class Population {

	private KnapsackIndividuum[] individuums;
	private KnapsackProblem problem;
	
	public Population(final int p_size, KnapsackProblem p_problem) {
		problem = p_problem;
		individuums = new KnapsackIndividuum[p_size];
		for(int i = 0; i < individuums.length; ++i)
			individuums[i] = new KnapsackIndividuum(problem);
	}
	
	public KnapsackIndividuum[] individuums() {
		return individuums;
	}
	
	public KnapsackProblem problem() {
		return problem;
	}
	
	public int getMeanWeight() {
		return getTotalWeight() / individuums.length;
	}
	
	public int getTotalWeight() {
		int result = 0;
		for(KnapsackIndividuum individuum : individuums)
			result += individuum.getTotalWeight();
		
		return result;
	}
	
	public int getMeanProfit() {
		return getTotalProfit() / individuums.length;
	}
	
	public int getTotalProfit() {
		int result = 0;
		for(KnapsackIndividuum individuum : individuums)
			result += individuum.getTotalProfit();
		
		return result;
	}
}
