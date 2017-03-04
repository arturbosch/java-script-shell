package io.gitlab.arturbosch.javascript.shell;

import io.gitlab.arturbosch.javascript.compiler.JavaStringCompiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Artur Bosch
 */
public class RunCompile {

	private static final JavaStringCompiler compiler = new JavaStringCompiler();

	public Class<?> compile(Path scriptPath) {
		HashMap<String, String> map = new HashMap<>();
		String fileName = readFileName(scriptPath);
		map.put(fileName, readScript(scriptPath));
		try {
			Map<String, byte[]> compile = compiler.compile(map);
			String className = fileName.substring(0, fileName.indexOf("."));
			return loadClass(className, compile);
		} catch (IOException e) {
			throw new IllegalStateException("Error trying to compile path " + scriptPath);
		}
	}

	private Class<?> loadClass(String className, Map<String, byte[]> classData) {
		try {
			return compiler.loadClass(className, classData);
		} catch (ClassNotFoundException | IOException e) {
			throw new IllegalStateException("Error trying to load class " + className);
		}
	}

	private String readFileName(Path path) {
		return path.getFileName().toString();
	}

	private String readScript(Path path) {
		try {
			return new String(Files.readAllBytes(path));
		} catch (IOException e) {
			throw new IllegalStateException("Error trying to read file " + path);
		}
	}
}
