package io.github.guiritter.line_terminator_normalizer;

import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.LINE_BREAK;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.processFile;
import static java.nio.file.Files.newBufferedReader;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public final class ResourceTest {
	
	@Test
	public void resourceTest() throws IOException {
		var outputPath = "C:\\desenvolvimento\\Java\\LineTerminatorNormalizer\\src\\main\\resources\\transaction_type_enum_out.dart";

		processFile(
			"C:\\desenvolvimento\\Java\\LineTerminatorNormalizer\\src\\main\\resources\\transaction_type_enum.dart",
			outputPath,
			new String(new int[]{ LINE_BREAK }, 0, 1)
		);

		var file = new File(outputPath);

		var reader = newBufferedReader(file.toPath());

		int antePenUltimateCharacter = -1;
		int penUltimateCharacter = -1;
		int ultimateCharacter = -1;

		while ((ultimateCharacter = reader.read()) > -1) {
			antePenUltimateCharacter = penUltimateCharacter;
			penUltimateCharacter = ultimateCharacter;
		}

		reader.close();

		assertEquals("}".codePointAt(0), antePenUltimateCharacter);
		assertEquals(LINE_BREAK, penUltimateCharacter);
		assertEquals(-1, ultimateCharacter);
	}
}
