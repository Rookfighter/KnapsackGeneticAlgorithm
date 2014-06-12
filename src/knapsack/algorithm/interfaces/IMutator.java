package knapsack.algorithm.interfaces;

import java.util.List;

import knapsack.container.KnapsackIndividuum;


public interface IMutator {

	void mutate(List<KnapsackIndividuum> p_children);
}
