package knapsack.misc;

import java.util.Locale;

import knapsack.container.Population;

public class StatisticGenerationElement {

	private float totalProfit;
	private float meanProfit;
	private float maxProfit;
	
	public StatisticGenerationElement(Population p_population) {
		totalProfit = p_population.getTotalProfit();
		meanProfit = p_population.getMeanProfit();
		maxProfit = p_population.getMaxProfit();
	}
	
	public float totalProfit() {
		return totalProfit;
	}
	
	public float meanProfit() {
		return meanProfit;
	}
	
	public float maxProfit() {
		return maxProfit;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.US, "(%.2f,%.2f,%.2f)", totalProfit, meanProfit, maxProfit);
	}
}
