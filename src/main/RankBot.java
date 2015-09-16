package main;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import main.exceptions.RankException;

/**
 * Rank Bot
 * 
 * <br>
 * <br>
 * 
 * <B> Purpose </B>
 * 
 * <br>
 * <br>
 * 
 * The purpose of this class is to determine the rank of a list of items amongst
 * its fellow permutations. This is designed to work with any Object that
 * implements the {@link Comparable} interface. In this case, however, variable
 * names and JavaDoc will reflect the use of the Character type to better
 * explain the code.
 * 
 * <br>
 * <br>
 * 
 * <B> Approach </B>
 * 
 * <br>
 * <br>
 * The goal of my approach is to break this down into smaller, more manageable
 * problems. The smallest problem we solve is "What is the effect a single
 * character has on the rank of the entire word?" The calculation required for
 * this letter's contribution is as follows:
 * 
 * <br>
 * <br>
 * 
 * <B>LetterRankAmongRemaining</B> = rank of this letter among the remaining
 * unprocessed letter ex. AAABBC, B has rank 3
 * 
 * <br>
 * 
 * <B>(NumRemainingLetters - 1)!</B> = Combinations of letters excluding the
 * first letter. ex. ABCD would yield (3)! = 6 combinations begin with A,B,C, or
 * D This value must be scaled to account for duplicates
 * 
 * <br>
 * 
 * <B> Scaling Factor</B> This is the scaling factor that allows us to
 * accurately account for duplicate combinations. <br>
 * See {@link PermutationScalingCalculator} for implementation
 * 
 * <br>
 * <br>
 * 
 * <B>Core Calculation</B>
 * 
 * <br>
 * 
 * Letter's Contribution to Overall Rank = LetterRankAmongRemaining *
 * (NumRemainingLetters - 1)! / Scaling Factor
 * 
 * <br>
 * <br>
 * 
 * <B> Performance </B>
 * 
 * <br>
 * <br>
 * This algorithm has the following Big O Complexity:
 * 
 * <br>
 * 
 * Best: O(nlogn)
 * 
 * <br>
 * 
 * Worst: O(n^2)
 * 
 * <br>
 * <br>
 * 
 * <B> Input <B>
 * 
 * <br>
 * <br>
 * 
 * In order to keep the rank below what a 64-bit integer can hold (long in
 * Java), the input String must be:
 * 
 * <br>
 * 
 * Below 6.07GB for an unsigned long Below 4.29GB for a signed long.
 * 
 * Unsigned Long <br>
 * (x)! = 2^64 (1+sqrt(2^67+1)) / 2 <br>
 * -b +/- sqrt ( b^2 - 4ac) / 2a <br>
 * String must have less than 6074001000.45
 * 
 * Signed Long <br>
 * (x)! = 2^63 (1+sqrt(2^66+1)) / 2 <br>
 * -b +/- sqrt ( b^2 - 4ac) / 2a <br>
 * 
 * @author RobReinold
 */
public class RankBot<T extends Comparable<T>> {
	/**
	 * List of items to be sorted. This array is not modified throughout the
	 * class to maintain the original order for reference.
	 */
	private final List<T> originalInput;
	/**
	 * This is the sorted list of items passed in. This will be modified as we
	 * progress through the algorithm.
	 */
	private final List<T> sortedInput;
	/**
	 * Numbers of items in originalInput List.
	 */
	private final int inputStringLength;

	/**
	 * Rankbot must take in a {@link LinkedList} containing the items to be
	 * sorted.
	 * 
	 * @param input
	 * @throws RankException
	 *             thrown if input is null or empty
	 */
	public RankBot(LinkedList<T> input) throws RankException {

		validate(input);

		inputStringLength = input.size();
		originalInput = input;
		sortedInput = (List<T>) input.clone();
	}

	private void validate(List<T> input) throws RankException {
		if (input == null) {
			throw new RankException(
					"Word cannot be ranked. Reason: Input String cannot be null");
		} else if (input.size() == 0) {
			throw new RankException(
					"Word cannot be ranked. Reason: Input String cannot be empty");
		}
	}

	/**
	 * Calculate the rank of this RankBot's word among its fellow permutations
	 * 
	 * @return rank of word
	 */
	public long calculateRank() {

		sortInput();

		// Calculate how duplicate characters affect rank
		PermutationScalingCalculator<T> duplicateTracker = new PermutationScalingCalculator<>(
				originalInput);

		// Required for calculations
		FastFactorial fastFactorial = new FastFactorial(inputStringLength - 1);
		// Rank begins with 1, rather than 0
		long rank = 1;
		// Iterate over each letter
		for (T letter : originalInput) {

			// Each letter contributes to the rank of input.
			int scaleFactorCausedByDuplicates = duplicateTracker
					.getScaleFactor();
			int totalCombinationsOfRemainingLetters = fastFactorial
					.getFactorial();
			long lettersContributionToRank = getRankOfIndividualLetter(letter,
					scaleFactorCausedByDuplicates,
					totalCombinationsOfRemainingLetters);

			rank += lettersContributionToRank;

			duplicateTracker.markLetterUsed(letter);
			fastFactorial.decrement();
			sortedInput.remove(letter);
		}

		return rank;
	}

	/**
	 * The rank of each letter contributes to its overall rank. This is the core
	 * computation which breaks the problem down into the impact of each
	 * individual letter.
	 * 
	 * @param letter current letter
	 * @param scaleFactor @see {@link PermutationScalingCalculator}
	 * @param factorial 
	 * @return
	 */
	private long getRankOfIndividualLetter(T letter, int scaleFactor,
			int factorial) {

		long letterRank = getLetterRankAmongRemaining(letter);

		return letterRank * (factorial / scaleFactor);
	}

	/**
	 * We process the input from index 0 to index length - 1. As we do this, we
	 * maintain a sorted list of remaining characters in {@link #sortedInput}.
	 * Rank must be recalculated for each iteration.
	 * 
	 * @param letter
	 * @return
	 */
	private int getLetterRankAmongRemaining(T letter) {
		return sortedInput.indexOf(letter);
	}

	/**
	 * Sort the input once at beginning
	 */
	private void sortInput() {
		// Timsort, best O(n), worst O(nlogn)
		Collections.<T> sort(sortedInput, new Comparator<T>() {
			public int compare(T a, T b) {
				return (a.compareTo(b));
			}

		});
	}

}