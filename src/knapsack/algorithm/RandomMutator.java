package knapsack.algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.IMutator;
import knapsack.container.Knapsack;
import knapsack.container.KnapsackItem;
import knapsack.container.KnapsackTask;
import knapsack.container.Population;

public class RandomMutator implements IMutator {

	private float mutationProbability;
	
	private final Random random = new Random();
	
	public RandomMutator(final float p_mutationProbability) {
		mutationProbability = p_mutationProbability;
	}
	
	@Override
	public void mutate(Population p_population, KnapsackTask p_task) {
		List<Knapsack> individuums = new LinkedList<Knapsack>(p_population.getIndividuums());
		
		
		int mutationCount = (int) (mutationProbability * individuums.size());
		
		int count = 0;
		while(!individuums.isEmpty() && count < mutationCount) {
			Knapsack individuum = individuums.get(random.nextInt(individuums.size()));
			
			individuum.remove(random.nextInt(individuum.count()));
			
			KnapsackItem toAdd = null;
			do {
				toAdd = p_task.getItemList().get(random.nextInt(p_task.getItemList().size()));
			} while (toAdd.weight + individuum.getTotalWeight() > individuum.maxWeight);
			
			individuum.add(toAdd);
			individuums.remove(individuum);
			count++;
		}
	}

}
