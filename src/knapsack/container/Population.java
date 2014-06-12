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
	
	public float getMaxProfit() {
		float result = -1;
		for(int i = 0; i < individuums.length; ++i) {
			float individuumProfit = individuums[i].getProfit();
			if(individuumProfit > result)
				result = individuumProfit;
		}
		
		return result;
	}
	
	public float getMeanProfit() {
		return getTotalProfit() / individuums.length;
	}
	
	public float getTotalProfit() {
		float result = 0;
		for(KnapsackIndividuum individuum : individuums)
			result += individuum.getProfit();
		
		return result;
	}
}
