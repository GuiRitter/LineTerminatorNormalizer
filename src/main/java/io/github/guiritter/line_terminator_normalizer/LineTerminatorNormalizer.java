package io.github.guiritter.line_terminator_normalizer;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

public final class LineTerminatorNormalizer {

	public static final int LINE_BREAK = 10;
	public static final int CARRIAGE_RETURN = 13;

	public static final String CHOSEN_LINE_TERMINATOR = new String(new int[] {LINE_BREAK}, 0, 1);

	public static final String DESIRED_FILE_EXTENSION = ".dart";

	public static final List<Integer> LINE_TERMINATOR_LIST = List.of(LINE_BREAK, CARRIAGE_RETURN);

	public static final String ROOT_FOLDER = "C:\\desenvolvimento\\Flutter\\project_merge\\src";

	public static final boolean isDesiredFileExtension(File file, String extension) {
		return file.getAbsolutePath().endsWith(extension);
	}

	public static final boolean isLineTerminator(int character) {
		return LINE_TERMINATOR_LIST.contains(character);
	}

	public static final boolean isNotLineTerminator(int character) {
		return !isLineTerminator(character);
	}

	public static final void processFile(String inputPath, String outputPath, String chosenLineTerminator) throws IOException {
		var file = new File(inputPath);

		var reader = newBufferedReader(file.toPath());

		var builder = new StringBuilder((int) file.length());

		int character;

		var characterProcessor = new CharacterProcessor(chosenLineTerminator);

		while ((character = reader.read()) > -1) {
			builder.append(characterProcessor.getCharToWrite(character));
		}

		reader.close();

		var codePointStream = builder.codePoints();

		var writer = newBufferedWriter(Paths.get(outputPath), CREATE, TRUNCATE_EXISTING);

		codePointStream.forEach(codePoint -> {
			try {
				writer.write(codePoint);
			} catch (IOException e) {}
		});

		writer.flush();
		writer.close();
	}

	public static final void processFolder(String rootFolder, String fileExtension, String chosenLineTerminator) throws IOException {
		var file = new File(rootFolder);

		List<File> toDoList = new LinkedList<File>();
		List<File> nextList = new LinkedList<File>();

		File fileArray[] = null;

		toDoList.add(file);

		while (!toDoList.isEmpty()) {

			file = toDoList.remove(0);

			fileArray = file.listFiles();

			for (File fFile : fileArray) {

				if (fFile.isDirectory())
				{
					out.print("d ");
					nextList.add(fFile);
				}
				else if (isDesiredFileExtension(fFile, fileExtension))
				{
					out.print("f ");
					processFile(fFile.getAbsolutePath(), fFile.getAbsolutePath(), chosenLineTerminator);
				}
				else
				{
					out.print("_ ");
				}

				out.println(fFile);
				out.flush();
			}

			nextList.addAll(toDoList);
			toDoList.clear();
			toDoList.addAll(nextList);
			nextList.clear();
		}
	}

	public static final void main(String args[]) throws IOException {
		processFolder(ROOT_FOLDER, DESIRED_FILE_EXTENSION, CHOSEN_LINE_TERMINATOR);
	}
}
