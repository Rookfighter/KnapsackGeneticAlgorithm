package knapsack.algorithm;

import knapsack.algorithm.interfaces.ITerminationCondition;

public class TimeTermination implements ITerminationCondition {

	private double targetSeconds;
	private long usecBegin;
	
	
	public TimeTermination(double p_seconds) {
		targetSeconds = p_seconds;
		usecBegin = System.nanoTime() / 1000;
	}
	
	@Override
	public boolean terminate() {
		double secDiff = ((double) ((System.nanoTime() / 1000) - usecBegin)) / 1000000;
		return secDiff >= targetSeconds;
	}

	@Override
	public void reset() {
		usecBegin = System.nanoTime() / 1000;
	}

}
