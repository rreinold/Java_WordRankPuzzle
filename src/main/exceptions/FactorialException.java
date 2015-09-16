package main.exceptions;
/**
 * This is intended to handle any exceptions related to factorial
 * calculation failures.
 * 
 * @author RobReinold
 *
 */
public class FactorialException extends Exception{
	
	private static final long serialVersionUID = -1526480326653329373L;

	public FactorialException(String exception) {
		super(exception);
	}
}
