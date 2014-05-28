package knapsack;

import java.util.List;
import java.util.Random;

public class PopulationFactory {
	
	private static final int MAX_TIMEOUT = 10;
	
	KnapsackTask task;
	Population result;
	Random random;
	
	public PopulationFactory() {
		random = new Random();
	}
	
	public Population createRandomPopulation(final int p_size, KnapsackTask p_task) {
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
			
			result.getIndividuums().add(individuum);
		}
		
		return result;
	}
	
}
