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
            }
        }

        this.parserType = parserType;
    }

    public ParserType getParserType() {
        return this.parserType;
    }
}
