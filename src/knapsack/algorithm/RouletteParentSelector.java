package knapsack.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.IParentSelector;
import knapsack.algorithm.interfaces.IFitnessCalculator;
import knapsack.container.Population;

public class RouletteParentSelector implements IParentSelector {

	private IFitnessCalculator fitnessCalculator;
	private float breedProbability;

	Population currentPopulation;
	boolean[] alreadyBreeded;

	private Random random = new Random();
	
	public RouletteParentSelector(IFitnessCalculator p_qualityCalculator, final float p_breedProbability, final int p_populationSize) {
		fitnessCalculator = p_qualityCalculator;
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
		float fitnessSum = 0;
		
		for(int i = 0; i < currentPopulation.individuums().length; ++i) {
			if(!alreadyBreeded[i])
				fitnessSum += fitnessCalculator.getFitness(currentPopulation.individuums()[i]);
		}
		
		float randomPick = random.nextFloat() * fitnessSum;
		
		float topFitness = 0;
		int result = -1;
		for(int i = 0; i < currentPopulation.individuums().length; ++i) {
			if(!alreadyBreeded[i]) {
				topFitness += fitnessCalculator.getFitness(currentPopulation.individuums()[i]);
				if(topFitness >= randomPick) {
					result = i;
					break;
				}
			}
		}
		
		return result;
	}

}
