package knapsack.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.IQualityCalculator;
import knapsack.algorithm.interfaces.IToDieSelector;
import knapsack.container.Population;

public class RouletteToDieSelector implements IToDieSelector {

	private IQualityCalculator qualityCalculator;
	private Population currentPopulation;
	private boolean[] alreadyDead;
	
	private final Random random = new Random();
	
	public RouletteToDieSelector(IQualityCalculator p_qualityCalculator, final int p_populationSize) {
		qualityCalculator = p_qualityCalculator;
		alreadyDead = new boolean[p_populationSize];
	}
	
	@Override
	public List<Integer> selectToDie(Population p_population, final int p_toDieCount) {
		currentPopulation = p_population;
		List<Integer> result = new ArrayList<Integer>(p_toDieCount);
		Arrays.fill(alreadyDead, false);
		
		for(int i = 0; i < p_toDieCount; i++) {
			int parent = rouletteSelection();
			alreadyDead[parent] = true;
			result.add(parent);
		}
		
		return result;
	}
	
	private int rouletteSelection() {
		int qualitySum = 0;
		for(int i = 0; i < currentPopulation.individuums().length; ++i) {
			if(!alreadyDead[i])
				qualitySum += qualityCalculator.getQuality(currentPopulation.individuums()[i]);
		}
			
		
		int inverseQualitySum = 0;
		for(int i = 0; i < currentPopulation.individuums().length; ++i) {
			if(!alreadyDead[i])
				inverseQualitySum += qualitySum / qualityCalculator.getQuality(currentPopulation.individuums()[i]);
		}
			
		int randomPick = random.nextInt(inverseQualitySum);
		
		int topQuality = 0;
		int result = -1;
		for(int i = 0; i < currentPopulation.individuums().length; ++i) {
			if(!alreadyDead[i]) {
				topQuality += (qualitySum / qualityCalculator.getQuality(currentPopulation.individuums()[i]));
				if(topQuality >= randomPick) {
					result = i;
					break;
				}
			}
		}
		
		return result;
	}

}
