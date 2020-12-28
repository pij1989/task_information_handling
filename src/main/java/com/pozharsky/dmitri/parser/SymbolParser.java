package com.pozharsky.dmitri.parser;

import java.util.List;

public class SymbolParser implements Handler {
    private List<String> words;

    @Override
    public List<String> handleRequest() {
        return null;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
