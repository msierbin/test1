package info.sierbin.nordea.demo.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Parser {
    void parse(
        InputStream inputStream,
        OutputStream outputStream
    ) throws IOException;

    ParserType getType();
}
