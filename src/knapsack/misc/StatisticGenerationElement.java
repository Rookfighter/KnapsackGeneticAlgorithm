package knapsack.misc;

import knapsack.container.Population;

public class StatisticGenerationElement {

	private float totalWeight;
	private float totalProfit;
	
	private float meanWeight;
	private float meanProfit;
	
	public StatisticGenerationElement(Population p_population) {
		totalWeight = p_population.getTotalWeight();
		totalProfit = p_population.getTotalProfit();
		
		meanWeight = p_population.getMeanWeight();
		meanProfit = p_population.getMeanProfit();
	}
	
	public float totalWeight() {
		return totalWeight;
	}
	
	public float totalProfit() {
		return totalProfit;
	}
	
	public float meanWeight() {
		return meanWeight;
	}
	
	public float meanProfit() {
		return meanProfit;
	}
}
