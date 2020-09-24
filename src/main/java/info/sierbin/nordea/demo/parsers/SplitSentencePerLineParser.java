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
    ) throws IOException {
        int data;
        while((data = inputStream.read()) != -1) {
            if (data != '\n') {
                outputStream.write(data);
            }
            if (data == '.' || data == '!' || data == '?') {
                outputStream.write('\n');
            }
        }
    }

}
