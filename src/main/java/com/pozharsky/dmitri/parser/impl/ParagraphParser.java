package com.pozharsky.dmitri.parser.impl;

import com.pozharsky.dmitri.composite.impl.Punctuation;
import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.parser.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.stream.Stream;

public class ParagraphParser implements Parser<Optional<TextComposite>, String> {
    private static final Logger logger = LogManager.getLogger(ParagraphParser.class);
    private static final String THREE_SPACE_DELIMITER = "\\s{3}";
    private static final Character WHITESPACE = ' ';
    private static final Character ENTER = '\n';
    private SentenceParser parser;

    public ParagraphParser(SentenceParser parser) {
        this.parser = parser;
    }

    @Override
    public Optional<TextComposite> parse(String data) {
        if (parser != null) {
            TextComposite textComposite = new TextComposite();
            Stream.of(data.split(THREE_SPACE_DELIMITER))
                    .forEach(e -> {
                        logger.debug("Paragraph: " + e);
                        Optional<TextComposite> optionalParagraph = parser.parse(e);
                        TextComposite paragraph = optionalParagraph.orElseThrow();
                        for (int i = 0; i < 3; i++) {
                            textComposite.add(new Punctuation(WHITESPACE));
                        }
                        textComposite.add(paragraph);
                        textComposite.add(new Punctuation(ENTER));
                    });
            return Optional.of(textComposite);
        } else {
            return Optional.empty();
        }
    }
}
