package knapsack.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.IParentSelector;
import knapsack.algorithm.interfaces.IQualityCalculator;
import knapsack.container.Knapsack;
import knapsack.container.Population;

public class RouletteParentSelector implements IParentSelector {

	private IQualityCalculator qualityCalculator;
	private float breedProbability;

	private List<Knapsack> individuums;

	private Random random = new Random();
	
	public RouletteParentSelector(IQualityCalculator p_qualityCalculator, final float p_breedProbability) {
		qualityCalculator = p_qualityCalculator;
		breedProbability = p_breedProbability;
	}
	
	@Override
	public List<Knapsack> selectParents(Population p_population) {
		individuums = new LinkedList<Knapsack>(p_population.individuums());
		
		int parentCount = (int) (p_population.individuums().size() * breedProbability);
		if(parentCount % 2 != 0)
			parentCount--;
		
		List<Knapsack> result = new ArrayList<Knapsack>(parentCount);
		
		for(int i = 0; i < parentCount && !individuums.isEmpty(); i++) {
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
		
		float randomPick = random.nextFloat() * qualitySum;
		
		qualitySum = 0;
		Knapsack result = null;
		for(Knapsack individuum : individuums) {
			qualitySum += qualityCalculator.getQuality(individuum);
			if(qualitySum <= randomPick) {
				result = individuum;
				break;
			}
		}
		
		return result;
	}

}
