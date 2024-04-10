package io.github.guiritter.line_terminator_normalizer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public final class NumericTest {
	
	@Test
	public final void cast() {
		assertEquals(1l, Math.min(1l, Integer.MAX_VALUE));
		assertEquals(Integer.MAX_VALUE, Math.min(281474976710656l, Integer.MAX_VALUE));
		assertEquals(0, (int) 281474976710656l);
	}
}
