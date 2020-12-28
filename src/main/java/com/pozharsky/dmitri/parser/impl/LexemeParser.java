package com.pozharsky.dmitri.parser.impl;

import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LexemeParser implements Parser<TextComposite,List<String>> {
    private static final Logger logger = LogManager.getLogger(LexemeParser.class);
    private static final String SPACE_DELIMITER = "\\s";
    private WordParser parser;

    public LexemeParser(WordParser parser) {
        this.parser = parser;
    }

    @Override
    public TextComposite parse(List<String> sentences) {
        if (parser != null) {
            List<String> lexemes = sentences.stream()
                    .flatMap(e -> Stream.of(e.trim().split(SPACE_DELIMITER)))
                    .collect(Collectors.toList());
            logger.info("Lexemes: " + lexemes);
            return parser.parse(lexemes);
        } else {
            return new TextComposite();
        }
    }
}
