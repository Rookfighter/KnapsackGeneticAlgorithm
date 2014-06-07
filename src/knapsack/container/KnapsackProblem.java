package knapsack.container;


public class KnapsackProblem {
	
	private KnapsackPartProblem[] partProblems;
	
	public KnapsackProblem(final int p_partProblemCount, final int p_itemCount) {
		partProblems = new KnapsackPartProblem[p_partProblemCount];
		for(int i = 0; i < partProblems.length; ++i)
			partProblems[i] = new KnapsackPartProblem(p_itemCount);
	}
	
	public KnapsackPartProblem[] partProblems() {
		return partProblems;
	}
}
