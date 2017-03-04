package io.gitlab.arturbosch.javascript.compiler;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Load class from byte[] which is compiled in memory.
 *
 * @author michael (original - https://github.com/michaelliao/compiler)
 */
class MemoryClassLoader extends URLClassLoader {

	// class name to class bytes:
	private Map<String, byte[]> classBytes = new HashMap<>();

	MemoryClassLoader(Map<String, byte[]> classBytes) {
		super(new URL[0], MemoryClassLoader.class.getClassLoader());
		this.classBytes.putAll(classBytes);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] buf = classBytes.get(name);
		if (buf == null) {
			return super.findClass(name);
		}
		classBytes.remove(name);
		return defineClass(name, buf, 0, buf.length);
	}

}