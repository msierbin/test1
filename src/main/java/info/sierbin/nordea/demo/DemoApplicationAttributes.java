package info.sierbin.nordea.demo;

import info.sierbin.nordea.demo.parsers.ParserType;

public class DemoApplicationAttributes {
    private final ParserType parserType;

    public DemoApplicationAttributes(String[] args) {
        ParserType parserType = ParserType.NOOP;
        for (String arg : args) {
            if ("-noop".equalsIgnoreCase(arg)) {
                parserType = ParserType.NOOP;
            } else if (
                "-SENTENCE_SPLITTER".equalsIgnoreCase(arg) ||
                "-ss".equalsIgnoreCase(arg)
            ) {
                parserType = ParserType.SENTENCE_SPLITTER;
            } else if (
                "-REMOVE_SPACES".equalsIgnoreCase(arg) ||
                "-rs".equalsIgnoreCase(arg)
            ) {
                parserType = ParserType.REMOVE_SPACES;
            } else if (
                "-XML_PARSER".equalsIgnoreCase(arg) ||
                    "-xml".equalsIgnoreCase(arg)
            ) {
                parserType = ParserType.XML_PARSER;
            } else if (
                "-CSV_PARSER".equalsIgnoreCase(arg) ||
                    "-csv".equalsIgnoreCase(arg)
            ) {
                parserType = ParserType.CSV_PARSER;
            } else if (
                "-XML_SENTENCE_PARSER".equalsIgnoreCase(arg) ||
                    "-xml_sentence".equalsIgnoreCase(arg)
            ) {
                parserType = ParserType.XML_SENTENCE_PARSER;
            } else if (
                "-CSV_SENTENCE_PARSER".equalsIgnoreCase(arg) ||
                    "-csv_sentence".equalsIgnoreCase(arg)
            ) {
                parserType = ParserType.CSV_SENTENCE_PARSER;
            }
        }

        this.parserType = parserType;
    }

    public ParserType getParserType() {
        return this.parserType;
    }
}
