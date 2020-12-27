package com.pozharsky.dmitri.parser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParagraphParser implements Handler {
    private static final String THREE_SPACE_DELIMITER = "   ";
    private String text;
    private SentenceParser parser;

    public ParagraphParser(String text, SentenceParser parser) {
        this.text = text;
        this.parser = parser;
    }

    @Override
    public List<String> handleRequest() {
        List<String> paragraphs = Stream.of(text.split(THREE_SPACE_DELIMITER))
                .collect(Collectors.toList());
        if (parser == null) {
            return paragraphs;
        } else {
            parser.setParagraphs(paragraphs);
            return parser.handleRequest();
        }
    }
}
