package main.util;

/**
 * FactorialUtil
 * 
 * <br><br>
 * 
 * <B> Purpose </B>
 * 
 * <br><br>
 * 
 * Serves as a static library of Factorial-related utility methods.
 * 
 * <br>
 * 
 * @author RobReinold
 * 
 */
public final class FactorialUtil {

	/**
	 *  Do Not Instantiate
	 */
	private FactorialUtil() {
	}

	/**
	 * Generates factorial of a given number.
	 * 
	 * Warning: O(n) Complexity
	 * 
	 * @param An input value greater than or equal to 0
	 * @return Factorial function of n, n!
	 */
	public static int getFactorial(int n) {
		int product = 1;

		if (n > 1) {
			for (int i = n; i > 1; i--) {
				product *= i;
			}
		}
		
		return product;
	}
}
