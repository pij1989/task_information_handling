package com.pozharsky.dmitri.parser.impl;

import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParagraphParser implements Parser<TextComposite, String> {
    private static final Logger logger = LogManager.getLogger(ParagraphParser.class);
    private static final String THREE_SPACE_DELIMITER = "\\s{3}";
    private SentenceParser parser;

    public ParagraphParser(SentenceParser parser) {
        this.parser = parser;
    }

    @Override
    public TextComposite parse(String data) {
        if (parser != null) {
            List<String> paragraphs = Stream.of(data.split(THREE_SPACE_DELIMITER))
                    .collect(Collectors.toList());
            logger.info("Paragraphs: " + paragraphs);
            return parser.parse(paragraphs);
        } else {
            return new TextComposite();
        }
    }
}
