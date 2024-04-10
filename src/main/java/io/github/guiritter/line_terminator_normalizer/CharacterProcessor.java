package io.github.guiritter.line_terminator_normalizer;

import static io.github.guiritter.line_terminator_normalizer.LineTerminatorNormalizer.isNotLineTerminator;

public final class CharacterProcessor {
	
	private int firstLineTerminator = -1;

	private int lastCharRead = -1;

	public final String CHOSEN_LINE_TERMINATOR;

	public CharacterProcessor(String chosenLineTerminator) {
		CHOSEN_LINE_TERMINATOR = chosenLineTerminator;
	}

	public final String getCharToWrite(int character) {
		if (isNotLineTerminator(character)) {
			lastCharRead = character;
			return new String(new int[]{ character }, 0, 1);
		} else {
			initializeFirstLineTerminator(character);

			if (isNotLineTerminator(lastCharRead))
			{
				lastCharRead = character;
				return CHOSEN_LINE_TERMINATOR;
			}
			else if (character == lastCharRead)
			{
				return CHOSEN_LINE_TERMINATOR;
			}
			else if (character == firstLineTerminator)
			{
				lastCharRead = character;
				return CHOSEN_LINE_TERMINATOR;
			}
			else
			{
				lastCharRead = character;
				return "";
			}
		}
	}

	public final void initializeFirstLineTerminator(int character) {
		if (firstLineTerminator == -1) {
			firstLineTerminator = character;
		}
	}
}
