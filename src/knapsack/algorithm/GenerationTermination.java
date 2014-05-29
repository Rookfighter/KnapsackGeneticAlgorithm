package knapsack.algorithm;

import knapsack.algorithm.interfaces.ITerminationCondition;

public class GenerationTermination implements ITerminationCondition {

	private int targetGeneration;
	private int currentGeneration;
	
	public GenerationTermination(final int p_targetGeneration) {
		targetGeneration = p_targetGeneration;
		currentGeneration = 0;
	}
	
	@Override
	public boolean terminate() {
		currentGeneration++;
		return currentGeneration >= targetGeneration ;
	}

}
