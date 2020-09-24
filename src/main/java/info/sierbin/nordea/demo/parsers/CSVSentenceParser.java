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
public class CSVSentenceParser implements Parser {

    @Override
    public ParserType getType() {
        return ParserType.CSV_SENTENCE_PARSER;
    }

    @Override
    public void parse(
        final InputStream inputStream,
        final OutputStream outputStream
    ) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        for (int i = 1; i < 50; i++) {
            outputStream.write((", Word " + i).getBytes(StandardCharsets.UTF_8));
        }
        int sentenceIdx = 1;
        outputStream.write('\n');
        while(reader.ready()) {
            String line = reader.readLine();
            final String[] words = line.split("\\s");
            Arrays.sort(words, Collator.getInstance());
            if (words.length > 0 && words[0].length() > 0) {
                outputStream.write("Sentence ".getBytes(StandardCharsets.UTF_8));
                outputStream.write(("" + sentenceIdx++).getBytes(StandardCharsets.UTF_8));
                for (String word : words) {
                    outputStream.write(", ".getBytes(StandardCharsets.UTF_8));
                    outputStream.write(word.getBytes(StandardCharsets.UTF_8));
                }
                outputStream.write('\n');
            }
        }
    }

}
