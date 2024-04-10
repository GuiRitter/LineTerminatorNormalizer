package io.github.guiritter.line_terminator_normalizer;

import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.CARRIAGE_RETURN;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.LINE_BREAK;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.getLineTerminator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public final class GetLineTerminator {

	public static final String CARRIAGE_RETURN_STRING = new String(new int[]{ CARRIAGE_RETURN }, 0, 1);
	public static final String LINE_BREAK_STRING = new String(new int[]{ LINE_BREAK }, 0, 1);

	public static final String COMBINATION_STRING = CARRIAGE_RETURN_STRING + LINE_BREAK_STRING;

	public static final String ROOT_PATH = "C:\\desenvolvimento\\Java\\LineTerminatorNormalizer\\src\\main\\resources";

	public static final Path generateSinglePath(String rootPath, String lineTerminator, int index) 
	{
		lineTerminator = lineTerminator.replace("\r", "r").replace("\n", "n");

		return Paths.get(rootPath + "\\" + lineTerminator + " " + (index++) + ".txt");
	}

	public static final Path generateCombinationPath(String rootPath, int index) 
	{
		return Paths.get(rootPath + "\\r n " + (index++) + ".txt");
	}

	public final void testSingle(String lineTerminator) throws IOException {
		File file;

		for (int index = 0; index < 6; index++) {
			file = generateSinglePath(ROOT_PATH, lineTerminator, index++).toFile();
	
			assertEquals(lineTerminator, getLineTerminator(file));
		}
	}

	@Test
	public final void testBothSingle() throws IOException {
		testSingle(LINE_BREAK_STRING);
		testSingle(CARRIAGE_RETURN_STRING);
	}

	@Test
	public final void testCombination() throws IOException {
		
		File file;

		for (int index = 0; index < 3; index++) {
			file = generateCombinationPath(ROOT_PATH, index++).toFile();
	
			assertEquals(COMBINATION_STRING, getLineTerminator(file));
		}
	}
}
