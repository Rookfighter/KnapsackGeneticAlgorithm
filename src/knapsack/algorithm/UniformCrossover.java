package knapsack.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.ICrossover;
import knapsack.algorithm.interfaces.IRulesApplyer;
import knapsack.container.KnapsackIndividuum;
import knapsack.container.Population;

public class UniformCrossover implements ICrossover {
	
	private final Random random = new Random();
	private final IRulesApplyer referee = new RandomApplyer();
	private Population currentPopulation;
	private boolean[] inheritanceMask;
	
	public UniformCrossover() {
		
	}
	
	@Override
	public List<KnapsackIndividuum> crossover(List<Integer> p_parents, Population p_population) {
		currentPopulation = p_population;
		List<KnapsackIndividuum> result = new ArrayList<KnapsackIndividuum>(p_parents.size());
		
		inheritanceMask = new boolean[currentPopulation.problem().items().length];
		
		for(int i = 0; i < p_parents.size() - 1; i += 2) {
			KnapsackIndividuum daddy = currentPopulation.individuums()[p_parents.get(i)];
			KnapsackIndividuum mommy = currentPopulation.individuums()[p_parents.get(i + 1)];
			KnapsackIndividuum[] children = getChildren(mommy, daddy);
			result.add(children[0]);
			result.add(children[1]);
		}
		
		return result;
	}
	
	
	private KnapsackIndividuum[] getChildren(KnapsackIndividuum mommy, KnapsackIndividuum daddy) {
		KnapsackIndividuum[] result = new KnapsackIndividuum[2];
		
		// calculate which gene is inherited from which parent
		for(int i = 0; i < inheritanceMask.length; ++i)
			inheritanceMask[i] = random.nextBoolean();
		
		result[0] = new KnapsackIndividuum(currentPopulation.problem());
		for(int i = 0; i < result[0].qualities().length; ++i) {
			if(inheritanceMask[i])
				result[0].qualities()[i] = mommy.qualities()[i];
			else
				result[0].qualities()[i] = daddy.qualities()[i];
		}
		referee.applyToRules(result[0]);
		
		result[1] = new KnapsackIndividuum(currentPopulation.problem());
		for(int i = 0; i < result[1].qualities().length; ++i) {
				if(inheritanceMask[i])
					result[1].qualities()[i] = daddy.qualities()[i];
				else
					result[1].qualities()[i] = mommy.qualities()[i];
		}
		referee.applyToRules(result[1]);
		
		return result;
	}

}
