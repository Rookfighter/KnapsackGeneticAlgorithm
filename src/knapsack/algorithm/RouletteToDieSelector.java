package knapsack.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.IQualityCalculator;
import knapsack.algorithm.interfaces.IToDieSelector;
import knapsack.container.Knapsack;
import knapsack.container.Population;

public class RouletteToDieSelector implements IToDieSelector {

	private IQualityCalculator qualityCalculator;
	private List<Knapsack> individuums;
	
	private final Random random = new Random();
	
	public RouletteToDieSelector(IQualityCalculator p_qualityCalculator) {
		qualityCalculator = p_qualityCalculator;
	}
	
	@Override
	public List<Knapsack> selectToDie(Population p_population, final int p_toDieCount) {
		individuums = new LinkedList<Knapsack>(p_population.getIndividuums());
		List<Knapsack> result = new ArrayList<Knapsack>(p_toDieCount);
		
		for(int i = 0; i < p_toDieCount && !individuums.isEmpty(); i++) {
			Knapsack parent = rouletteSelection();
			individuums.remove(parent);
			result.add(parent);
		}
		
		return result;
	}
	
	private Knapsack rouletteSelection() {
		float qualitySum = 0;
		for(Knapsack individuum : individuums)
			qualitySum += qualityCalculator.getQuality(individuum);
		
		float inverseQualitySum = 0;
		for(Knapsack individuum : individuums)
			inverseQualitySum += qualitySum / qualityCalculator.getQuality(individuum);
		
		float randomPick = random.nextFloat() * inverseQualitySum;
		
		inverseQualitySum = 0;
		Knapsack result = null;
		for(Knapsack individuum : individuums) {
			inverseQualitySum += (qualitySum / qualityCalculator.getQuality(individuum));
			if(inverseQualitySum <= randomPick) {
				result = individuum;
				break;
			}
		}
		
		return result;
	}

}
