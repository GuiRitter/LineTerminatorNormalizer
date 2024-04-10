package io.github.guiritter.line_terminator_normalizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.getKeyWithoutPrefix;

public final class GetKeyWithoutPrefix {

	@Test
	public final void test() {
		assertEquals(
			"\\src\\enum\\transaction_type_enum.dart",
			getKeyWithoutPrefix(
				"C:\\desenvolvimento\\Flutter\\project_merge",
				"C:\\desenvolvimento\\Flutter\\project_merge\\src\\enum\\transaction_type_enum.dart"
			)
		);
	}
}
