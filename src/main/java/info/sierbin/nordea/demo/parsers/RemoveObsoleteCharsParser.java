package info.sierbin.nordea.demo.parsers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Service;

@Service
public class RemoveObsoleteCharsParser implements Parser {

    @Override
    public ParserType getType() {
        return ParserType.REMOVE_SPACES;
    }

    @Override
    public void parse(
        final InputStream inputStream,
        final OutputStream outputStream
    ) throws IOException {
        int previousData = -1;
        boolean wasPreviousSpace = false;

        int data;
        while((data = inputStream.read()) != -1) {
            if ((data == ' ' || data == '\t')) {
                if (wasPreviousSpace || previousData == '\n') {
                    // do not propagate duplicated spaces / tabs
                } else {
                    outputStream.write(' ');
                }
                wasPreviousSpace = true;
            } else if (data == ',' || data == ';' || data == ':' || data == '\'' || data == '"' || data == '-') {
                if (wasPreviousSpace) {
                    // do not propagate duplicated spaces / tabs
                } else {
                    // do not propagate commas, delimiters, quotes
                    outputStream.write(' ');
                }
                wasPreviousSpace = true;
            } else {
                outputStream.write(data);
                wasPreviousSpace = false;
            }

            previousData = data;
        }
    }

}
