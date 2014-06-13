package knapsack.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import knapsack.algorithm.interfaces.IFitnessCalculator;
import knapsack.algorithm.interfaces.IToDieSelector;
import knapsack.container.Population;

public class WorstFitnessToDieSelector implements IToDieSelector {

	private IFitnessCalculator fitnessCalculator;
	private boolean[] alreadyDead;
	private List<Integer> result;
	
	public WorstFitnessToDieSelector(IFitnessCalculator p_qualityCalculator, final int p_populationSize) {
		fitnessCalculator = p_qualityCalculator;
		alreadyDead = new boolean[p_populationSize];
		result = new ArrayList<Integer>(p_populationSize);
	}
	
	@Override
	public List<Integer> selectToDie(Population p_population, int p_toDieCount) {
		result.clear();
		Arrays.fill(alreadyDead, false);
		
		// select zero fitness individuums
		for(int i = 0; i < p_population.individuums().length && result.size() < p_toDieCount; ++i) {
			if(!alreadyDead[i] && fitnessCalculator.getFitness(p_population.individuums()[i]) == 0) {
				alreadyDead[i] = true;
				result.add(i);
			}
		}
		
		// select worst fitness individuums
		for(int i = result.size(); i < p_toDieCount; ++i) {
			int individuum = -1;
			float minimum = -1;
			for(int j = 0; j < p_population.individuums().length; ++j) {
				if(alreadyDead[j])
					continue;
				
				float fitness = fitnessCalculator.getFitness(p_population.individuums()[j]);
				if(minimum < 0) {
					minimum = fitness;
					individuum = j;
				} else if (fitness < minimum) {
					minimum = fitness;
					individuum = j;
				}
			}
			
			alreadyDead[individuum] = true;
			result.add(individuum);
		}
		
		return result;
	}
	

}
