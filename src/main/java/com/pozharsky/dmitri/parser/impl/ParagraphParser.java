package com.pozharsky.dmitri.parser.impl;

import com.pozharsky.dmitri.composite.TextType;
import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.stream.Stream;

public class ParagraphParser implements Parser<Optional<TextComposite>, String> {
    private static final Logger logger = LogManager.getLogger(ParagraphParser.class);
    private static final String THREE_SPACE_DELIMITER = "\\s{3}";
    private SentenceParser parser;

    public ParagraphParser(SentenceParser parser) {
        this.parser = parser;
    }

    @Override
    public Optional<TextComposite> parse(String data) {
        if (parser != null) {
            TextComposite textComposite = new TextComposite(TextType.TEXT);
            Stream.of(data.split(THREE_SPACE_DELIMITER))
                    .forEach(e -> {
                        logger.debug("Paragraph: " + e);
                        Optional<TextComposite> optionalParagraph = parser.parse(e);
                        TextComposite paragraph = optionalParagraph.orElseThrow();
                        textComposite.add(paragraph);
                    });
            return Optional.of(textComposite);
        } else {
            return Optional.empty();
        }
    }
}
