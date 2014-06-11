package knapsack.algorithm;

import java.util.Random;

import knapsack.algorithm.interfaces.IPopulationFactory;
import knapsack.container.KnapsackProblem;
import knapsack.container.Population;

public class RandomPopulationFactory implements IPopulationFactory {
	
	private static final int MAX_TIMEOUT = 10;
	
	Population result;
	
	Random random;
	
	public RandomPopulationFactory() {
		random = new Random();
	}

	@Override
	public Population generatePopulation(int p_size, KnapsackProblem p_problem) {
		result = new Population(p_size, p_problem);
		
		for(int i = 0; i < result.individuums().length; ++i) {
		
			int timeout = 0;
			while(timeout < MAX_TIMEOUT) {
				
				int partProblem = random.nextInt(result.individuums()[i].qualities().length);
				int item = random.nextInt(result.individuums()[i].qualities()[partProblem].length);

				if(!result.individuums()[i].setQuality(partProblem, item, true)) 
					++timeout;
			}
		}
		
		return result;
	}
	
}
