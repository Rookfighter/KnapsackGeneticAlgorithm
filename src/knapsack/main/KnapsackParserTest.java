package knapsack.main;

import java.util.List;

import knapsack.container.KnapsackProblem;
import knapsack.misc.KnapsackParser;

public class KnapsackParserTest {

	public static void main(String[] args) {
		KnapsackParser parser = new KnapsackParser("res/mknap1.txt");
		try {
			parser.parse();
			
			List<KnapsackProblem> problems = parser.getProblems();
			
			for(int i = 0; i < problems.size(); ++i) {
				System.out.printf("Problem %d:\n", i + 1);
				KnapsackProblem problem = problems.get(i);
				System.out.println(problem.toString());
			}
			
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

}
