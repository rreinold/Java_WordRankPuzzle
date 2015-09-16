package test.util;

import static org.junit.Assert.assertEquals;
import main.util.FactorialUtil;

import org.junit.Test;

public class FactorialUtilUnitTest {
	@Test
	public void testNegative() {
		assertEquals("FactorialUtil said factorial(-1) != 1",
				FactorialUtil.getFactorial(-1), 1);
	}
	
	@Test
	public void testZero() {
		assertEquals("FactorialUtil said factorial(0) != 1",
				FactorialUtil.getFactorial(0), 1);
	}

	@Test
	public void testOne() {
		assertEquals("FactorialUtil said factorial(1) != 1",
				FactorialUtil.getFactorial(1), 1);
	}

	@Test
	public void testSet() {
		assertEquals("FactorialUtil said factorial(4) != 24",
				FactorialUtil.getFactorial(4), 24);
		
		assertEquals("FactorialUtil said factorial(7) != 5040",
				FactorialUtil.getFactorial(7), 5040);

		assertEquals("FactorialUtil said factorial(12) != 479001600",
				FactorialUtil.getFactorial(12), 479001600);
		
	}
}
