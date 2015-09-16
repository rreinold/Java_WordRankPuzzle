package main;

import main.util.FactorialUtil;

/**
 * <B>Decrementing Factorial</B>
 * 
 * <br>
 * <br>
 * 
 * <B> Purpose </B>
 * 
 * <br>
 * <br>
 * 
 * Factorial Implementation optimized for decrementation of its input n
 * 
 * <br>
 * <br>
 * 
 * <B> Approach </B>
 * 
 * <br>
 * <br>
 * 
 * Factorial(n) takes <B>O(n)</B> complexity. For our particular calculations,
 * we recognize our need for factorial(n),factorial(n-1),...,factorial(0). So,
 * we generate factorial in <B>O(n)</B> time. However, that point onwards, each
 * calculation will take <B>O(1)</B> time. <br>
 * <br>
 * i.e. factorial(n-1) = factorial(n) / n , n-- until it reaches 0
 * 
 * <br>
 * <br>
 * 
 * <B> Assumptions </B>
 * 
 * <br>
 * <br>
 * 
 * Input has already been validated to be greater than 0, so as to optimize
 * performance and readability.
 * 
 * <br>
 * <br>
 * 
 * @author RobReinold
 * 
 */
public class FastFactorial {
	/**
	 * Factorial of the current internal {@link #n}. This factorial is decreased
	 * each iteration by dividing by {@link #n}
	 */
	private int factorial;
	/**
	 * Current value with which factorial will be generated. This value is
	 * decremented as we proceed through calculations
	 */
	private int n;

	/**
	 * Initializes with the factorial of the max number
	 * 
	 * @param max
	 */
	public FastFactorial(int max) {
		this.n = max;
		factorial = FactorialUtil.getFactorial(max);
	}

	/**
	 * @return @see {@link #factorial}
	 */
	public int getFactorial() {
		return factorial;
	}

	/**
	 * @return @see {@link #n}
	 */
	public int getN() {
		return n;
	}

	/**
	 * Decrement n and then return factorial(n). If n < 1, decrement is not
	 * executed
	 * 
	 * @return @see {@link #n}
	 */
	public int decrementAndGet() {
		decrement();
		return factorial;
	}

	/**
	 * Decrement n if n > 1
	 */
	public void decrement() {
		if (n > 1) {
			factorial /= n;
			n--;
		}
	}

}
