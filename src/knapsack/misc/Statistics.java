package knapsack.misc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import knapsack.container.KnapsackProblem;
import knapsack.container.Population;

public class Statistics {
	
	private static final String DATE_FORMAT_NOW = "yyyy-MM-dd-HH-mm-ss";

	private Deque<StatisticProblemElement> problems;
	
	public Statistics() {
		problems = new LinkedList<StatisticProblemElement>();
	}
	
	public void nextProblem(KnapsackProblem p_problem) {
		problems.add(new StatisticProblemElement(p_problem));
	}
	
	public void nextGeneration(Population p_population) {
		problems.getLast().generations().add(new StatisticGenerationElement(p_population));
	}
	
	public void saveGnuPlotFiles(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		if(p_dir.endsWith("/") || p_dir.endsWith("\\") && !p_dir.isEmpty())
			p_dir = p_dir.substring(0, p_dir.length() - 2);
		
		String dirName = String.format("%s/%s", p_dir, now());
		
		createDirectories(dirName);
		
		saveTotalWeightFiles(dirName);
		saveTotalProfitFiles(dirName);
		saveMeanWeightFiles(dirName);
		saveMeanProfitFiles(dirName);
		saveHighestIndividuumProfitFile(dirName);
		saveHighestKnapsackProfitFile(dirName);
		saveWeightRatioFile(dirName);
	}
	
	private void createDirectories(String p_dir) {
		
		for(int i = 0; i < problems.size(); ++i) {
			File file = new File(String.format("%s/problem%d", p_dir,  i));
			if(!file.mkdirs())
				throw new IllegalArgumentException("Could not create directories for plot files");
		}
	}
	
	private void saveTotalWeightFiles(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			PrintWriter writer = new PrintWriter(String.format("%s/problem%d/totalWeight.txt", p_dir, i), "UTF-8");
			try {
				int k = 0;
				for(StatisticGenerationElement generation: problem.generations()) {
					writer.printf(Locale.US, "%d \t %.2f\n", k, generation.totalWeight());
					k++;
				}
			} finally {
				writer.close();
			}
			++i;
		}
	}

	private void saveTotalProfitFiles(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			PrintWriter writer = new PrintWriter(String.format("%s/problem%d/totalProfit.txt", p_dir, i), "UTF-8");
			try {
				int k = 0;
				for(StatisticGenerationElement generation: problem.generations()) {
					writer.printf(Locale.US, "%d \t %.2f\n", k, generation.totalProfit());
					k++;
				}
			} finally {
				writer.close();
			}
			i++;
		}
	}
	
	private void saveMeanWeightFiles(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			PrintWriter writer = new PrintWriter(String.format("%s/problem%d/meanWeight.txt", p_dir, i), "UTF-8");
			try {
				int k = 0;
				for(StatisticGenerationElement generation: problem.generations()) {
					writer.printf(Locale.US, "%d \t %.2f\n", k, generation.meanWeight());
					k++;
				}
			} finally {
				writer.close();
			}
			i++;
		}
	}
	
	private void saveMeanProfitFiles(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			PrintWriter writer = new PrintWriter(String.format("%s/problem%d/meanProfit.txt", p_dir, i), "UTF-8");
			try {
				int k = 0;
				for(StatisticGenerationElement generation: problem.generations()) {
					writer.printf(Locale.US, "%d \t %.2f\n", k, generation.meanProfit());
					k++;
				}
			} finally {
				writer.close();
			}
			i++;
		}
	}
	
	private void saveHighestIndividuumProfitFile(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			PrintWriter writer = new PrintWriter(String.format("%s/problem%d/maxIndividuumProfit.txt", p_dir, i), "UTF-8");
			try {
				int k = 0;
				for(StatisticGenerationElement generation: problem.generations()) {
					writer.printf(Locale.US, "%d \t %.2f\n", k, generation.highestIndividuumProfit());
					k++;
				}
			} finally {
				writer.close();
			}
			i++;
		}
	}
	
	private void saveHighestKnapsackProfitFile(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			PrintWriter writer = new PrintWriter(String.format("%s/problem%d/maxKnapsackProfit.txt", p_dir, i), "UTF-8");
			try {
				int k = 0;
				for(StatisticGenerationElement generation: problem.generations()) {
					writer.printf(Locale.US, "%d \t %.2f\n", k, generation.highestKnapsackProfit());
					k++;
				}
			} finally {
				writer.close();
			}
			i++;
		}
	}
	
	private void saveWeightRatioFile(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			PrintWriter writer = new PrintWriter(String.format("%s/problem%d/weightRatio.txt", p_dir, i), "UTF-8");
			try {
				int k = 0;
				for(StatisticGenerationElement generation: problem.generations()) {
					writer.printf(Locale.US, "%d \t %.2f\n", k, generation.weightRatio());
					k++;
				}
			} finally {
				writer.close();
			}
			i++;
		}
	}
	
	private String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			sb.append("Problem ").append(i + 1).append(":\n").append(problem.toString()).append("\n");
			++i;
		}
		
		return sb.toString().trim();
	}
	
}
