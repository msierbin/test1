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
class XMLParserTest {

	@Autowired
	private StringInputProvider inputProvider;
	@Autowired
	private StringOutputProvider outputProvider;
	@Autowired
	private XMLParser xmlSentenceParser;

	@Test
	public void rule1() throws IOException {
		// given
		inputProvider.setContent(" Mary had a little lamb .\n" +
			"\n" +
			"Peter called for the wolf , and Aesop came .\n" +
			"Cinderella likes shoes..");

		// when
		this.xmlSentenceParser.parse(inputProvider.get(), outputProvider.get());

		// then
		final String out = outputProvider.getOut();
		Assertions.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
			"<text>\n" +
			"\t<sentence>\n" +
			"\t\t<word>Aesop</word>\n" +
			"\t\t<word>and</word>\n" +
			"\t\t<word>called</word>\n" +
			"\t\t<word>came</word>\n" +
			"\t\t<word>for</word>\n" +
			"\t\t<word>Peter</word>\n" +
			"\t\t<word>the</word>\n" +
			"\t\t<word>wolf</word>\n" +
			"\t</sentence>\n" +
			"\t<sentence>\n" +
			"\t\t<word>Cinderella</word>\n" +
			"\t\t<word>likes</word>\n" +
			"\t\t<word>shoes</word>\n" +
			"\t</sentence>\n" +
			"</text>", out);
	}
}
