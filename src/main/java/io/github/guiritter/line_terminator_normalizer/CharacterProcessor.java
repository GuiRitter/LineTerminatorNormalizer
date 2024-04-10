package io.github.guiritter.line_terminator_normalizer;

import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.isNotLineTerminator;
import java.util.Arrays;
import java.util.List;

public final class CharacterProcessor {
	
	private int firstLineTerminator = -1;

	private int lastCharRead = -1;

	public final int[] CHOSEN_LINE_TERMINATOR;

	public static final void addFromArrayIntoList(int[] input, List<Integer> output) {
		for (int fItem : input) {
			output.add(fItem);
		}
	}

	public CharacterProcessor(int[] chosenLineTerminator) {
		CHOSEN_LINE_TERMINATOR = Arrays.copyOf(chosenLineTerminator, chosenLineTerminator.length);
	}

	public final void writeChar(int character, List<Integer> outputList) {
		if (isNotLineTerminator(character)) {
			lastCharRead = character;
			outputList.add(character);
		} else {
			initializeFirstLineTerminator(character);

			if (isNotLineTerminator(lastCharRead))
			{
				lastCharRead = character;
				addFromArrayIntoList(CHOSEN_LINE_TERMINATOR, outputList);
			}
			else if (character == lastCharRead)
			{
				addFromArrayIntoList(CHOSEN_LINE_TERMINATOR, outputList);
			}
			else if (character == firstLineTerminator)
			{
				lastCharRead = character;
				addFromArrayIntoList(CHOSEN_LINE_TERMINATOR, outputList);
			}
			else
			{
				lastCharRead = character;
			}
		}
	}

	public final void initializeFirstLineTerminator(int character) {
		if (firstLineTerminator == -1) {
			firstLineTerminator = character;
		}
	}
}
