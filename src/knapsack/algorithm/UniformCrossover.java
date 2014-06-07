package knapsack.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import knapsack.algorithm.interfaces.ICrossover;
import knapsack.container.KnapsackIndividuum;
import knapsack.container.Population;

public class UniformCrossover implements ICrossover {
	
	private final Random random = new Random();
	private Population currentPopulation;
	private boolean[][] inheritanceMask;

	public UniformCrossover() {
		
	}
	
	@Override
	public List<KnapsackIndividuum> crossover(List<Integer> p_parents, Population p_population) {
		currentPopulation = p_population;
		List<KnapsackIndividuum> result = new ArrayList<KnapsackIndividuum>(p_parents.size());
		
		inheritanceMask = new boolean[currentPopulation.problem().partProblems().length][];
		for(int i = 0; i < inheritanceMask.length; ++i)
			inheritanceMask[i] = new boolean[currentPopulation.problem().partProblems()[i].items().length];
		
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
		
		for(int i = 0; i < currentPopulation.problem().partProblems().length; ++i) {
			for(int j = 0; j < currentPopulation.problem().partProblems()[i].items().length; ++j) {
				inheritanceMask[i][j] = random.nextBoolean();
			}
		}
		
		result[0] = new KnapsackIndividuum(currentPopulation.problem());
		for(int i = 0; i < currentPopulation.problem().partProblems().length; ++i) {
			for(int j = 0; j < currentPopulation.problem().partProblems()[i].items().length; ++j) {
				if(inheritanceMask[i][j])
					result[0].qualities()[i][j] = mommy.qualities()[i][j];
				else
					result[0].qualities()[i][j] = daddy.qualities()[i][j];
			}
		}
		applyToRules(result[0]);
		
		result[1] = new KnapsackIndividuum(currentPopulation.problem());
		for(int i = 0; i < currentPopulation.problem().partProblems().length; ++i) {
			for(int j = 0; j < currentPopulation.problem().partProblems()[i].items().length; ++j) {
				if(inheritanceMask[i][j])
					result[1].qualities()[i][j] = daddy.qualities()[i][j];
				else
					result[1].qualities()[i][j] = mommy.qualities()[i][j];
			}
		}
		applyToRules(result[1]);
		
		return result;
	}
	
	private void applyToRules(KnapsackIndividuum fred) {
		for(int i = 0; i < fred.qualities().length; ++i) {
			while(fred.isOverLimit(i)) {
				int toRemove = random.nextInt(fred.qualities()[i].length);
				if(fred.qualities()[i][toRemove])
					fred.qualities()[i][toRemove] = false;
			}
		}
	}

}
