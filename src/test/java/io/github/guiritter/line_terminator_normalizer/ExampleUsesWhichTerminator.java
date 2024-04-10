package io.github.guiritter.line_terminator_normalizer;

import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.CARRIAGE_RETURN;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.LINE_BREAK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;

public class ExampleUsesWhichTerminator {
	
	@Test
	public void exampleUsesWhichTerminator() throws IOException {
		var file = new File("C:\\desenvolvimento\\Java\\LineTerminatorNormalizer\\src\\main\\resources\\transaction_type_enum.dart");

		var reader = Files.newBufferedReader(file.toPath());

		int preAntePenUltimateCharacter = -1;
		int antePenUltimateCharacter = -1;
		int penUltimateCharacter = -1;
		int ultimateCharacter = -1;

		while ((ultimateCharacter = reader.read()) > -1) {
			preAntePenUltimateCharacter = antePenUltimateCharacter;
			antePenUltimateCharacter = penUltimateCharacter;
			penUltimateCharacter = ultimateCharacter;
		}

		reader.close();

		assertEquals("}".codePointAt(0), preAntePenUltimateCharacter);
		assertEquals(CARRIAGE_RETURN, antePenUltimateCharacter);
		assertEquals(LINE_BREAK, penUltimateCharacter);
		assertEquals(-1, ultimateCharacter);
	}
}
