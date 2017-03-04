package io.gitlab.arturbosch.javascript.shell;

import io.gitlab.arturbosch.javascript.compiler.CompilationException;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

/**
 * @author Artur Bosch
 */
public class JavaScriptShell {

	public static void main(String[] args) throws IOException {
		LineReader reader = LineReaderBuilder.builder()
				.terminal(TerminalBuilder.terminal())
				.build();
		String prompt = "java-script-engine> ";
		while (true) {
			String line;
			try {
				line = reader.readLine(prompt);
				new RunScript().execute(line);
			} catch (ShellException | CompilationException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (UserInterruptException e) {
				// Ignore
			} catch (EndOfFileException e) {
				return;
			}
		}
	}
}
