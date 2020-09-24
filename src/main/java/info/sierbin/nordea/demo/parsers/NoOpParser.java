package info.sierbin.nordea.demo.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Service;

@Service
public class NoOpParser implements Parser {

    @Override
    public ParserType getType() {
        return ParserType.NOOP;
    }

    @Override
    public void parse(
        final InputStream inputStream,
        final OutputStream outputStream
    ) throws IOException {
        outputStream.write(inputStream.readAllBytes());
    }

}
