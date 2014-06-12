package knapsack.container;

import java.util.Locale;

public class KnapsackIndividuum {

	private KnapsackProblem problem;
	private boolean[] qualities;
			
	public KnapsackIndividuum(KnapsackProblem p_problem) {
		problem = p_problem;
		
		// create array of qualities of individuum
		qualities = new boolean[problem.items().length];
	}
	
	public boolean[] qualities() {
		return qualities;
	}
	
	public KnapsackProblem problem() {
		return problem;
	}
	
	public float getProfit() {
		float result = 0;
		for(int i = 0; i < qualities.length; ++i) {
			if(qualities[i])
				result += problem.items()[i].profit;
		}
		
		return result;
	}
	
	public boolean setQuality(final int p_item, final boolean p_value) {
		if(p_value && usesItem(p_item))
			return false;
		
		qualities[p_item] = p_value;
		
		if(p_value && isOverLimit()) {
			qualities[p_item] = !p_value;
			return false;
		}
		
		return true;
	}
	
	public boolean usesItem(final int p_item) {
		return qualities[p_item];
	}
	
	public boolean isOverLimit() {
		for(int i = 0; i < problem.maxConstraints().length; ++i) {
			if(getConstraintSum(i) > problem.maxConstraints()[i])
				return true;
		}
		
		return false;
	}
	
	public float getConstraintSum(final int p_constraint) {
		float sum = 0;
		// iterate over all items and sum up the values of the specified constraint
		for(int i = 0; i < problem.items().length; ++i) {
			if(qualities[i])
				sum += problem.items()[i].constraints()[p_constraint];
		}
		
		return sum;
	}
	
	public void apply(KnapsackIndividuum p_individuum) {
		qualities = p_individuum.qualities;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.US, "(%.2f)", getProfit());
	}
}
