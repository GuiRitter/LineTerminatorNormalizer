package io.github.guiritter.line_terminator_normalizer;

import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.CARRIAGE_RETURN;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.LINE_BREAK;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.isLineTerminator;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.isNotLineTerminator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public final class IsLineTerminator {
	
	@Test
	public final void test() throws IOException {
		assertEquals(true, isLineTerminator(LINE_BREAK));
		assertEquals(true, isLineTerminator(CARRIAGE_RETURN));
		assertEquals(false, isLineTerminator("a".charAt(0)));
		assertEquals(false, isNotLineTerminator(LINE_BREAK));
		assertEquals(false, isNotLineTerminator(CARRIAGE_RETURN));
		assertEquals(true, isNotLineTerminator("a".charAt(0)));
	}
}
