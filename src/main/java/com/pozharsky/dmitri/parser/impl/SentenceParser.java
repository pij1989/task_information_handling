package com.pozharsky.dmitri.parser.impl;

import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements Parser<Optional<TextComposite>, String> {
    private static final Logger logger = LogManager.getLogger(SentenceParser.class);
    private static final String SENTENCE_PATTERN = "[A-Z][^.!?]*((\\.|!|\\?)(?! |\\n|\\r|\\r\\n)[^.!?]*)*(\\.|!|\\?)";
    private LexemeParser parser;

    public SentenceParser(LexemeParser parser) {
        this.parser = parser;
    }

    @Override
    public Optional<TextComposite> parse(String paragraph) {
        if (parser != null) {
            TextComposite textComposite = new TextComposite();
            Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
            Matcher matcher = pattern.matcher(paragraph);
            while (matcher.find()) {
                String sentence = matcher.group();
                logger.debug("Sentence: " + sentence);
                Optional<TextComposite> optionalSentence = parser.parse(sentence);
                TextComposite parseSentence = optionalSentence.orElseThrow();
                textComposite.add(parseSentence);
            }
            return Optional.of(textComposite);
        } else {
            return Optional.empty();
        }
    }
}
