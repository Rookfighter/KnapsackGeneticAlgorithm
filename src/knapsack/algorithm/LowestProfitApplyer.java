package knapsack.algorithm;

import knapsack.algorithm.interfaces.IRulesApplyer;
import knapsack.container.KnapsackIndividuum;

public class LowestProfitApplyer implements IRulesApplyer {

	@Override
	public void applyToRules(KnapsackIndividuum p_individuum) {
		int item = getLowestProfitItem(p_individuum);
		while(item >= 0 && p_individuum.isOverLimit()) {
			p_individuum.qualities()[item] = false;
			item = getLowestProfitItem(p_individuum);
		}
	}
	
	private int getLowestProfitItem(KnapsackIndividuum p_individuum) {
		int result = -1;
		
		for(int i = 0; i < p_individuum.qualities().length; ++i) {
			if(p_individuum.qualities()[i]) {
				if(result < 0)
					result = i;
				else if(p_individuum.problem().items()[i].profit < p_individuum.problem().items()[result].profit)
					result = i;
			}
		}
		
		return result;
	}

}
