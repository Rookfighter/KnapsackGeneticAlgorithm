package knapsack.algorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.IMutator;
import knapsack.algorithm.interfaces.IRulesApplyer;
import knapsack.container.KnapsackIndividuum;

public class RandomMutator implements IMutator {

	private float mutationProbability;
	private boolean[] alreadyMutated;
	
	private final IRulesApplyer referee = new RandomApplyer();
	private final Random random = new Random();
	
	public RandomMutator(final float p_mutationProbability, final int p_populationSize) {
		mutationProbability = p_mutationProbability;
		alreadyMutated = new boolean[p_populationSize];
	}
	
	@Override
	public void mutate(List<KnapsackIndividuum> p_children) {
		Arrays.fill(alreadyMutated, false);
		
		for(int i = 0; i < p_children.size(); ++i) {
			boolean mutate = random.nextFloat() <= mutationProbability;
			if(mutate) {
				KnapsackIndividuum individuum = p_children.get(i);
				
				int item;
				item = random.nextInt(individuum.qualities().length);
				
				// flip the quality
				individuum.qualities()[item] = !individuum.qualities()[item];
				referee.applyToRules(individuum);
			}
		}
	}
	

}
