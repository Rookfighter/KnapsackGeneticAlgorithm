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
import knapsack.container.KnapsackTask;
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
	
	public void nextTask(KnapsackTask p_task) {
		problems.getLast().tasks().add(new StatisticTaskElement(p_task));
	}
	
	public void nextGeneration(Population p_population) {
		problems.getLast().tasks().getLast().generations().add(new StatisticGenerationElement(p_population));
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
			int j = 0;
			for(StatisticTaskElement task: problem.tasks())
			{
				PrintWriter writer = new PrintWriter(String.format("%s/problem%d/task%d_totalWeight.txt", p_dir, i, j), "UTF-8");
				try {
					int k = 0;
					for(StatisticGenerationElement generation: task.generations()) {
						writer.printf(Locale.US, "%d \t %.2f\n", k, generation.totalWeight());
						k++;
					}
				} finally {
					writer.close();
				}
				++j;
			}
			++i;
		}
	}

	private void saveTotalProfitFiles(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			int j = 0;
			for(StatisticTaskElement task: problem.tasks())
			{
				PrintWriter writer = new PrintWriter(String.format("%s/problem%d/task%d_totalProfit.txt", p_dir, i, j), "UTF-8");
				try {
					int k = 0;
					for(StatisticGenerationElement generation: task.generations()) {
						writer.printf(Locale.US, "%d \t %.2f\n", k, generation.totalProfit());
						k++;
					}
				} finally {
					writer.close();
				}
				j++;
			}
			i++;
		}
	}
	
	private void saveMeanWeightFiles(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			int j = 0;
			for(StatisticTaskElement task: problem.tasks())
			{
				PrintWriter writer = new PrintWriter(String.format("%s/problem%d/task%d_meanWeight.txt", p_dir, i, j), "UTF-8");
				try {
					int k = 0;
					for(StatisticGenerationElement generation: task.generations()) {
						writer.printf(Locale.US, "%d \t %.2f\n", k, generation.meanWeight());
						k++;
					}
				} finally {
					writer.close();
				}
				j++;
			}
			i++;
		}
	}
	
	private void saveMeanProfitFiles(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			int j = 0;
			for(StatisticTaskElement task: problem.tasks())
			{
				PrintWriter writer = new PrintWriter(String.format("%s/problem%d/task%d_meanProfit.txt", p_dir, i, j), "UTF-8");
				try {
					int k = 0;
					for(StatisticGenerationElement generation: task.generations()) {
						writer.printf(Locale.US, "%d \t %.2f\n", k, generation.meanProfit());
						k++;
					}
				} finally {
					writer.close();
				}
				j++;
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
