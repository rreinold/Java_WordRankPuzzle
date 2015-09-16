package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.util.FactorialUtil;

/**
 * Permutation Scaling Calculator
 * 
 * <br>
 * <br>
 * 
 * <B> Purpose </B>
 * 
 * The purpose of this class is to determine the scale factor of permutations
 * containing multiple instances of the same type, in this case T. This scale
 * factor is required for the algorithm used in {@link RankBot}.
 * 
 * <br>
 * <br>
 * 
 * <B> Approach </B>
 * 
 * <br>
 * <br>
 * 
 * If we have n letters, and the number of instances(letter at n),
 * instances(letter at n-1) , ... , instances(letter at 0), then we need the
 * product of instances(letter at n)!,instances(letter at n-1)! , ... ,
 * instances(letter at 0)! <br>
 * For example, input = AABBBC instances(A) = 2, instances(B) = 3, instances(C)
 * = 1 Then we generate 2!*3!*1! = 24
 * 
 * <br>
 * <br>
 * 
 * @author RobReinold
 * 
 * @param <T>
 *            Items to be counted
 */
public class PermutationScalingCalculator<T> {
	/**
	 * Map containing items and its respective number of instances
	 */
	private final Map<T, Integer> letterCountFactorials;
	/**
	 * Scaling factor required to account for duplicates in rank.
	 */
	private int scaleFactor;

	public PermutationScalingCalculator(List<T> input) {
		this.letterCountFactorials = countInstances(input);
		scaleFactor = calculateScaleFactor(letterCountFactorials);
	}

	/**
	 * Decrement the number of instances for this letter
	 * 
	 * @param letter
	 *            to decrement instances
	 */
	public void markLetterUsed(T letter) {
		Integer instances = letterCountFactorials.get(letter);
		scaleFactor /= instances;
		letterCountFactorials.put(letter, --instances);
		scaleFactor = calculateScaleFactor(letterCountFactorials);
	}

	/**
	 * 
	 * @return @see {@link #scaleFactor}
	 */
	public int getScaleFactor() {
		return scaleFactor;
	}

	/**
	 * @param letterInstances
	 *            Map of items to occurrences
	 * @return @see Updated {@link #scaleFactor}
	 */
	private int calculateScaleFactor(Map<T, Integer> letterInstances) {
		int product = 1;
		for (Integer instances : letterInstances.values()) {
			product *= FactorialUtil.getFactorial(instances);
		}
		return product;
	}

	/**
	 * Iterate through items and count the number of instances of each
	 * 
	 * @param input
	 * @return
	 */
	private Map<T, Integer> countInstances(List<T> input) {
		Map<T, Integer> counter = new HashMap<>();
		for (T key : input) {
			Integer count = 0;
			if ((count = counter.get(key)) == null) {
				count = 0;
			}
			counter.put(key, ++count);
		}
		return counter;
	}

}
