package com.pozharsky.dmitri.parser;

import java.util.List;

public class WordParser implements Handler {
    private List<String> lexemes;
    private SymbolParser parser;

    public WordParser(SymbolParser parser) {
        this.parser = parser;
    }

    @Override
    public List<String> handleRequest() {
        return null;
    }

    void setLexemes(List<String> lexemes) {
        this.lexemes = lexemes;
    }
}
