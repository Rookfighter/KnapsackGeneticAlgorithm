package knapsack.container;

import java.util.LinkedList;
import java.util.List;

public class Population {

	private List<Knapsack> individuums;
	private int populationSize;
	
	public Population(final int p_size) {
		individuums = new LinkedList<Knapsack>();
		populationSize = p_size;
	}
	
	public List<Knapsack> individuums() {
		return individuums;
	}
	
	public int getPopulationSize() {
		return populationSize;
	}
	
	public float getMeanWeight() {
		return getTotalWeight() / individuums.size();
	}
	
	public float getTotalWeight() {
		float result = 0;
		for(Knapsack individuum : individuums)
			result += individuum.getTotalWeight();
		
		return result;
	}
	
	public float getMeanProfit() {
		return getTotalProfit() / individuums.size();
	}
	
	public float getTotalProfit() {
		float result = 0;
		for(Knapsack individuum : individuums)
			result += individuum.getTotalProfit();
		
		return result;
	}
	
	public Population copy() {
		Population result = new Population(populationSize);
		for(Knapsack individuum : individuums)
			result.individuums.add(individuum.copy());
		
		return result;
	}
}
