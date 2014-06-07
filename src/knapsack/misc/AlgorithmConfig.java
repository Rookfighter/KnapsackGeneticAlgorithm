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
	private static final int HIGH_GEN = 100;
	private static final int LOW_POP = 10;
	private static final int HIGH_POP = 100;
	
	public static AlgorithmConfig lowPopHighGenConfig() {
		return createConfig(LOW_POP, HIGH_GEN, 0.7f, 0.2f);
	}
	
	public static AlgorithmConfig highPopLowGenConfig() {
		return createConfig(HIGH_POP, LOW_GEN, 0.7f, 0.2f);
	}
	
	public static AlgorithmConfig lowPopLowGenConfig() {
		return createConfig(LOW_POP, LOW_GEN, 0.7f, 0.2f);
	}
	
	public static AlgorithmConfig highPopHighGenConfig() {
		
		return createConfig(HIGH_POP, HIGH_GEN, 0.7f, 0.2f);
	}
	
	public static AlgorithmConfig createConfig(final int p_populationSize,
											   final int p_generationCount,
											   final float p_breedProbability,
											   final float p_mutationProbability) {
		AlgorithmConfig result = new AlgorithmConfig();
		result.populationSize = p_populationSize;
		result.breedProbability = p_breedProbability;
		result.mutationProbability = p_mutationProbability;
		result.populationFactory = new RandomPopulationFactory();
		result.qualityCalculator = new QualityCalculator();
		result.parentSelector = new RouletteParentSelector(result.qualityCalculator, result.breedProbability, result.populationSize);
		result.toDieSelector = new RouletteToDieSelector(result.qualityCalculator, result.populationSize);
		result.crossover = new UniformCrossover();
		result.mutator = new RandomMutator(result.mutationProbability, result.populationSize);
		result.generationCount = p_generationCount;
		result.condition = new GenerationTermination(result.generationCount);
		
		return result;
	}
	
}
