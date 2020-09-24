package info.sierbin.nordea.demo.parsers;

import info.sierbin.nordea.demo.streams.StreamPiper;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Service;

@Service
public class XMLParser implements Parser {

    private final SplitSentencePerLineParser splitParser;
    private final RemoveObsoleteCharsParser removeObsoleteCharsParser;
    private final XMLSentenceParser xmlSentenceParser;
    private  final StreamPiper streamPiper;

    public XMLParser(
        final SplitSentencePerLineParser splitParser,
        final RemoveObsoleteCharsParser removeObsoleteCharsParser,
        final XMLSentenceParser xmlSentenceParser,
        final StreamPiper streamPiper
    ) {
        this.splitParser = splitParser;
        this.removeObsoleteCharsParser = removeObsoleteCharsParser;
        this.xmlSentenceParser = xmlSentenceParser;
        this.streamPiper = streamPiper;
    }

    @Override
    public ParserType getType() {
        return ParserType.XML_PARSER;
    }

    @Override
    public void parse(
        final InputStream inputStream,
        final OutputStream outputStream
    ) throws IOException {
        InputStream stream = this.streamPiper.pipe(inputStream, splitParser::parse);
        stream = this.streamPiper.pipe(stream, removeObsoleteCharsParser::parse);
        stream = this.streamPiper.pipe(stream, xmlSentenceParser::parse);
        stream.transferTo(outputStream);
        outputStream.close();
    }

}
