package knapsack.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.IFitnessCalculator;
import knapsack.algorithm.interfaces.IToDieSelector;
import knapsack.container.Population;

public class RouletteToDieSelector implements IToDieSelector {

	private IFitnessCalculator fitnessCalculator;
	private Population currentPopulation;
	private boolean[] alreadyDead;
	
	private final Random random = new Random();
	
	public RouletteToDieSelector(IFitnessCalculator p_qualityCalculator, final int p_populationSize) {
		fitnessCalculator = p_qualityCalculator;
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
		double fitnessSum = 0;
		for(int i = 0; i < currentPopulation.individuums().length; ++i) {
			if(!alreadyDead[i])
				fitnessSum += fitnessCalculator.getFitness(currentPopulation.individuums()[i]);
		}
			
		
		double inverseFitnessSum = 0;
		for(int i = 0; i < currentPopulation.individuums().length; ++i) {
			if(!alreadyDead[i])
				inverseFitnessSum += fitnessSum / fitnessCalculator.getFitness(currentPopulation.individuums()[i]);
		}
			
		double randomPick = random.nextDouble() * inverseFitnessSum;
		
		double topFitness = 0;
		int result = -1;
		for(int i = 0; i < currentPopulation.individuums().length; ++i) {
			if(!alreadyDead[i]) {
				topFitness += (fitnessSum / fitnessCalculator.getFitness(currentPopulation.individuums()[i]));
				if(topFitness >= randomPick) {
					result = i;
					break;
				}
			}
		}
		
		return result;
	}

}
