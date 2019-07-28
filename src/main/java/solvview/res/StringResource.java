package solvview.res;

import solvview.core.util.FileReader;
import solvview.core.util.ResourceReader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

/**
 * String resource representation.
 *
 * Loading a resource will load it from a file outside the jar (assuming it exists) or from the
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
