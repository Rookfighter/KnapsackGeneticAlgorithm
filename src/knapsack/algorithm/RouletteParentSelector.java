package knapsack.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.IParentSelector;
import knapsack.algorithm.interfaces.IQualityCalculator;
import knapsack.container.Population;

public class RouletteParentSelector implements IParentSelector {

	private IQualityCalculator qualityCalculator;
	private float breedProbability;

	Population currentPopulation;
	boolean[] alreadyBreeded;

	private Random random = new Random();
	
	public RouletteParentSelector(IQualityCalculator p_qualityCalculator, final float p_breedProbability, final int p_populationSize) {
		qualityCalculator = p_qualityCalculator;
		breedProbability = p_breedProbability;
		alreadyBreeded = new boolean[p_populationSize];
	}
	
	@Override
	public List<Integer> selectParents(Population p_population) {
		currentPopulation = p_population;
		Arrays.fill(alreadyBreeded, false);
	
		List<Integer> result = new ArrayList<Integer>((int) (currentPopulation.individuums().length * breedProbability));
		
		for(int i = 0; i < currentPopulation.individuums().length; i++) {
			boolean breed = random.nextFloat() <= breedProbability;
			if(breed) {
				int parent = rouletteSelection();
				alreadyBreeded[parent] = true;
				result.add(parent);
			}
		}
		// parent count has to be even
		if(result.size() % 2 != 0)
			result.remove(result.size() - 1);
		
		return result;
	}
	
	private int rouletteSelection() {
		int qualitySum = 0;
		
		for(int i = 0; i < currentPopulation.individuums().length; ++i) {
			if(!alreadyBreeded[i])
				qualitySum += qualityCalculator.getQuality(currentPopulation.individuums()[i]);
		}
		
		int randomPick = random.nextInt(qualitySum);
		
		int topQuality = 0;
		int result = -1;
		for(int i = 0; i < currentPopulation.individuums().length; ++i) {
			if(!alreadyBreeded[i]) {
				topQuality += qualityCalculator.getQuality(currentPopulation.individuums()[i]);
				if(topQuality >= randomPick) {
					result = i;
					break;
				}
			}
		}
		
		return result;
	}

}
