package knapsack.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import knapsack.container.Knapsack;
import knapsack.container.KnapsackItem;
import knapsack.container.KnapsackProblem;
import knapsack.container.KnapsackTask;

public class KnapsackParser {

	private String filePath;
	private int problemCount;
	
	
	private List<KnapsackProblem> problems;
	
	private BufferedReader file;
	
	public KnapsackParser(String p_file) {
		filePath = p_file;
	}
	
	public void parse() throws IOException {
		file = new BufferedReader(new FileReader(filePath));
		
		try {
			readFirstLine();
			readProblems();
		} finally {
			file.close();
		}
	}
	
	private void readFirstLine() throws IOException {
		String line = file.readLine().trim();
		while(line != null && line.isEmpty())
			line = file.readLine().trim();
		
		String[] words = line.split(" ");
		
		if(words.length != 1)
			throw new IllegalArgumentException(String.format("First line of file has invalid wordcount: %d", words.length));
		
		problemCount = Integer.parseInt(words[0]);
	}
	
	private void readProblems() throws IOException {
		problems = new ArrayList<KnapsackProblem>(problemCount);
		
		for(int i = 0; i < problemCount; ++i)
			problems.add(readNextProblem());
	}
	
	private KnapsackProblem readNextProblem() throws IOException {
		String line = file.readLine().trim();
		String[] words = line.split(" ");
		if(words.length < 2)
			throw new IllegalArgumentException(String.format("First line of problem has too less parameter: %d", words.length));
		
		int variableCount = Integer.parseInt(words[0]);
		int constraintCount = Integer.parseInt(words[1]);
		
		//init data structure
		KnapsackProblem problem = new KnapsackProblem(constraintCount);
		for(int i = 0; i < constraintCount; ++i) {
			KnapsackTask task = new KnapsackTask(variableCount);
			for(int j = 0; j < variableCount; ++j)
				task.items().add(new KnapsackItem());
			task.setKnapsack(new Knapsack());		
			problem.tasks().add(task);
		}
		
		//read values
		for(int i = 0; i < variableCount; i += words.length) {
			words = file.readLine().trim().split(" ");
			for(int j = 0; j < words.length; ++j) {
				float value = Float.parseFloat(words[j]);
				for(int k = 0; k < constraintCount; ++k)
					problem.tasks().get(k).items().get(i + j).profit = value;
			}
		}
		
		//read weights
		for(int i = 0; i < constraintCount; ++i) {
			for(int j = 0; j < variableCount; j += words.length) {
				words = file.readLine().trim().split(" ");
				for(int k = 0; k < words.length; ++k)
					problem.tasks().get(i).items().get(j + k).weight = Float.parseFloat(words[k]);
			}
		}
		
		//read max weights
		for(int i = 0; i < constraintCount; i += words.length) {
			words = file.readLine().trim().split(" ");
			for(int j = 0; j < words.length; ++j)
				problem.tasks().get(i + j).getKnapsack().maxWeight = Float.parseFloat(words[j]);
		}
		
		return problem;
	}
	
	public List<KnapsackProblem> getProblems() {
		ArrayList<KnapsackProblem> result = new ArrayList<KnapsackProblem>(problems.size());
		for(KnapsackProblem problem : problems)
			result.add(problem.copy());
		
		return result;
	}
	
}
