package knapsack.algorithm;

import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.IPopulationFactory;
import knapsack.container.Knapsack;
import knapsack.container.KnapsackItem;
import knapsack.container.KnapsackTask;
import knapsack.container.Population;

public class RandomPopulationFactory implements IPopulationFactory {
	
	private static final int MAX_TIMEOUT = 10;
	
	KnapsackTask task;
	Population result;
	
	Random random;
	
	public RandomPopulationFactory() {
		random = new Random();
	}

	@Override
	public Population generatePopulation(KnapsackTask p_task, int p_size) {
		result = new Population(p_size);
		task = p_task;
		
		for(int i = 0; i < p_size; ++i) {
			Knapsack individuum = new Knapsack();
			
			int timeout = 0;
			List<KnapsackItem> itemList = task.createItemList();
			while(true) {
				if(itemList.isEmpty() || timeout >= MAX_TIMEOUT)
					break;
				
				int index = random.nextInt() % itemList.size();
				if((itemList.get(index).weight + individuum.getTotalWeight()) > individuum.maxWeight) {
					timeout++;
					continue;
				}
				
				timeout = 0;
				individuum.add(itemList.get(index));
				itemList.remove(index);
			}
			
			result.addIndividuum(individuum);
		}
		
		return result;
	}
	
}
