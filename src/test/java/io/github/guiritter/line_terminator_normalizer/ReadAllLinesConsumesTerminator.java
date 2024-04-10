package io.github.guiritter.line_terminator_normalizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.Test;

public final class ReadAllLinesConsumesTerminator {
	
	@Test
	public void readAllLinesConsumesTerminator() throws IOException {
		var file = new File("C:\\desenvolvimento\\Java\\LineTerminatorNormalizer\\src\\main\\resources\\transaction_type_enum.dart");

		var lineList = Files.readAllLines(file.toPath());

		var line = String.join("", lineList);

		assertEquals("enum TransactionType { add, edit }", line);
	}
}
