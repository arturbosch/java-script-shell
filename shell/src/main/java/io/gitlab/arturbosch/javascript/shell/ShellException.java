package io.gitlab.arturbosch.javascript.shell;

/**
 * @author Artur Bosch
 */
public class ShellException extends IllegalStateException {

	public ShellException(String s) {
		super(s);
	}

	public ShellException(String message, Throwable cause) {
		super(message, cause);
	}
}
