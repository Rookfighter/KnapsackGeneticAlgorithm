package knapsack.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.ICrossover;
import knapsack.container.Knapsack;
import knapsack.container.KnapsackItem;

public class UniformCrossover implements ICrossover {
	
	private static final int MAX_TIMEOUT = 10;
	
	private final Random random = new Random();

	public UniformCrossover() {
		
	}
	
	@Override
	public List<Knapsack> crossover(List<Knapsack> p_parents) {
		List<Knapsack> result = new ArrayList<Knapsack>(p_parents.size());
		
		for(int i = 0; i < p_parents.size() - 1; i += 2) {
			result.addAll(getChildren(p_parents.get(i) , p_parents.get(i + 1)));
		}
		
		return result;
	}
	
	private List<Knapsack> getChildren(Knapsack mommy, Knapsack daddy) {
		List<Knapsack> result = new ArrayList<Knapsack>(2);
		
		int itemCount = Math.max(daddy.items().size(), mommy.items().size());
		boolean[] itemMask = new boolean[itemCount];
		
		for(int i = 0; i < itemCount; ++i)
			itemMask[i] = random.nextBoolean();
		
		Knapsack child = new Knapsack();
		for(int i = 0; i < itemCount; ++i) {
			if(itemMask[i] && i < daddy.items().size())
				child.items().add(daddy.items().get(i));
			else if(i < mommy.items().size())
				child.items().add(mommy.items().get(i));
		}
		applyToRules(mommy, daddy, child);
		result.add(child);
		
		child = new Knapsack();
		for(int i = 0; i < itemCount; ++i) {
			if(itemMask[i] && i < mommy.items().size())
				child.items().add(mommy.items().get(i));
			else if(i < daddy.items().size())
				child.items().add(daddy.items().get(i));
		}
		applyToRules(mommy, daddy, child);
		result.add(child);
		
		return result;
	}
	
	private void applyToRules(Knapsack mommy, Knapsack daddy, Knapsack fred) {
		while(fred.overLimit())
			fred.removeLowestProfitItem();
		
		List<KnapsackItem> duplicates = new LinkedList<KnapsackItem>();
		
		for(KnapsackItem item1: fred.items())
		{
			int count = 0;
			for(KnapsackItem item2: fred.items())
			{
				if(item1 == item2)
					count++;
				if(count > 1)
					duplicates.add(item2);
			}
		}
		
		fred.items().removeAll(duplicates);
		
		int timeout = 0;
		while(timeout < MAX_TIMEOUT) {
			
			Knapsack parent;
			if(random.nextBoolean())
				parent = daddy;
			else
				parent = mommy;
			
			KnapsackItem toAdd = null;
			do {
				toAdd = parent.items().get(random.nextInt(parent.items().size()));
			} while(toAdd != null && fred.items().contains(toAdd));
			
			if(fred.getTotalWeight() + toAdd.weight > fred.maxWeight)
				timeout++;
			else {
				timeout = 0;
				fred.items().add(toAdd);
			}
		}
	}


}
