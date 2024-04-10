package io.github.guiritter.line_terminator_normalizer;

import static java.nio.file.Files.newBufferedReader;
import static java.nio.file.Files.newBufferedWriter;
import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

import static java.lang.System.out;

public final class Emoji {
	
	public final void readBoth() throws IOException {
		
		System.out.println("pass\n");
		
		var file = new File("C:\\desenvolvimento\\Java\\LineTerminatorNormalizer\\src\\main\\resources\\emoji pass.dart");

		var reader = newBufferedReader(file.toPath());

		int character = -1;

		while ((character = reader.read()) > -1) {
			out.println(character);
		}

		reader.close();

		System.out.println();

		//

		System.out.println("fail\n");
		
		file = new File("C:\\desenvolvimento\\Java\\LineTerminatorNormalizer\\src\\main\\resources\\emoji fail.dart");

		reader = newBufferedReader(file.toPath());

		while ((character = reader.read()) > -1) {
			out.println(character);
		}

		reader.close();

		System.out.println();
	}

	public final void write() throws IOException {
		
		var writer = newBufferedWriter(Paths.get("C:\\desenvolvimento\\Java\\LineTerminatorNormalizer\\src\\main\\resources\\out.dart"), CREATE, TRUNCATE_EXISTING);
		
		writer.write(47);
		writer.write(47);
		writer.write(32);
		writer.write(55357);
		writer.write(56485);
		writer.write(32);
		writer.write(114);

		writer.flush();
		writer.close();
	}

	@Test
	public final void test() throws IOException {
		// readBoth();
		write();
	}
}
