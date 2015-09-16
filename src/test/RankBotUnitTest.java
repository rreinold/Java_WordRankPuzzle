package test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.RankBot;
import main.exceptions.RankException;

import org.junit.Before;
import org.junit.Test;

public class RankBotUnitTest {
	Map<LinkedList<Character>, Long> nonUniqueLetters = new HashMap<>();
	Map<LinkedList<Character>, Long> uniqueLetters = new HashMap<>();
	Map<LinkedList<Character>, Long> allCases = new HashMap<>();
	

	@Before
	public void before() {
		
		nonUniqueLetters.put(createListWith("ABAB"), 2L);
		nonUniqueLetters.put(createListWith("AAAB"), 1L);
		nonUniqueLetters.put(createListWith("BAAA"), 4L);
		nonUniqueLetters.put(createListWith("BOOKKEEPER"), 10743L);
		
		nonUniqueLetters.put(createListWith("B"), 1L);
		uniqueLetters.put(createListWith("QUESTION"), 24572L);
		
		allCases.putAll(uniqueLetters);
		allCases.putAll(nonUniqueLetters);
	}

	@Test
	public void runUniqueLettersTest() throws RankException{
		runTestWith(uniqueLetters);
	}
	
	@Test
	public void runNonUniqueLettersTest() throws RankException{
		runTestWith(nonUniqueLetters);
	}
	
	@Test(expected = RankException.class)
	public void runNullTest() throws RankException{
		RankBot<Character> rankBot = new RankBot<>(null);
		rankBot.calculateRank();
	}
	
	
	@Test(expected = RankException.class)
	public void runEmptyStringTest() throws RankException{
		RankBot<Character> rankBot = new RankBot<Character>(new LinkedList<Character>());
		rankBot.calculateRank();
	}
	

	private void runTestWith(Map<LinkedList<Character>,Long> map) throws RankException{
		for (Entry<LinkedList<Character>, Long> testCase : map.entrySet()) {
			
			LinkedList<Character> input = testCase.getKey();
			RankBot<Character> rankBot = new RankBot<Character>(input);
			
			long expectedOutput = testCase.getValue();
			long output = rankBot.calculateRank();
			
			assertEquals(
					"WordRank produced incorrect output.\n"
					+ "Input : " + input 
					+ " Output : " + output 
					+ " Expected Output : "	+ output, 
					expectedOutput, 
					output
					);
		}
	}
	
	private LinkedList<Character> createListWith(String input){
		LinkedList<Character> characterList = new LinkedList<>();
		for(char letter : input.toCharArray()){
			characterList.add(letter);
		}
		return characterList;
	}
}
