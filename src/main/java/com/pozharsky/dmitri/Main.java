package com.pozharsky.dmitri;

import com.pozharsky.dmitri.parser.LexemeParser;
import com.pozharsky.dmitri.parser.ParagraphParser;
import com.pozharsky.dmitri.parser.SentenceParser;
import com.pozharsky.dmitri.reader.TextReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TextReader reader = new TextReader();
        String string = reader.readText("data\\text.txt");
        System.out.println(string);
        LexemeParser lexemeParser = new LexemeParser(null);
        SentenceParser sentenceParser = new SentenceParser(lexemeParser);
        ParagraphParser paragraphParser = new ParagraphParser(string.trim(), sentenceParser);
        List<String> result = paragraphParser.handleRequest();
        System.out.println(result);
    }
}
