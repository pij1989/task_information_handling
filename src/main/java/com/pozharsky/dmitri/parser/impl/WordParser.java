package com.pozharsky.dmitri.parser.impl;

import com.pozharsky.dmitri.composite.impl.Punctuation;
import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements Parser<Optional<TextComposite>, String> {
    private static final Logger logger = LogManager.getLogger(WordParser.class);
    private static final String WORD_AND_PUNCTUATION_PATTERN_GROUP = "([A-Z_a-z_А-Я_а-я_0-9]+)|([^\\s\\w_А-Яа-я])";
    private static final int FIRST_GROUP = 1;
    private static final int SECOND_GROUP = 2;
    private static final int INDEX = 0;
    private SymbolParser parser;

    public WordParser(SymbolParser parser) {
        this.parser = parser;
    }

    @Override
    public Optional<TextComposite> parse(String lexeme) {
        if (parser != null) {
            TextComposite textComposite = new TextComposite();
            Pattern pattern = Pattern.compile(WORD_AND_PUNCTUATION_PATTERN_GROUP);
            Matcher matcher = pattern.matcher(lexeme.trim());
            while (matcher.find()) {
                String word = matcher.group(FIRST_GROUP);
                String punctuation = matcher.group(SECOND_GROUP);
                if (word != null) {
                    logger.debug("Word: " + word);
                    TextComposite symbols = parser.parse(word);
                    textComposite.add(symbols);
                }
                if (punctuation != null) {
                    logger.debug("Punctuation: " + punctuation);
                    textComposite.add(new Punctuation(punctuation.charAt(INDEX)));
                }
            }
            return Optional.of(textComposite);
        } else {
            return Optional.empty();
        }
    }
}
