package io.github.guiritter.line_terminator_normalizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;

public class LineAmount {
	
	@Test
	public void lineAmount() throws IOException {
		var file = new File("C:\\desenvolvimento\\Java\\LineTerminatorNormalizer\\src\\main\\resources\\transaction_type_enum.dart");

		var lineList = Files.readAllLines(file.toPath());

		assertEquals(1, lineList.size());
	}
}
