package com.pozharsky.dmitri;

import com.pozharsky.dmitri.comparator.ComponentComparator;
import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.parser.impl.*;
import com.pozharsky.dmitri.reader.TextReader;
import com.pozharsky.dmitri.service.TextCompositeService;

import java.io.IOException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        TextReader reader = new TextReader();
        String string = reader.readText("data\\text.txt");
        //System.out.println(string);
        LexemeParser lexemeParser = new LexemeParser(new WordParser(new SymbolParser()));
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        Optional<TextComposite> optional = paragraphParser.parse(string.trim());
        TextComposite textComposite = optional.orElseThrow();
        System.out.println(textComposite.buildString());
        TextCompositeService textCompositeService = new TextCompositeService();
        textCompositeService.sortParagraphBySentenceAmount(textComposite, ComponentComparator.SENTENCE_AMOUNT);
        System.out.println(textComposite.buildString());
    }
}
