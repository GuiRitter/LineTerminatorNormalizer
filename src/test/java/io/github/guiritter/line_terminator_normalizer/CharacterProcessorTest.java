package io.github.guiritter.line_terminator_normalizer;

import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.CARRIAGE_RETURN;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.CARRIAGE_RETURN_ARRAY;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.COMBINATION_ARRAY;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.LINE_BREAK;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.LINE_BREAK_ARRAY;
import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.NO_ARRAY;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public final class CharacterProcessorTest {
	
	public static final String As = "A";
	public static final int Ai = As.codePointAt(0);
	public static final int[] Aa = new int[]{ Ai };
	public static final String Bs = "B";
	public static final int Bi = Bs.codePointAt(0);
	public static final int[] Ba = new int[]{ Bi };
	public static final int ni = LINE_BREAK;
	public static final int ri = CARRIAGE_RETURN;

	public static final List<Integer> outputList = new ArrayList<>(2);

	public static final int[] integerListToIntArray(List<Integer> input) {
		return Arrays.stream(input.toArray(new Integer[0])).mapToInt(Integer::intValue).toArray();
	}
	
	@Test
	public final void test() throws IOException {
		testCombination(LINE_BREAK_ARRAY);
		testCombination(CARRIAGE_RETURN_ARRAY);

		testSingle(ni, CARRIAGE_RETURN_ARRAY);
		testSingle(ni, COMBINATION_ARRAY);

		testSingle(ri, LINE_BREAK_ARRAY);
		testSingle(ri, COMBINATION_ARRAY);
	}

	public final void testCombination(int[] chosenLineTerminator) throws IOException {

		var characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// \r\n 0
		outputList.clear(); characterProcessor.writeChar(ri                  , outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ni                  , outputList); assertArrayEquals(NO_ARRAY            , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ri                  , outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ni                  , outputList); assertArrayEquals(NO_ARRAY            , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ri                  , outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ni                  , outputList); assertArrayEquals(NO_ARRAY            , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ri                  , outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ni                  , outputList); assertArrayEquals(NO_ARRAY            , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ri                  , outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ni                  , outputList); assertArrayEquals(NO_ARRAY            , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ri                  , outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ni                  , outputList); assertArrayEquals(NO_ARRAY            , integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// \r\n 1
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ri                  , outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ni                  , outputList); assertArrayEquals(NO_ARRAY            , integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// \r\n 2
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ri                  , outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ni                  , outputList); assertArrayEquals(NO_ARRAY            , integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// \r\n 3
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ri                  , outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ni                  , outputList); assertArrayEquals(NO_ARRAY            , integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// \r\n 4
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ri                  , outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(ni                  , outputList); assertArrayEquals(NO_ARRAY            , integerListToIntArray(outputList));
	}

	public final void testSingle(int originLineTerminator, int[] chosenLineTerminator) throws IOException {
		var characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 0
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 1
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 2
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 3
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 4
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 5
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 6
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 7
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Ai                  , outputList); assertArrayEquals(Aa                  , integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);

		// 8
		outputList.clear(); characterProcessor.writeChar(originLineTerminator, outputList); assertArrayEquals(chosenLineTerminator, integerListToIntArray(outputList));
		outputList.clear(); characterProcessor.writeChar(Bi                  , outputList); assertArrayEquals(Ba                  , integerListToIntArray(outputList));

		characterProcessor = new CharacterProcessor(chosenLineTerminator);
	}
}
