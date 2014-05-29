package knapsack.main;

import java.util.List;

import knapsack.container.KnapsackProblem;
import knapsack.misc.KnapsackParser;

public class KnapsackParserTest {

	public static void main(String[] args) {
		KnapsackParser parser = new KnapsackParser("mknapcb1.txt");
		try {
			parser.parse();
			
			List<KnapsackProblem> problems = parser.getProblems();
			
			for(int i = 0; i < problems.size(); ++i) {
				System.out.printf("Problem %d:%n", i + 1);
				KnapsackProblem problem = problems.get(i);
				for(int j = 0; j < problem.taskCount(); ++j)
					System.out.println(problem.getTask(j).toString());
			}
			
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace();
		}
	}

}
