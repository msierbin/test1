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
class RemoveSpacesTest {

	@Autowired
	private StringInputProvider inputProvider;
	@Autowired
	private StringOutputProvider outputProvider;
	@Autowired
	private RemoveObsoleteCharsParser removeObsoleteCharsParser;

	@Test
	public void whenRemoveSpacesParser_ExpectObsoleteCharsRemoved() throws IOException {
		// given
		inputProvider.setContent("What\the  shouted was shocking:  停在那儿, 你这肮脏的掠夺者! I couldn't understand a word,perhaps because Chinese \n" +
			" isn't my mother tongue. I was just standing there watching Mr. Young marching around - he \n" +
			"was    furious.   Why was he directing  his  anger at me? Little did I know \tabout \tthat.\n" +
			"\n" +
			"Nordea Markets is the leading international capital markets operator and investment banking partner in the Nordic and Baltic Sea regions.\n" +
			"We are located next door to you , connecting you to the global markets. \n" +
			"\n" +
			"We combine local expertise with global strength to provide you with a complete portfolio of financial services and solutions. We have \n" +
			"one of the strongest, most diversified product ranges in the Nordics and offer outstanding liquidity in local currencies.\n" +
			"\n" +
			"But more significantly, we offer you access to an unequalled team of experts ,in all facets of capital markets, dedicated to serving \n" +
			"you in the best possible manner.\n" +
			"At Nordea Markets we have a rare combination of local expertise and global strength which gives you, our customer, the opportunity to use us for a wide variety of financial services and solutions.\n" +
			"In fact - in all of the Nordics, you’d have a hard time finding a product range as strong and diversified as ours (and we can give you excellent liquidity in local currencies too). But most importantly, we have a huge team of outstanding specialists ready to serve you, no matter what your financial challenge might be.\n");

		// when
		this.removeObsoleteCharsParser.parse(inputProvider.get(), outputProvider.get());

		// then
		final String out = outputProvider.getOut();
		Assertions.assertEquals("What he shouted was shocking: 停在那儿 你这肮脏的掠夺者! I couldn t understand a word perhaps because Chinese \n" +
			" isn t my mother tongue. I was just standing there watching Mr. Young marching around he \n" +
			"was furious. Why was he directing his anger at me? Little did I know about that.\n" +
			"\n" +
			"Nordea Markets is the leading international capital markets operator and investment banking partner in the Nordic and Baltic Sea regions.\n" +
			"We are located next door to you connecting you to the global markets. \n" +
			"\n" +
			"We combine local expertise with global strength to provide you with a complete portfolio of financial services and solutions. We have \n" +
			"one of the strongest most diversified product ranges in the Nordics and offer outstanding liquidity in local currencies.\n" +
			"\n" +
			"But more significantly we offer you access to an unequalled team of experts in all facets of capital markets dedicated to serving \n" +
			"you in the best possible manner.\n" +
			"At Nordea Markets we have a rare combination of local expertise and global strength which gives you our customer the opportunity to use us for a wide variety of financial services and solutions.\n" +
			"In fact in all of the Nordics you’d have a hard time finding a product range as strong and diversified as ours (and we can give you excellent liquidity in local currencies too). But most importantly we have a huge team of outstanding specialists ready to serve you no matter what your financial challenge might be.\n", out);
	}
}
