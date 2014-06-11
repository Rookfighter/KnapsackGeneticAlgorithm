package knapsack.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import knapsack.container.KnapsackProblem;

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
		
		String line = getNextFilledLine();
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
		String line = getNextFilledLine();
		
		String[] words = line.split(" ");
		if(words.length < 2)
			throw new IllegalArgumentException(String.format("First line of problem has too less parameter: %d", words.length));
		
		int variableCount = Integer.parseInt(words[0]);
		int constraintCount = Integer.parseInt(words[1]);
		
		//init data structure
		KnapsackProblem problem = new KnapsackProblem(constraintCount, variableCount);
		
		//read profits
		for(int i = 0; i < variableCount; i += words.length) {
			words = getNextFilledLine().split(" ");
			for(int j = 0; j < words.length; ++j) {
				double value = Double.parseDouble(words[j]);
				for(int k = 0; k < constraintCount; ++k)
					problem.partProblems()[k].items()[i + j].profit = value;
			}
		}
		
		//read weights
		for(int i = 0; i < constraintCount; ++i) {
			for(int j = 0; j < variableCount; j += words.length) {
				words = getNextFilledLine().split(" ");
				for(int k = 0; k < words.length; ++k)
					problem.partProblems()[i].items()[j + k].weight = Double.parseDouble(words[k]);
			}
		}
		
		//read max weights
		for(int i = 0; i < constraintCount; i += words.length) {
			words = getNextFilledLine().split(" ");
			for(int j = 0; j < words.length; ++j)
				problem.partProblems()[i + j].setKnappsackSize(Double.parseDouble(words[j]));
		}
		
		return problem;
	}
	
	private String getNextFilledLine() throws IOException {
		String line;
		do {
			line = file.readLine().trim();
		} while(line == null || line.isEmpty());
		
		return line;
	}
	
	public List<KnapsackProblem> getProblems() {
		return problems;
	}
	
}
