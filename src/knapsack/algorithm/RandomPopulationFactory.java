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
		
			for(int j = 0; j < result.individuums()[i].qualities().length; ++j) {
				
				int timeout = 0;
				while(timeout < MAX_TIMEOUT) {
					
					int index = random.nextInt(result.individuums()[i].qualities()[j].length);
					
					if(result.individuums()[i].qualities()[j][index]) {
						++timeout;
					} else {
						int weight = result.individuums()[i].getWeight(j);
						weight += p_problem.partProblems()[j].items()[index].weight;
						
						if(weight <= p_problem.partProblems()[j].knappsackSize())
							result.individuums()[i].qualities()[j][index] = true;
						else
							++timeout;
					}
				}
			}
		}
		
		return result;
	}
	
}
