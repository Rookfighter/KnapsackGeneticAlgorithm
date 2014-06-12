package knapsack.container;

import java.util.Locale;

public class KnapsackItem {

	public float profit;
	private float[] constraints;
	
	public KnapsackItem(final int p_constraintCount) {
		constraints = new float[p_constraintCount];
		profit = 0;
	}
	
	public float[] constraints() {
		return constraints;
	}
	
	public String toString(final int p_constraint) {
		return String.format(Locale.US, "(%.2f,%.2f)", profit, constraints[p_constraint]);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format(Locale.US, "(%.2f:", profit));
		for(int i = 0; i < constraints.length; ++i)
			sb.append(String.format(Locale.US, "%.2f,", constraints[i]));
		if(sb.charAt(sb.length() - 1) == ',')
			sb.deleteCharAt(sb.length() - 1);
		sb.append(")");
		return sb.toString();
	}
	
}
