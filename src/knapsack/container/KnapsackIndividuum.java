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
	
	public KnapsackProblem problem() {
		return problem;
	}
	
	public double getTotalProfit() {
		double result = 0;
		for(int i = 0; i < qualities.length; ++i)
			result += getProfit(i);
		
		return result;
	}
	
	public double getProfit(final int p_partProblem) {
		double result = 0;
		for(int i = 0; i < qualities[p_partProblem].length; ++i) {
			if(qualities[p_partProblem][i])
				result += problem.partProblems()[p_partProblem].items()[i].profit;
		}
		
		return result;
	}
	
	public double getTotalWeight() {
		double result = 0;
		for(int i = 0; i < qualities.length; ++i)
			result += getWeight(i);
		
		return result;
	}
	
	public double getWeight(final int p_partProblem) {
		double result = 0;
		for(int i = 0; i < qualities[p_partProblem].length; ++i) {
			if(qualities[p_partProblem][i])
				result += problem.partProblems()[p_partProblem].items()[i].weight;
		}
		
		return result;
	}
	
	public boolean setQuality(final int p_partProblem, final int p_item, final boolean p_value) {
		if(isInvalidItem(p_partProblem, p_item))
			return false;
		
		if(p_value && usesItem(p_item))
			return false;
		
		qualities[p_partProblem][p_item] = p_value;
		
		if(p_value && isOverLimit(p_partProblem)) {
			qualities[p_partProblem][p_item] = !p_value;
			return false;
		}
		
		return true;
	}
	
	public boolean isOverLimit(final int p_partProblem) {
		return getWeight(p_partProblem) > problem.partProblems()[p_partProblem].knappsackSize();
	}
	
	public boolean usesItem(final int p_item) {
		return indexOfItemUser(p_item) >= 0;
	}
	
	public boolean hasItemDuplicate(final int p_item) {
		int index = indexOfItemUser(p_item);
		if(index < 0)
			return false;
		
		for(int i = index + 1; i < qualities.length; ++i) {
			if(qualities[i][p_item])
				return true;
		}
		
		return false;
	}
	
	public int indexOfItemUser(final int p_item) {
		for(int i = 0; i < qualities.length; ++i) {
			if(qualities[i][p_item])
				return i;
		}
		
		return -1;
	}
	
	public void applyToRules() {
		removeDuplicateItems();
		lowerDownToLimitWeight();
	}
	
	public void lowerDownToLimitWeight() {
		for(int i = 0; i < qualities().length; ++i) {
			while(isOverLimit(i)) {
				int item = getLowestProfitItem(i);
				qualities()[i][item] = false;
			}
		}
	}
	
	public int getLowestProfitItem(final int p_partProblem) {
		int result = -1;
		for(int i = 0; i < qualities[p_partProblem].length; ++i) {
			if(qualities[p_partProblem][i]) {
				if(result < 0)
					result = i;
				else if (problem.partProblems()[p_partProblem].items()[i].profit < problem.partProblems()[p_partProblem].items()[result].profit)
					result = i;
			}
		}
		
		return result;
	}
	
	public void removeDuplicateItems() {
		for(int i = 0; i < qualities[0].length; ++i) {
			while(hasItemDuplicate(i)) {
				int partProblem = getHighestWeightPartProblem(i);
				qualities[partProblem][i] = false;
			}
		}
	}
	
	public int getHighestWeightPartProblem(final int p_item) {
		int result = -1;
		for(int i = 0; i < qualities.length; ++i) {
			if(qualities[i][p_item]) {
				if(result < 0)
					result = i;
				else if (problem.partProblems()[i].items()[p_item].weight > problem.partProblems()[result].items()[p_item].weight)
					result = i;
			}
		}
		
		return result;
	}
	
	public void apply(KnapsackIndividuum p_individuum) {
		qualities = p_individuum.qualities;
	}
	
	public boolean isInvalidItem(final int p_partProblem, final int p_item) {
		return Math.abs(problem.partProblems()[p_partProblem].items()[p_item].weight) < 0.5;
	}
}
