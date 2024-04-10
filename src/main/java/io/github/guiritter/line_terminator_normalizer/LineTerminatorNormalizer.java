package io.github.guiritter.line_terminator_normalizer;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import static java.lang.System.out;

public final class LineTerminatorNormalizer {

	public static final int LINE_BREAK = 10;
	public static final int CARRIAGE_RETURN = 13;

	public static final String DESIRED_FILE_EXTENSION = ".dart";

	public static final List<Integer> LINE_TERMINATOR_LIST = List.of(LINE_BREAK, CARRIAGE_RETURN);

	public static final String REFERENCE_FOLDER = "C:\\desenvolvimento\\Flutter\\project_ref\\src";

	public static final Map<String, String> REFERENCE_MAP = new HashMap<>();

	public static final String ROOT_FOLDER = "C:\\desenvolvimento\\Flutter\\project_merge\\src";
	
	public static final String getKeyWithoutPrefix(String prefix, String key) {
		return key.substring(prefix.length());
	}

	public static final String getLineTerminator(File file) throws IOException {
		boolean hasLN = false;
		boolean hasCR = false;

		var reader = newBufferedReader(file.toPath());

		int character;

		while ((character = reader.read()) > -1) {

			if (character == LINE_BREAK) {

				if (hasLN) {
					reader.close();
					return "\n";
				} else {
					hasLN = true;
				}
 			} else if (character == CARRIAGE_RETURN) {

				if (hasCR) {
					reader.close();
					return "\r";
				} else {
					hasCR = true;
				}
			}

			if (hasLN && hasCR) {
				reader.close();
				return "\r\n";
			}
		}

		reader.close();

		if (hasLN) {
			return "\n";
		}

		if (hasCR) {
			return "\r";
		}

		return "";
	}

	private static final String getReference(String rootFolder, String filePath) {
		return REFERENCE_MAP.get(getKeyWithoutPrefix(rootFolder, filePath));
	}

	public static final boolean isDesiredFileExtension(File file, String extension) {
		return file.getAbsolutePath().endsWith(extension);
	}

	public static final boolean isLineTerminator(int character) {
		return LINE_TERMINATOR_LIST.contains(character);
	}

	public static final boolean isNotLineTerminator(int character) {
		return !isLineTerminator(character);
	}

	public static final void normalize(String rootFolder, String fileExtension, String referenceFolder) throws IOException {
		processReference(referenceFolder, fileExtension);
		processFolder(rootFolder, fileExtension);
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

	public static final void processFolder(String rootFolder, String fileExtension) throws IOException {
		var fileProcessor = new Consumer<File>() {
			@Override
			public void accept(File fFile) {
				if (fFile.isDirectory())
				{
					out.print("d ");
				}
				else if (isDesiredFileExtension(fFile, fileExtension))
				{
					out.print("f ");
					try {

						var referenceLineTerminator = getReference(rootFolder, fFile.getAbsolutePath());

						if (referenceLineTerminator != null) {

							var currentLineTerminator = getLineTerminator(fFile);

							if ((currentLineTerminator != null) && (referenceLineTerminator.compareTo(currentLineTerminator) != 0)) {

								processFile(fFile.getAbsolutePath(), fFile.getAbsolutePath(), referenceLineTerminator);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
				{
					out.print("_ ");
				}

				out.println(fFile);
				out.flush();
			}
		};

		traverseFolder(rootFolder, fileProcessor);
	}

	public static final void processReference(String referenceFolder, String fileExtension) {
		var fileProcessor = new Consumer<File>() {
			@Override
			public void accept(File fFile) {
				if (fFile.isDirectory())
				{
					out.print("d ");
				}
				else if (isDesiredFileExtension(fFile, fileExtension))
				{
					out.print("f ");
					try {
						var lineTerminator = getLineTerminator(fFile);
						putReference(referenceFolder, fFile.getAbsolutePath(), lineTerminator);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else
				{
					out.print("_ ");
				}

				out.println(fFile);
				out.flush();
			}
		};

		traverseFolder(referenceFolder, fileProcessor);
	}

	private static final void putReference(String rootFolder, String filePath, String value) {
		REFERENCE_MAP.put(getKeyWithoutPrefix(rootFolder, filePath), value);
	}

	public static final void traverseFolder(String rootPath, Consumer<File> fileConsumer) {
		var file = new File(rootPath);

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
					nextList.add(fFile);
				}
				fileConsumer.accept(fFile);
			}

			nextList.addAll(toDoList);
			toDoList.clear();
			toDoList.addAll(nextList);
			nextList.clear();
		}
	}

	public static final void main(String args[]) throws IOException {
		normalize(ROOT_FOLDER, DESIRED_FILE_EXTENSION, REFERENCE_FOLDER);
	}
}
