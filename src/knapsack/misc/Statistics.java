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

import com.panayotis.gnuplot.JavaPlot;
import com.panayotis.gnuplot.plot.AbstractPlot;
import com.panayotis.gnuplot.plot.DataSetPlot;
import com.panayotis.gnuplot.style.PlotStyle;
import com.panayotis.gnuplot.style.Style;
import com.panayotis.gnuplot.utils.Debug;

import knapsack.container.KnapsackProblem;
import knapsack.container.Population;

public class Statistics {
	
	private static final String DATE_FORMAT_NOW = "yyyy-MM-dd-HH-mm-ss";

	private Deque<StatisticProblemElement> problems;
	private float breedProbability;
	private float mutationProbability;
	private int generationCount;
	private int populationSize;
	
	public Statistics(AlgorithmConfig p_config) {
		problems = new LinkedList<StatisticProblemElement>();
		breedProbability = p_config.breedProbability;
		mutationProbability = p_config.mutationProbability;
		generationCount = p_config.generationCount;
		populationSize = p_config.populationSize;
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
		saveConfig(dirName);
		
		saveTotalProfit(dirName);
		saveMeanProfit(dirName);
		saveMaxProfit(dirName);
	}
	
	private void createDirectories(String p_dir) {
		
		for(int i = 0; i < problems.size(); ++i) {
			File file = new File(String.format("%s/problem%d", p_dir,  i));
			if(!file.mkdirs())
				throw new IllegalArgumentException("Could not create directories for plot files");
		}
	}
	
	private void saveConfig(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(String.format("%s/configuration.txt", p_dir), "UTF-8");
		try {
			writer.printf(Locale.US, "Breed probability: %.4f\n", breedProbability);
			writer.printf(Locale.US, "Mutation probability: %.4f\n", mutationProbability);
			writer.printf(Locale.US, "Generation count: %d\n", generationCount);
			writer.printf(Locale.US, "Population size: %d\n", populationSize);
		} finally {
			writer.close();
		}
	}

	private void saveTotalProfit(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
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
	
	private void saveMeanProfit(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
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
	
	private void saveMaxProfit(String p_dir) throws FileNotFoundException, UnsupportedEncodingException {
		int i = 0;
		for(StatisticProblemElement problem: problems) {
			PrintWriter writer = new PrintWriter(String.format("%s/problem%d/maxProfit.txt", p_dir, i), "UTF-8");
			try {
				int k = 0;
				for(StatisticGenerationElement generation: problem.generations()) {
					writer.printf(Locale.US, "%d \t %.2f\n", k, generation.maxProfit());
					k++;
				}
			} finally {
				writer.close();
			}
			i++;
		}
	}
	
	public void plot() {
		for(StatisticProblemElement problem : problems)
			plotMeanProfit(problem);
	}
	
	private void plotMeanProfit(StatisticProblemElement p_problem) {
		JavaPlot plot = new JavaPlot();
        JavaPlot.getDebugger().setLevel(Debug.QUIET);
        
        plot.setTitle("Mean Profit");
        plot.getAxis("x").setLabel("Generation");
        plot.getAxis("y").setLabel("Profit");
        
        double[][] plots = new double[p_problem.generations().size()][2];
        
        int i = 0;
        for(StatisticGenerationElement generation : p_problem.generations()) {
        	plots[i][0] = i;
        	plots[i][1] = generation.meanProfit();
        	i++;
        }
        
        DataSetPlot dataSet = new DataSetPlot(plots);
        plot.addPlot(dataSet);
        
        PlotStyle stl = ((AbstractPlot) plot.getPlots().get(0)).getPlotStyle();
        stl.setStyle(Style.LINES);
        plot.plot();
        
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
	
	public float breedProbability() {
		return breedProbability;
	}
	
	public float mutationProbability() {
		return mutationProbability;
	}
	
	public int generationCount() {
		return generationCount;
	}
	
	public int populationSize() {
		return populationSize;
	}
	
}
