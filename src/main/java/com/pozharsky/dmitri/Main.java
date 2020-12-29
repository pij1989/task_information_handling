package com.pozharsky.dmitri;

import com.pozharsky.dmitri.parser.impl.*;
import com.pozharsky.dmitri.reader.TextReader;

public class Main {
    public static void main(String[] args) {
        TextReader reader = new TextReader();
        String string = reader.readText("data\\text.txt");
        //System.out.println(string);
        LexemeParser lexemeParser = new LexemeParser(new WordParser(new SymbolParser()));
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        paragraphParser.parse(string.trim());
    }
}
