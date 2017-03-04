package io.gitlab.arturbosch.javascript.shell;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author Artur Bosch
 */
public class RunScript {

	private final static String id = "run";
	private final RunCompile runCompile = new RunCompile();

	public void execute(String line) {
		String[] input = validateInput(line);
		String[] args = Arrays.copyOfRange(input, 1, input.length);
		Path scriptPath = Paths.get(input[0]);
		Class<?> aClass = runCompile.compile(scriptPath);
		new RunMain().run(aClass, args);
	}

	private String[] validateInput(String line) {
		if (!line.startsWith(id)) throw new ShellException("No matching command found! Try 'run'!");
		String input = line.substring(id.length());
		String[] args = Arrays.stream(input.split(" "))
				.filter(s -> !s.isEmpty()).toArray(String[]::new);
		if (args.length == 0) throw new ShellException("Specify a path to a java source file!");
		return args;
	}

}
