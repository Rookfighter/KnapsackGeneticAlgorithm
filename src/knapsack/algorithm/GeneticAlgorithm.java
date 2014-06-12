package knapsack.algorithm;

import java.util.List;

import knapsack.container.KnapsackIndividuum;
import knapsack.container.KnapsackProblem;
import knapsack.container.Population;
import knapsack.misc.AlgorithmConfig;
import knapsack.misc.Statistics;

public class GeneticAlgorithm {

	private AlgorithmConfig config;
	
	private final Statistics statistics = new Statistics();
	
	public GeneticAlgorithm(AlgorithmConfig p_config) {
		config = p_config;
	}
	
	public void solve(KnapsackProblem p_problem){
		statistics.nextProblem(p_problem);
		Population population = config.populationFactory.generatePopulation(config.populationSize, p_problem);
		config.condition.reset();
		do {
			statistics.nextGeneration(population);
			stepGeneration(population);
		} while(!config.condition.terminate());
	}
	
	private void stepGeneration(Population p_population) {
		
		List<Integer> parents = config.parentSelector.selectParents(p_population);
		List<Integer> toDie = config.toDieSelector.selectToDie(p_population, parents.size()); 
		List<KnapsackIndividuum> children = config.crossover.crossover(parents, p_population);
		config.mutator.mutate(children);
		
		//kill indiviuums and add children
		for(int i = 0; i < toDie.size(); ++i)
			p_population.individuums()[toDie.get(i)].apply(children.get(i));
	}
	
	public Statistics getStatistics() {
		return statistics;
	}
}
