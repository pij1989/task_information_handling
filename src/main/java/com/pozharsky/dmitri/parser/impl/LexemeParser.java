package com.pozharsky.dmitri.parser.impl;

import com.pozharsky.dmitri.composite.TextType;
import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.stream.Stream;

public class LexemeParser implements Parser<Optional<TextComposite>, String> {
    private static final Logger logger = LogManager.getLogger(LexemeParser.class);
    private static final String SPACE_DELIMITER = "\\s";
    private WordParser parser;

    public LexemeParser(WordParser parser) {
        this.parser = parser;
    }

    @Override
    public Optional<TextComposite> parse(String sentence) {
        if (parser != null) {
            TextComposite textComposite = new TextComposite(TextType.SENTENCE);
            Stream.of(sentence.trim().split(SPACE_DELIMITER))
                    .forEach(e -> {
                        logger.debug("Lexeme: " + e);
                        Optional<TextComposite> optionalLexeme = parser.parse(e);
                        TextComposite lexeme = optionalLexeme.orElseThrow();
                        textComposite.add(lexeme);
                    });
            return Optional.of(textComposite);
        } else {
            return Optional.empty();
        }
    }
}
