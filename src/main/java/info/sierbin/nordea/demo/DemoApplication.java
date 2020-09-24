package info.sierbin.nordea.demo;

import info.sierbin.nordea.demo.parsers.Parser;
import info.sierbin.nordea.demo.parsers.ParserStrategy;
import info.sierbin.nordea.demo.providers.InputProvider;
import info.sierbin.nordea.demo.providers.OutputProvider;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private InputProvider inputProvider;

	@Autowired
	private OutputProvider outputProvider;

	@Autowired
	private ParserStrategy parserStrategy;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws IOException {
		final DemoApplicationAttributes params = new DemoApplicationAttributes(args);
		final Parser parser = this.parserStrategy.find(params.getParserType());
		parser.parse(this.inputProvider.get(), this.outputProvider.get());
	}
}
