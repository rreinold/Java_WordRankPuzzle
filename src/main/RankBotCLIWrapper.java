package main;

import java.util.LinkedList;

import main.exceptions.RankException;

/**
 * Rank Bot Command Line Interface (CLI) Wrapper
 * 
 * <B> Purpose </B>
 * 
 * The purpose of this class is to provide a command line interface for the
 * RankBot class.
 * 
 * <br><br>
 * 
 * @author RobReinold
 * 
 */
public class RankBotCLIWrapper {

	public static void main(String[] args) {

		if (args.length == 0 || args[0] == null) {

			System.out.println("Error: Input word cannot be empty or null");

		} else if (args[0].equals("")) {

			System.out.println("Error: Cannot generate rank of empty String.");

		} else {
			LinkedList<Character> input = createListWith(args);

			try {
				RankBot<Character> rankBot = new RankBot<>(input);
				long rank = rankBot.calculateRank();

				System.out.println("Rank of " + args[0] + " is " + rank
						+ "!\nThank you! Come again!");

			} catch (RankException e) {

				System.out.println("Error: " + e.toString());

			}

		}

	}

	/**
	 * Helper method for creating a list with a String
	 * 
	 * @param args
	 *            CLI Argument
	 * @return List<Character> of the String
	 */
	private static LinkedList<Character> createListWith(String[] args) {
		LinkedList<Character> input = new LinkedList<>();
		for (char letter : args[0].toCharArray()) {
			input.add(new Character(letter));
		}
		return input;
	}
}
