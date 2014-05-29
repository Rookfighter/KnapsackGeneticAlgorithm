package knapsack.algorithm;

import java.util.List;

import knapsack.container.Knapsack;
import knapsack.container.KnapsackProblem;
import knapsack.container.KnapsackTask;
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
		for(KnapsackTask task: p_problem.tasks()) {
			solveTask(task);
		}
	}
	
	private Population solveTask(KnapsackTask p_task) {
		statistics.nextTask(p_task);
		Population population = config.populationFactory.generatePopulation(p_task, config.populationSize);
		config.condition.reset();
		
		do {
			statistics.nextGeneration(population);
			selecion(population);
			config.mutator.mutate(population, p_task);
		} while(!config.condition.terminate());
		
		return population;
	}
	
	private void selecion(Population p_population) {
		
		List<Knapsack> parents = config.parentSelector.selectParents(p_population);
		List<Knapsack> toDie = config.toDieSelector.selectToDie(p_population, parents.size()); 
		List<Knapsack> children = config.crossover.crossover(parents);
		
		p_population.individuums().removeAll(toDie);
		p_population.individuums().addAll(children);
	}
	
	public Statistics getStatistics() {
		return statistics;
	}
}
