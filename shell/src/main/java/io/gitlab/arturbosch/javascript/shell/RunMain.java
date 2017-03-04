package io.gitlab.arturbosch.javascript.shell;

import java.lang.reflect.Method;

/**
 * @author Artur Bosch
 */
public final class RunMain {

	public void run(Class<?> aClass, String[] args) {
		try {
			Method main = aClass.getMethod("main", String[].class);
			Object o = aClass.newInstance();
			main.invoke(o, (Object) args);
		} catch (Throwable throwable) {
			throw new ShellException("Could not execute 'main' method of " + aClass.getSimpleName(), throwable);
		}
	}

}
