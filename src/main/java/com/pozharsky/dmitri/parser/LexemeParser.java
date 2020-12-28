package com.pozharsky.dmitri.parser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LexemeParser implements Handler {
    private static final String SPACE_DELIMITER = " ";
    private List<String> sentences;
    private WordParser parser;

    public LexemeParser(WordParser parser) {
        this.parser = parser;
    }

    @Override
    public List<String> handleRequest() {
        List<String> lexemes = sentences.stream()
                .flatMap(e -> Stream.of(e.trim().split(SPACE_DELIMITER)))
                .collect(Collectors.toList());
        if (parser == null) {
            return lexemes;
        } else {
            parser.setLexemes(lexemes);
            return parser.handleRequest();
        }
    }

    void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }
}
