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
	
	public double getHighestIndividuumProfit() {
		double result = -1;
		for(int i = 0; i < individuums.length; ++i) {
			double individuumProfit = individuums[i].getTotalProfit();
			if(individuumProfit > result)
				result = individuumProfit;
		}
		
		return result;
	}
	
	public double getHighestKnapsackProfit() {
		double result = -1;
		for(int i = 0; i < individuums.length; ++i) {
			for(int j = 0; j < individuums[i].problem().partProblems().length; ++j) {
				double knapsackProfit = individuums[i].getProfit(j);
				if(knapsackProfit > result)
					result = knapsackProfit;
			}
		}
		
		return result;
	}
	
	public double getWeightRatio() {
		double result = 0;
		for(int i = 0; i < individuums.length; ++i) {
			double tmp = 0;
			for(int j = 0; j < individuums[i].problem().partProblems().length; ++j) {
				tmp += (individuums[i].getWeight(j) / individuums[i].problem().partProblems()[j].knappsackSize());
			}
			result += tmp / (individuums[i].problem().partProblems().length);
		}
		
		return result / individuums.length;
	}
	
	public double getMeanWeight() {
		return getTotalWeight() / individuums.length;
	}
	
	public double getTotalWeight() {
		double result = 0;
		for(KnapsackIndividuum individuum : individuums)
			result += individuum.getTotalWeight();
		
		return result;
	}
	
	public double getMeanProfit() {
		return getTotalProfit() / individuums.length;
	}
	
	public double getTotalProfit() {
		double result = 0;
		for(KnapsackIndividuum individuum : individuums)
			result += individuum.getTotalProfit();
		
		return result;
	}
}
