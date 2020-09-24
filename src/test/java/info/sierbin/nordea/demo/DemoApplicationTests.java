package info.sierbin.nordea.demo;

import info.sierbin.nordea.demo.parsers.NoOpParser;
import info.sierbin.nordea.demo.providers.StringInputProvider;
import info.sierbin.nordea.demo.providers.StringOutputProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("string-test")
class DemoApplicationTests {

	@Autowired
	private StringInputProvider inputProvider;
	@Autowired
	private StringOutputProvider outputProvider;
	@Autowired
	private NoOpParser noopParser;

	@Test
	void contextLoads() {
	}

	@Test
	public void whenNoOpParser_ExpectTheSameTextReturned() throws IOException {
		// given
		inputProvider.setContent("Marek 1 test");

		// when
		this.noopParser.parse(inputProvider.get(), outputProvider.get());

		// then
		final String out = outputProvider.getOut();
		Assertions.assertEquals("Marek 1 test", out);
	}
}
