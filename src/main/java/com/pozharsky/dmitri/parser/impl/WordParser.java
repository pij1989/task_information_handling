package com.pozharsky.dmitri.parser.impl;

import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.parser.Parser;

import java.util.List;

public class WordParser implements Parser<TextComposite,List<String>> {
    private SymbolParser parser;

    public WordParser(SymbolParser parser) {
        this.parser = parser;
    }


    @Override
    public TextComposite parse(List<String> lexemes) {
        if(parser != null){
            return parser.parse(lexemes);
        } else {
            return new TextComposite();
        }
    }
}
