package solvview.core.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {
	public static String readFile(Path path, Charset encoding) throws IOException {
		byte[] bytes = Files.readAllBytes(path);
		return new String(bytes, encoding);
	}
}
