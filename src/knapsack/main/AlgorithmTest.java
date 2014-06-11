package knapsack.main;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

import knapsack.algorithm.GeneticAlgorithm;
import knapsack.container.KnapsackProblem;
import knapsack.misc.AlgorithmConfig;
import knapsack.misc.KnapsackParser;

public class AlgorithmTest {

	private static Scanner scanner = new Scanner(System.in);
	private static List<KnapsackProblem> problems;
	
	public static void main(String[] args) {
		KnapsackParser parser = new KnapsackParser("res/mknap1.txt");
		
		try {
			System.out.print("Parsing file...");
			parser.parse();
			System.out.println(" [Done]");
			AlgorithmTest.problems = parser.getProblems();
			
			runAlgorithm(AlgorithmConfig.highPopHighGenConfig(), 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private static void userInteraction() throws FileNotFoundException, UnsupportedEncodingException {
		int problem;
		while(true) {
			System.out.printf("Choose your problem (0-%d): ", problems.size() - 1);
			if(scanner.hasNextInt()) {
				problem = scanner.nextInt();
				break;
			} else {
				scanner.next();
				System.out.println("Please enter a number.");
			}
		}
		
		int populationSize;
		while(true) {
			System.out.print("Choose population size: ");
			if(scanner.hasNextInt()) {
				populationSize = scanner.nextInt();
				break;
			} else {
				scanner.next();
				System.out.println("Please enter a number.");
			}
		}
		
		int generationCount;
		while(true) {
			System.out.print("Choose generation count: ");
			if(scanner.hasNextInt()) {
				generationCount = scanner.nextInt();
				break;
			} else {
				scanner.next();
				System.out.println("Please enter a number.");
			}
		}
		
		float breedProbability;
		while(true) {
			System.out.print("Choose breed probability: ");
			if(scanner.hasNextFloat()) {
				breedProbability = scanner.nextFloat();
				break;
			} else {
				scanner.next();
				System.out.println("Please enter a number.");
			}
		}
		
		float mutationProbability;
		while(true) {
			System.out.print("Choose mutation probability: ");
			if(scanner.hasNextFloat()) {
				mutationProbability = scanner.nextFloat();
				break;
			} else {
				scanner.next();
				System.out.println("Please enter a number.");
			}
		}
		
		runAlgorithm(AlgorithmConfig.createConfig(populationSize, generationCount, breedProbability, mutationProbability), problem);
	}
	
	private static void runAlgorithm(AlgorithmConfig p_config, final int p_problem) throws FileNotFoundException, UnsupportedEncodingException {
		long diff;
		System.out.println("=================================");
		System.out.println("Algorithm Config:");
		System.out.printf("Generations: %d\n", p_config.generationCount);
		System.out.printf("Population size: %d\n", p_config.populationSize);
		System.out.printf("Breed prob: %.2f\n", p_config.breedProbability);
		System.out.printf("Mutation prob: %.2f\n", p_config.mutationProbability);
		System.out.println("Starting Genetic Algorithm.");
		
		GeneticAlgorithm algorithm = new GeneticAlgorithm(p_config);
		System.out.printf("Solving problem %d...", p_problem + 1);
		diff = System.nanoTime();
		algorithm.solve(AlgorithmTest.problems.get(p_problem));
		diff = System.nanoTime() - diff;
		System.out.print(" [Done]");
		int ms = (int) (diff / 1000000);
		System.out.printf(" Time: [%dms] / [%.2fs]\n", ms, ((double) ms) / 1000.0);
		
		System.out.print("Saving statistics...");
		algorithm.getStatistics().saveGnuPlotFiles("plots");
		System.out.println(" [Done]");
		System.out.println("Genetic Algorithm terminated.");
		System.out.println("=================================");
	}

}
