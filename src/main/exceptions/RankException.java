package main.exceptions;
/**
 * RankExceptions are created when a ranking class is unable
 * to rank a given input.
 * 
 * @author RobReinold
 *
 */
public class RankException extends Exception {
	
	private static final long serialVersionUID = 2989598345627699061L;

	public RankException(String exception) {
		super(exception);
	}
}
