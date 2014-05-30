package knapsack.main;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import knapsack.algorithm.GeneticAlgorithm;
import knapsack.container.KnapsackProblem;
import knapsack.misc.AlgorithmConfig;
import knapsack.misc.KnapsackParser;

public class AlgorithmTest {

	private static List<KnapsackProblem> problems;
	
	public static void main(String[] args) {
		KnapsackParser parser = new KnapsackParser("res/mknapcb1.txt");
		
		try {
			System.out.print("Parsing file...");
			parser.parse();
			System.out.println(" [Done]");
			AlgorithmTest.problems = parser.getProblems();
			
			runAlgorithm(AlgorithmConfig.highPopHighGenConfig());
			Thread.sleep(1000);
			/*runAlgorithm(AlgorithmConfig.highPopLowGenConfig());
			Thread.sleep(1000);
			runAlgorithm(AlgorithmConfig.lowPopHighGenConfig());
			Thread.sleep(1000);
			runAlgorithm(AlgorithmConfig.lowPopLowGenConfig());*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void runAlgorithm(AlgorithmConfig p_config) throws FileNotFoundException, UnsupportedEncodingException {
		long diff;
		System.out.println("=================================");
		System.out.println("Algorithm Config:");
		System.out.printf("Generations: %d\n", p_config.generationCount);
		System.out.printf("Population size: %d\n", p_config.populationSize);
		System.out.printf("Breed prob: %.2f\n", p_config.breedProbability);
		System.out.printf("Mutation prob: %.2f\n", p_config.mutationProbability);
		System.out.println("Starting Genetic Algorithm.");
		
		GeneticAlgorithm algorithm = new GeneticAlgorithm(p_config);
		for(int i = 0; i < 1; ++i) {
			System.out.printf("Solving problem %d...", i + 1);
			diff = System.nanoTime();
			algorithm.solve(AlgorithmTest.problems.get(i));
			diff = System.nanoTime() - diff;
			System.out.print(" [Done]");
			int ms = (int) (diff / 1000000);
			System.out.printf(" Time: [%dms] / [%.2fs]\n", ms, ((double) ms) / 1000.0);
		}
		
		System.out.print("Saving statistics...");
		algorithm.getStatistics().saveGnuPlotFiles("plots");
		System.out.println(" [Done]");
		System.out.println("Genetic Algorithm terminated.");
	}

}
