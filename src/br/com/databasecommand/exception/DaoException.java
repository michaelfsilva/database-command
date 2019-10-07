package br.com.databasecommand.exception;

/**
 * An runtime exception will be thrown if something goes wrong
 */
public class DaoException extends RuntimeException {
	public DaoException() {
		super("An error ocurred during DAO execution.");
	}
}