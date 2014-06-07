package knapsack.container;

public class KnapsackIndividuum {

	private KnapsackProblem problem;
	private boolean[][] qualities;
			
	public KnapsackIndividuum(KnapsackProblem p_problem) {
		problem = p_problem;
		
		// create array of qualities of individuum
		qualities = new boolean[problem.partProblems().length][];
		for(int i = 0; i < qualities.length; ++i)
			qualities[i] = new boolean[problem.partProblems()[i].items().length];
	}
	
	public boolean[][] qualities() {
		return qualities;
	}
	
	public int getTotalProfit() {
		int result = 0;
		for(int i = 0; i < qualities.length; ++i)
			result += getProfit(i);
		
		return result;
	}
	
	public int getProfit(final int p_index) {
		int result = 0;
		for(int i = 0; i < qualities[p_index].length; ++i) {
			if(qualities[p_index][i])
				result += problem.partProblems()[p_index].items()[i].profit;
		}
		
		return result;
	}
	
	public int getTotalWeight() {
		int result = 0;
		for(int i = 0; i < qualities.length; ++i)
			result += getWeight(i);
		
		return result;
	}
	
	public int getWeight(final int p_index) {
		int result = 0;
		for(int i = 0; i < qualities[p_index].length; ++i) {
			if(qualities[p_index][i])
				result += problem.partProblems()[p_index].items()[i].weight;
		}
		
		return result;
	}
	
	public boolean isOverLimit(final int p_index) {
		return getWeight(p_index) > problem.partProblems()[p_index].knappsackSize();
	}
	
	public void apply(KnapsackIndividuum p_individuum) {
		qualities = p_individuum.qualities;
	}
}
