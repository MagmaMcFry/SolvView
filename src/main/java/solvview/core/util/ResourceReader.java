package solvview.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class ResourceReader {
	public static String readResource(InputStream is) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			return br.lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			return null;
		}
	}
}
