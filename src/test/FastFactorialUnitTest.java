package test;

import static org.junit.Assert.assertEquals;
import main.FastFactorial;
import main.util.FactorialUtil;

import org.junit.Test;
public class FastFactorialUnitTest {
	FastFactorial fastFactorial;

	@Test
	public void testNegative() {
		fastFactorial = new FastFactorial(-1);
		int factorial = fastFactorial.getFactorial();
		assertEquals(
				"FastFactorial failed to evaluluate negative factorials",
				1,
				factorial
				);
	}
	
	@Test
	public void testNegative2() {
		int expected = -1;
		fastFactorial = new FastFactorial(-1);
		fastFactorial.decrementAndGet();
		int n = fastFactorial.getN();
		assertEquals(
				"FastFactorial incorrectly decremented a negative number",
				expected,
				n
				);
	}
	@Test
	public void testDecrement(){
		int n = 12;
		fastFactorial = new FastFactorial(n);
		
		while(n >= 0){
			int expectedFactorial = FactorialUtil.getFactorial(n);
			int factorial = fastFactorial.getFactorial();
			
			assertEquals(
					"FastFactorial decrementation test failed.",
					expectedFactorial,
					factorial);
			
			n--;
			fastFactorial.decrement();
		}
	}
	
	@Test
	public void testDecrementAndGet(){
		int n = 12;
		fastFactorial = new FastFactorial(n);
		
		while(n >= 0){
			int expectedFactorial = FactorialUtil.getFactorial(n-1);
			int factorial = fastFactorial.decrementAndGet();
			
			assertEquals(
					"FastFactorial decrementation test failed.",
					expectedFactorial,
					factorial);
			
			n--;
		}
	}
}
