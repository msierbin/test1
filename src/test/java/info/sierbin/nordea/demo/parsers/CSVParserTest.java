package info.sierbin.nordea.demo.parsers;

import info.sierbin.nordea.demo.providers.StringInputProvider;
import info.sierbin.nordea.demo.providers.StringOutputProvider;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("string-test")
class CSVParserTest {

	@Autowired
	private StringInputProvider inputProvider;
	@Autowired
	private StringOutputProvider outputProvider;
	@Autowired
	private CSVParser csvParser;

	@Test
	public void rule1() throws IOException {
		// given
		inputProvider.setContent(" Mary had a little lamb .\n" +
			"\n" +
			"Peter called for the wolf , and Aesop came .\n" +
			"Cinderella likes shoes..");

		// when
		this.csvParser.parse(inputProvider.get(), outputProvider.get());

		// then
		final String out = outputProvider.getOut();
		Assertions.assertEquals(", Word 1, Word 2, Word 3, Word 4, Word 5, Word 6, Word 7, Word 8, Word 9, Word 10, Word 11, Word 12, Word 13, Word 14, Word 15, Word 16, Word 17, Word 18, Word 19, Word 20, Word 21, Word 22, Word 23, Word 24, Word 25, Word 26, Word 27, Word 28, Word 29, Word 30, Word 31, Word 32, Word 33, Word 34, Word 35, Word 36, Word 37, Word 38, Word 39, Word 40, Word 41, Word 42, Word 43, Word 44, Word 45, Word 46, Word 47, Word 48, Word 49\n" +
			"Sentence 1, Aesop, and, called, came, for, Peter, the, wolf\n" +
			"Sentence 2, Cinderella, likes, shoes\n", out);
	}
}
