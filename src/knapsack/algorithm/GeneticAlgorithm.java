package knapsack.algorithm;

import java.util.List;

import knapsack.algorithm.interfaces.ICrossover;
import knapsack.algorithm.interfaces.IMutator;
import knapsack.algorithm.interfaces.IParentSelector;
import knapsack.algorithm.interfaces.IPopulationFactory;
import knapsack.algorithm.interfaces.IQualityCalculator;
import knapsack.algorithm.interfaces.ITerminationCondition;
import knapsack.algorithm.interfaces.IToDieSelector;
import knapsack.container.Knapsack;
import knapsack.container.KnapsackProblem;
import knapsack.container.KnapsackTask;
import knapsack.container.Population;

public class GeneticAlgorithm {

	private static final int POPULATION_SIZE = 10;
	private static final float BREED_PROBABILITY = 1.0f;
	private static final float MUTATION_PROBABILITY = 0.1f;
	
	private final IPopulationFactory populationFactory = new RandomPopulationFactory();
	private final IQualityCalculator qualityCalculator = new QualityCalculator();
	private final IParentSelector parentSelector = new RouletteParentSelector(qualityCalculator, BREED_PROBABILITY);
	private final IToDieSelector toDieSelector = new RouletteToDieSelector(qualityCalculator);
	private final ICrossover crossover = new UniformCrossover();
	private final IMutator mutator = new RandomMutator(MUTATION_PROBABILITY);
	
	public GeneticAlgorithm() {

	}
	
	public void solve(KnapsackProblem p_problem){
		for(int i = 0; i < p_problem.taskCount(); ++i) {
			solveTask(p_problem.getTask(i));
			
		}
	}
	
	private Population solveTask(KnapsackTask p_task) {
		Population result = populationFactory.generatePopulation(p_task, POPULATION_SIZE);
		ITerminationCondition condition = new GenerationTermination(10);
		
		do {
			selecion(result);
			mutator.mutate(result, p_task);
		} while(!condition.terminate());
		
		return result;
	}
	
	private void selecion(Population p_population) {
		
		List<Knapsack> parents = parentSelector.selectParents(p_population);
		
		List<Knapsack> toDie = toDieSelector.selectToDie(p_population, parents.size()); 
		p_population.removeIndividuums(toDie);
		
		List<Knapsack> children = crossover.crossover(parents);
		p_population.addIndividuums(children);
	}
}
