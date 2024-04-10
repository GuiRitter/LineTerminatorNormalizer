package io.github.guiritter.line_terminator_normalizer;

import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.CARRIAGE_RETURN;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.LINE_BREAK;
import static java.nio.file.Files.newBufferedWriter;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public final class GetLineTerminatorScenarioGenerator {

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

	public void generateSingle(String lineTerminator) throws IOException {
		int index = 0;

		var writer = newBufferedWriter(generateSinglePath(ROOT_PATH, lineTerminator, index++), CREATE, TRUNCATE_EXISTING);

		writer.write(lineTerminator);
		writer.write(lineTerminator);
		writer.write("A");

		writer.close();

		//

		writer = newBufferedWriter(generateSinglePath(ROOT_PATH, lineTerminator, index++), CREATE, TRUNCATE_EXISTING);

		writer.write(lineTerminator);
		writer.write("A");
		writer.write(lineTerminator);
		writer.write("A");
		
		writer.close();

		//

		writer = newBufferedWriter(generateSinglePath(ROOT_PATH, lineTerminator, index++), CREATE, TRUNCATE_EXISTING);

		writer.write(lineTerminator);
		writer.write("A");
		writer.write("A");
		writer.write(lineTerminator);
		writer.write("A");

		writer.close();

		//

		writer = newBufferedWriter(generateSinglePath(ROOT_PATH, lineTerminator, index++), CREATE, TRUNCATE_EXISTING);

		writer.write("A");
		writer.write(lineTerminator);
		writer.write(lineTerminator);
		writer.write("A");

		writer.close();

		//

		writer = newBufferedWriter(generateSinglePath(ROOT_PATH, lineTerminator, index++), CREATE, TRUNCATE_EXISTING);

		writer.write("A");
		writer.write(lineTerminator);
		writer.write("A");
		writer.write(lineTerminator);
		writer.write("A");
		
		writer.close();

		//

		writer = newBufferedWriter(generateSinglePath(ROOT_PATH, lineTerminator, index++), CREATE, TRUNCATE_EXISTING);
		
		writer.write("A");
		writer.write(lineTerminator);
		writer.write("A");
		writer.write("A");
		writer.write(lineTerminator);
		writer.write("A");

		writer.close();
	}

	// @Test
	public void generate() throws IOException {
		generateSingle(LINE_BREAK_STRING);
		generateSingle(CARRIAGE_RETURN_STRING);
	}

	@Test
	public void generateCombination() throws IOException {
		int index = 0;

		var writer = newBufferedWriter(generateCombinationPath(ROOT_PATH, index++), CREATE, TRUNCATE_EXISTING);

		writer.write(COMBINATION_STRING);
		writer.write("A");

		writer.close();

		//

		writer = newBufferedWriter(generateCombinationPath(ROOT_PATH, index++), CREATE, TRUNCATE_EXISTING);

		writer.write("A");
		writer.write(COMBINATION_STRING);
		writer.write("A");

		writer.close();

		//

		writer = newBufferedWriter(generateCombinationPath(ROOT_PATH, index++), CREATE, TRUNCATE_EXISTING);

		writer.write("A");
		writer.write(COMBINATION_STRING);
		writer.write(COMBINATION_STRING);
		writer.write("A");

		writer.close();
	}
}
