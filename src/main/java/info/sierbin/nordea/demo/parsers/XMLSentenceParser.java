package info.sierbin.nordea.demo.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.Collator;
import java.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class XMLSentenceParser implements Parser {

    @Override
    public ParserType getType() {
        return ParserType.XML_SENTENCE_PARSER;
    }

    @Override
    public void parse(
        final InputStream inputStream,
        final OutputStream outputStream
    ) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        outputStream.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>".getBytes("UTF-8"));
        outputStream.write('\n');
        outputStream.write("<text>".getBytes(StandardCharsets.UTF_8));
        outputStream.write('\n');
        while(reader.ready()) {
            String line = reader.readLine();
            final String[] words = line.split("\\s");
            Arrays.sort(words, Collator.getInstance());
            if (words.length > 0 && words[0].length() > 0) {
                outputStream.write("\t<sentence>".getBytes(StandardCharsets.UTF_8));
                outputStream.write('\n');
                for (String word : words) {
                    outputStream.write("\t\t<word>".getBytes(StandardCharsets.UTF_8));
                    outputStream.write(word.getBytes(StandardCharsets.UTF_8));
                    outputStream.write("</word>".getBytes(StandardCharsets.UTF_8));
                    outputStream.write('\n');
                }
                outputStream.write("\t</sentence>".getBytes(StandardCharsets.UTF_8));
                outputStream.write('\n');
            }
        }
        outputStream.write("</text>".getBytes(StandardCharsets.UTF_8));
    }

}
