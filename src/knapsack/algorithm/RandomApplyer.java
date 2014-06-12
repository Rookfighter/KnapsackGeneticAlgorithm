package knapsack.algorithm;

import java.util.Random;

import knapsack.algorithm.interfaces.IRulesApplyer;
import knapsack.container.KnapsackIndividuum;

public class RandomApplyer implements IRulesApplyer {

	private final Random random = new Random();
	@Override
	public void applyToRules(KnapsackIndividuum p_individuum) {
		while(p_individuum.isOverLimit()) {
			int item = random.nextInt(p_individuum.qualities().length);
			if(p_individuum.qualities()[item])
				p_individuum.qualities()[item] = false;
		}
	}

}
