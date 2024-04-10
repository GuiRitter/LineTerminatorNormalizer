package io.github.guiritter.line_terminator_normalizer;

import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.CARRIAGE_RETURN;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.LINE_BREAK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class StringConversion {
	
	@Test
	public void test() throws IOException {
		assertEquals("\n", new String(new int[]{ LINE_BREAK }, 0, 1));
		assertEquals("\r", new String(new int[]{ CARRIAGE_RETURN }, 0, 1));
		assertEquals("\r\n", new String(new int[]{ CARRIAGE_RETURN, LINE_BREAK }, 0, 2));
	}
}
