package com.pozharsky.dmitri;

import com.pozharsky.dmitri.parser.impl.LexemeParser;
import com.pozharsky.dmitri.parser.impl.ParagraphParser;
import com.pozharsky.dmitri.parser.impl.SentenceParser;
import com.pozharsky.dmitri.parser.impl.WordParser;
import com.pozharsky.dmitri.reader.TextReader;

public class Main {
    public static void main(String[] args) {
        TextReader reader = new TextReader();
        String string = reader.readText("data\\text.txt");
        System.out.println(string);
        LexemeParser lexemeParser = new LexemeParser(new WordParser(null));
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);
        paragraphParser.parse(string.trim());
    }
}
