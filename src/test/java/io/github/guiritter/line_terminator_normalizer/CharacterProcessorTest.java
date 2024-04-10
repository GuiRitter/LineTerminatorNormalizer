package io.github.guiritter.line_terminator_normalizer;

import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.CARRIAGE_RETURN;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.LINE_BREAK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class CharacterProcessorTest {
	
	public static final String As = "A";
	public static final int Ai = As.codePointAt(0);
	public static final String Bs = "B";
	public static final int Bi = Bs.codePointAt(0);
	public static final int ni = LINE_BREAK;
	public static final String ns = new String(new int[]{ LINE_BREAK }, 0, 1);
	public static final int ri = CARRIAGE_RETURN;
	public static final String rs = new String(new int[]{ CARRIAGE_RETURN }, 0, 1);
	
	@Test
	public void test() throws IOException {
		testCombination(ns);
		testCombination(rs);

		testSingle(ni, rs);
		testSingle(ni, rs + ns);

		testSingle(ri, ns);
		testSingle(ri, rs + ns);
	}

	public void testCombination(String chosenLineTerminator) throws IOException {

		var characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// \r\n 0
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(ri));
		assertEquals(""                  , characterProcessor.getCharToWrite(ni));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(ri));
		assertEquals(""                  , characterProcessor.getCharToWrite(ni));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(ri));
		assertEquals(""                  , characterProcessor.getCharToWrite(ni));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(ri));
		assertEquals(""                  , characterProcessor.getCharToWrite(ni));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(ri));
		assertEquals(""                  , characterProcessor.getCharToWrite(ni));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(ri));
		assertEquals(""                  , characterProcessor.getCharToWrite(ni));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// \r\n 1
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(ri));
		assertEquals(""                  , characterProcessor.getCharToWrite(ni));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// \r\n 2
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(ri));
		assertEquals(""                  , characterProcessor.getCharToWrite(ni));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// \r\n 3
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(ri));
		assertEquals(""                  , characterProcessor.getCharToWrite(ni));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// \r\n 4
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(ri));
		assertEquals(""                  , characterProcessor.getCharToWrite(ni));
	}

	public void testSingle(int originLineTerminator, String chosenLineTerminator) throws IOException {
		var characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 0
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 1
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 2
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 3
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 4
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 5
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 6
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 7
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(As                  , characterProcessor.getCharToWrite(Ai                  ));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 8
		assertEquals(chosenLineTerminator, characterProcessor.getCharToWrite(originLineTerminator));
		assertEquals(Bs                  , characterProcessor.getCharToWrite(Bi                  ));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);
	}
}
