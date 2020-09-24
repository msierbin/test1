package info.sierbin.nordea.demo.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.text.Collator;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XMLParser implements Parser {

    @Autowired
    SplitSentencePerLineParser splitParser;
    @Autowired
    RemoveObsoleteCharsParser removeObsoleteCharsParser;
    @Autowired
    XMLSentenceParser xmlSentenceParser;

    @Override
    public ParserType getType() {
        return ParserType.COMBINED_XML_PARSER;
    }

    @Override
    public void parse(
        final InputStream inputStream,
        final OutputStream outputStream
    ) throws IOException {
        final PipedOutputStream pipedOutputStream1 = new PipedOutputStream();
        final PipedInputStream pipedInputStream1 = new PipedInputStream();
        pipedInputStream1.connect(pipedOutputStream1);
        new Thread(() -> {
            try {
                splitParser.parse(inputStream, pipedOutputStream1);
                pipedOutputStream1.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        final PipedOutputStream pipedOutputStream2 = new PipedOutputStream();
        final PipedInputStream pipedInputStream2 = new PipedInputStream();
        pipedInputStream2.connect(pipedOutputStream2);
        new Thread(() -> {
            try {
                removeObsoleteCharsParser.parse(pipedInputStream1, pipedOutputStream2);
                pipedOutputStream2.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        xmlSentenceParser.parse(pipedInputStream2, outputStream);
    }

}
