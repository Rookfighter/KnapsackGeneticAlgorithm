package knapsack.misc;

import java.util.Locale;

import knapsack.container.Population;

public class StatisticGenerationElement {

	private double totalWeight;
	private double totalProfit;
	
	private double meanWeight;
	private double meanProfit;
	
	private double highestIndidividuumProfit;
	private double highestKnapsackProfit;
	
	private double weightRatio;
	
	public StatisticGenerationElement(Population p_population) {
		totalWeight = p_population.getTotalWeight();
		totalProfit = p_population.getTotalProfit();
		
		meanWeight = p_population.getMeanWeight();
		meanProfit = p_population.getMeanProfit();
		
		highestIndidividuumProfit = p_population.getHighestIndividuumProfit();
		highestKnapsackProfit = p_population.getHighestKnapsackProfit();
		
		weightRatio = p_population.getWeightRatio();
	}
	
	public double totalWeight() {
		return totalWeight;
	}
	
	public double totalProfit() {
		return totalProfit;
	}
	
	public double meanWeight() {
		return meanWeight;
	}
	
	public double meanProfit() {
		return meanProfit;
	}
	
	public double highestIndividuumProfit() {
		return highestIndidividuumProfit;
	}
	
	public double highestKnapsackProfit() {
		return highestKnapsackProfit;
	}
	
	public double weightRatio() {
		return weightRatio;
	}
	
	@Override
	public String toString() {
		return String.format(Locale.US, "(%.2f,%.2f,%.2f,%.2f)", totalWeight, totalProfit, meanWeight, meanProfit);
	}
}
