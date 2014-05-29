package knapsack.main;

import java.util.List;

import knapsack.algorithm.GeneticAlgorithm;
import knapsack.container.KnapsackProblem;
import knapsack.misc.KnapsackParser;

public class AlgorithmTest {

	public static void main(String[] args) {
		KnapsackParser parser = new KnapsackParser("res/mknapcb1.txt");
		GeneticAlgorithm algorithm = new GeneticAlgorithm();
		
		System.out.println("Starting Genetic Algorithm.");
		try {
			System.out.print("Parsing file...");
			parser.parse();
			System.out.println(" [Done]");
			
			List<KnapsackProblem> problems = parser.getProblems();
			
			System.out.print("Solving problems...");
			algorithm.solve(problems.get(0));
			System.out.println(" [Done]");
			
			System.out.print("Saving statistics...");
			algorithm.getStatistics().saveGnuPlotFiles("plots");
			System.out.println(" [Done]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Genetic Algorithm terminated.");

	}

}
