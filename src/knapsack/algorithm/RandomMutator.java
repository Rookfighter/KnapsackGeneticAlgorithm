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
				int index;
				do {
					index = random.nextInt(p_population.individuums().length);
				} while (alreadyMutated[index]);
				
				KnapsackIndividuum individuum = p_population.individuums()[index];
				
				index = random.nextInt(individuum.qualities().length);
				int index2 = random.nextInt(individuum.qualities()[index].length);
				
				individuum.qualities()[index][index2] = !individuum.qualities()[index][index2];
				
				applyToRules(individuum);
			}
		}
	}
	
	private void applyToRules(KnapsackIndividuum p_individuum) {
		for(int i = 0; i < p_individuum.qualities().length; ++i) {
			while(p_individuum.isOverLimit(i)) {
				int toRemove = random.nextInt(p_individuum.qualities()[i].length);
				if(p_individuum.qualities()[i][toRemove])
					p_individuum.qualities()[i][toRemove] = false;
			}
		}
	}

}
