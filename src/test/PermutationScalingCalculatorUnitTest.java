package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.PermutationScalingCalculator;

import org.junit.Before;
import org.junit.Test;

public class PermutationScalingCalculatorUnitTest {
	private PermutationScalingCalculator<Character> calculator;
	private Map<List<Character>, Integer> input = new HashMap<>();

	@Before
	public void before() {
		input.put(createListWith("RRROOB"),12);
		input.put(createListWith("RROOB"),4);
		input.put(createListWith("ROOB"),2);
		input.put(createListWith("RRROOBB"),24);
		
		input.put(createListWith("ATHEN"), 1);
		input.put(createListWith("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),1);
	}

	@Test
	public void testVarietyOfInputs() {
		for(Entry<List<Character>, Integer> testCase : input.entrySet()){
			
			calculator = new PermutationScalingCalculator<>(testCase.getKey());
			assertEquals(
					"Scale factor is incorrect given "+ testCase.getKey(),
					testCase.getValue().intValue(),
					calculator.getScaleFactor()
					);
		}

	}

	private LinkedList<Character> createListWith(String input) {
		LinkedList<Character> characterList = new LinkedList<>();
		for (char letter : input.toCharArray()) {
			characterList.add(letter);
		}
		return characterList;
	}
}
