package com.pozharsky.dmitri.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements Handler {
    private static final String SENTENCE_PATTERN = "[A-Z][^.!?]*((\\.|!|\\?)(?! |\\n|\\r|\\r\\n)[^.!?]*)*(\\.|!|\\?)";
    private List<String> paragraphs;
    private LexemeParser parser;

    public SentenceParser(LexemeParser parser) {
        this.parser = parser;
    }

    @Override
    public List<String> handleRequest() {
        Pattern pattern = Pattern.compile(SENTENCE_PATTERN);
        List<String> sentences = new ArrayList<>();
        for (String paragraph : paragraphs) {
            Matcher matcher = pattern.matcher(paragraph);
            while (matcher.find()) {
                String sentence = matcher.group();
                sentences.add(sentence);
            }
        }
        if (parser == null) {
            return sentences;
        } else {
            parser.setSentences(sentences);
            return parser.handleRequest();
        }
    }

    void setParagraphs(List<String> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
