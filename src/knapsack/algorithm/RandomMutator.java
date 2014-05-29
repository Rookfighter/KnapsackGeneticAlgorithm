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

	private static final int MAX_TIMEOUT = 20;
	
	private float mutationProbability;
	
	private final Random random = new Random();
	
	public RandomMutator(final float p_mutationProbability) {
		mutationProbability = p_mutationProbability;
	}
	
	@Override
	public void mutate(Population p_population, KnapsackTask p_task) {
		List<Knapsack> individuums = new LinkedList<Knapsack>(p_population.individuums());
		
		for(int i = 0; i < p_population.individuums().size(); ++i) {
			boolean mutate = random.nextFloat() <= mutationProbability;
			if(mutate) {
				int index = random.nextInt(individuums.size());
				Knapsack individuum = individuums.get(index);
				
				index = random.nextInt(individuum.items().size());
				individuum.items().remove(index);
				
				List<KnapsackItem> availableItems = new LinkedList<KnapsackItem>(p_task.items());
				availableItems.removeAll(individuum.items());

				int timeout = 0;
				boolean found = false;
				KnapsackItem toAdd = null;
				while(!found && timeout < MAX_TIMEOUT && !availableItems.isEmpty()) {
					index = random.nextInt(availableItems.size());
					toAdd = availableItems.get(index);
						
					found = (toAdd.weight + individuum.getTotalWeight()) <= individuum.maxWeight;
					availableItems.remove(toAdd);
					timeout++;
				}
				
				if(found)
					individuum.items().add(toAdd);
				individuums.remove(individuum);
			}
		}
	}

}
