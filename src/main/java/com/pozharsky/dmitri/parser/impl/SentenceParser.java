package com.pozharsky.dmitri.parser.impl;

import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements Parser<TextComposite, List<String>> {
    private static final Logger logger = LogManager.getLogger(SentenceParser.class);
    private static final String SENTENCE_PATTERN = "[A-Z][^.!?]*((\\.|!|\\?)(?! |\\n|\\r|\\r\\n)[^.!?]*)*(\\.|!|\\?)";
    private LexemeParser parser;

    public SentenceParser(LexemeParser parser) {
        this.parser = parser;
    }

    @Override
    public TextComposite parse(List<String> paragraphs) {
        if (parser != null) {
            Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
            List<String> sentences = new ArrayList<>();
            for (String paragraph : paragraphs) {
                Matcher matcher = pattern.matcher(paragraph);
                while (matcher.find()) {
                    String sentence = matcher.group();
                    sentences.add(sentence);
                }
            }
            logger.info("Sentences: " + sentences);
            return parser.parse(sentences);
        } else {
            return new TextComposite();
        }
    }
}
