package solvview.res;

import solvview.core.util.FileReader;
import solvview.core.util.ResourceReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

/**
 * String resource representation.
 *
 * Each resource defaults to the contents of a file in the jar, but will be overridden
 * by a file of the same name in the working directory.
 */
public class StringResource {
	private final String name;

	public StringResource(String name) {
		this.name = name;
	}

	public String get() {
		try {
			return FileReader.readFile(Paths.get(System.getProperty("user.dir"), name), StandardCharsets.UTF_8);
		} catch (IOException e) {
			return ResourceReader.readResource(getClass().getResourceAsStream("/" + name));
		}
	}
}
