package knapsack.algorithm;

import java.util.Arrays;
import java.util.Random;

import knapsack.algorithm.interfaces.IMutator;
import knapsack.container.KnapsackIndividuum;
import knapsack.container.Population;

public class RandomMutator implements IMutator {

	private float mutationProbability;
	private boolean[] alreadyMutated;
	
	private final Random random = new Random();
	
	public RandomMutator(final float p_mutationProbability, final int p_populationSize) {
		mutationProbability = p_mutationProbability;
		alreadyMutated = new boolean[p_populationSize];
	}
	
	@Override
	public void mutate(Population p_population) {
		Arrays.fill(alreadyMutated, false);
		
		for(int i = 0; i < p_population.individuums().length; ++i) {
			boolean mutate = random.nextFloat() <= mutationProbability;
			if(mutate) {
				int individuumIndex;
				do {
					individuumIndex = random.nextInt(p_population.individuums().length);
				} while (alreadyMutated[individuumIndex]);
				
				KnapsackIndividuum individuum = p_population.individuums()[individuumIndex];
				
				int partProblem, item;
				do {
					partProblem = random.nextInt(individuum.qualities().length);
					item = random.nextInt(individuum.qualities()[partProblem].length);
				} while (individuum.isInvalidItem(partProblem, item));
				
				individuum.qualities()[partProblem][item] = !individuum.qualities()[partProblem][item];
				individuum.applyToRules();
			}
		}
	}
	

}
