package info.sierbin.nordea.demo.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Service;

@Service
public class SplitSentencePerLineParser implements Parser {

    @Override
    public ParserType getType() {
        return ParserType.SENTENCE_SPLITTER;
    }

    @Override
    public void parse(
        final InputStream inputStream,
        final OutputStream outputStream
    ) {
        try {
            boolean previousNewLine = false;

            int data;
            while((data = inputStream.read()) != -1) {
                if (data == '.' || data == '!' || data == '?') {
                    outputStream.write('\n');
                    previousNewLine = true;
                } else if (data == ' ' && previousNewLine) {
                    // skip space
                    previousNewLine = false;
                } else if (data != '\n') {
                    outputStream.write(data);
                    previousNewLine = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
