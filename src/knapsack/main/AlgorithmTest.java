package knapsack.main;

import java.util.List;

import knapsack.algorithm.GeneticAlgorithm;
import knapsack.container.KnapsackProblem;
import knapsack.misc.KnapsackParser;

public class AlgorithmTest {

	public static void main(String[] args) {
		KnapsackParser parser = new KnapsackParser("res/mknapcb1.txt");
		GeneticAlgorithm algorithm = new GeneticAlgorithm();
		
		try {
			parser.parse();
			
			List<KnapsackProblem> problems = parser.getProblems();
			
			algorithm.solve(problems.get(0));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(algorithm.getStatistics().toString());

	}

}
