package knapsack.misc;

import knapsack.algorithm.GenerationTermination;
import knapsack.algorithm.QualityCalculator;
import knapsack.algorithm.RandomMutator;
import knapsack.algorithm.RandomPopulationFactory;
import knapsack.algorithm.RouletteParentSelector;
import knapsack.algorithm.RouletteToDieSelector;
import knapsack.algorithm.UniformCrossover;
import knapsack.algorithm.interfaces.ICrossover;
import knapsack.algorithm.interfaces.IMutator;
import knapsack.algorithm.interfaces.IParentSelector;
import knapsack.algorithm.interfaces.IPopulationFactory;
import knapsack.algorithm.interfaces.IQualityCalculator;
import knapsack.algorithm.interfaces.ITerminationCondition;
import knapsack.algorithm.interfaces.IToDieSelector;

public class AlgorithmConfig {

	public int populationSize;
	public int generationCount;
	public float breedProbability;
	public float mutationProbability;
	
	public IPopulationFactory populationFactory;
	public IQualityCalculator qualityCalculator;
	public IParentSelector parentSelector;
	public IToDieSelector toDieSelector;
	public ICrossover crossover;
	public IMutator mutator;
	public ITerminationCondition condition;
	
	private static final int LOW_GEN = 10;
	private static final int HIGH_GEN = 300;
	private static final int LOW_POP = 10;
	private static final int HIGH_POP = 100;
	
	public static AlgorithmConfig lowPopHighGenConfig() {
		AlgorithmConfig result = new AlgorithmConfig();
		
		result.populationSize = LOW_POP;
		result.breedProbability = 0.7f;
		result.mutationProbability = 0.2f;
		result.populationFactory = new RandomPopulationFactory();
		result.qualityCalculator = new QualityCalculator();
		result.parentSelector = new RouletteParentSelector(result.qualityCalculator, result.breedProbability);
		result.toDieSelector = new RouletteToDieSelector(result.qualityCalculator);
		result.crossover = new UniformCrossover();
		result.mutator = new RandomMutator(result.mutationProbability);
		result.generationCount = HIGH_GEN;
		result.condition = new GenerationTermination(result.generationCount);
		
		return result;
	}
	
	public static AlgorithmConfig highPopLowGenConfig() {
		AlgorithmConfig result = new AlgorithmConfig();
		
		result.populationSize = HIGH_POP;
		result.breedProbability = 0.7f;
		result.mutationProbability = 0.2f;
		result.populationFactory = new RandomPopulationFactory();
		result.qualityCalculator = new QualityCalculator();
		result.parentSelector = new RouletteParentSelector(result.qualityCalculator, result.breedProbability);
		result.toDieSelector = new RouletteToDieSelector(result.qualityCalculator);
		result.crossover = new UniformCrossover();
		result.mutator = new RandomMutator(result.mutationProbability);
		result.generationCount = LOW_GEN;
		result.condition = new GenerationTermination(result.generationCount);
		
		return result;
	}
	
	public static AlgorithmConfig lowPopLowGenConfig() {
		AlgorithmConfig result = new AlgorithmConfig();
		
		result.populationSize = LOW_POP;
		result.breedProbability = 0.7f;
		result.mutationProbability = 0.2f;
		result.populationFactory = new RandomPopulationFactory();
		result.qualityCalculator = new QualityCalculator();
		result.parentSelector = new RouletteParentSelector(result.qualityCalculator, result.breedProbability);
		result.toDieSelector = new RouletteToDieSelector(result.qualityCalculator);
		result.crossover = new UniformCrossover();
		result.mutator = new RandomMutator(result.mutationProbability);
		result.generationCount = LOW_GEN;
		result.condition = new GenerationTermination(result.generationCount);
		
		return result;
	}
	
	public static AlgorithmConfig highPopHighGenConfig() {
		AlgorithmConfig result = new AlgorithmConfig();
		
		result.populationSize = HIGH_POP;
		result.breedProbability = 0.7f;
		result.mutationProbability = 0.2f;
		result.populationFactory = new RandomPopulationFactory();
		result.qualityCalculator = new QualityCalculator();
		result.parentSelector = new RouletteParentSelector(result.qualityCalculator, result.breedProbability);
		result.toDieSelector = new RouletteToDieSelector(result.qualityCalculator);
		result.crossover = new UniformCrossover();
		result.mutator = new RandomMutator(result.mutationProbability);
		result.generationCount = HIGH_GEN;
		result.condition = new GenerationTermination(result.generationCount);
		
		return result;
	}
	
}
