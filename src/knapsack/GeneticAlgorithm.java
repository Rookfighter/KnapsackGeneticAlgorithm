package knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {

	private static final int POPULATION_SIZE = 10;
	private static final float BREED_PROBABILITY = 1.0f;
	
	private KnapsackProblem problem;
	private PopulationFactory populationFactory;
	private IQualityCalculator qualityCalculator;
	private Random random;
	
	private KnapsackTask task;
	private Population population;
	
	public GeneticAlgorithm() {
		populationFactory = new PopulationFactory();
		qualityCalculator = new QualityCalculator();
		random = new Random();
	}
	
	public void solve(KnapsackProblem p_problem){
		problem = p_problem;
		
		for(int i = 0; i < p_problem.taskCount(); ++i) {
			solveTask(p_problem.getTask(i));
			
		}
	}
	
	private Population solveTask(KnapsackTask p_task) {
		task = p_task;
		Population result = populationFactory.createRandomPopulation(POPULATION_SIZE, task);
		
		uniformCrossover(population);
		mutate(population);
		
		return result;
	}
	
	private void uniformCrossover(Population p_population) {
		List<Knapsack> parents = selectParents(p_population);
		deleteRandomIndividuums(p_population, parents.size());
		
		for(int i = 0; i < parents.size() / 2; ++i) {
			addChildren(p_population, parents.get(i), parents.get(i + 1));
		}
	}
	
	private List<Knapsack> selectParents(final Population p_population) {
		Population population = p_population.copy();
		
		int parentCount = (int) (population.getIndividuums().size() * BREED_PROBABILITY);
		if(parentCount % 2 != 0)
			parentCount--;
		
		List<Knapsack> result = new ArrayList<Knapsack>(parentCount);
		
		for(int i = 0; i < parentCount; i++) {
			Knapsack parent = rouletteSelection(population);
			if(parent != null) {
				result.add(parent);
				population.getIndividuums().remove(parent);
			}
		}
		
		return result;
	}
	
	private Knapsack rouletteSelection(final Population p_population) {
		float qualitySum = 0;
		
		for(Knapsack individuum : p_population.getIndividuums())
			qualitySum += qualityCalculator.getQuality(individuum);
		
		float randomPick = random.nextFloat() * qualitySum;
		
		qualitySum = 0;
		Knapsack result = null;
		for(int i = 0; i < p_population.getIndividuums().size(); ++i) {
			qualitySum += qualityCalculator.getQuality(p_population.getIndividuums().get(i));
			if(qualitySum <= randomPick) {
				result = p_population.getIndividuums().get(i);
				break;
			}
				
		}
		
		return result;
	}
	
	private void deleteRandomIndividuums(Population p_population, final int p_count) {
		for(int i = 0; i < p_count; ++i) {
			int index = random.nextInt() % p_population.getIndividuums().size();
			p_population.getIndividuums().remove(index);
		}
	}
	
	public void addChildren(Population p_population, Knapsack p_daddy, Knapsack p_mommy) {
		int itemCount = Math.max(p_daddy.count(), p_mommy.count());
		boolean[] itemMask = new boolean[itemCount];
		
		for(int i = 0; i < itemCount; ++i)
			itemMask[i] = random.nextBoolean();
		
		Knapsack child = new Knapsack();
		for(int i = 0; i < itemCount; ++i) {
			if(itemMask[i] && i < p_daddy.count())
				child.add(p_daddy.getItem(i).copy());
			else
				child.add(p_mommy.getItem(i).copy());
		}
		
		population.getIndividuums().add(child);
		
		for(int i = 0; i < itemCount; ++i) {
			if(itemMask[i] && i < p_mommy.count())
				child.add(p_mommy.getItem(i).copy());
			else
				child.add(p_daddy.getItem(i).copy());
		}
		
		population.getIndividuums().add(child);
	
	}
	
	private void mutate(Population p_population) {
		
	}
	
}
