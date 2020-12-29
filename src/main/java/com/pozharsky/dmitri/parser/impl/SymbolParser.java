package com.pozharsky.dmitri.parser.impl;

import com.pozharsky.dmitri.composite.impl.Symbol;
import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.parser.Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolParser implements Parser<TextComposite, String> {
    private static final String SYMBOL_PATTERN = "[A-Z_a-z_А-Я_а-я_0-9]";
    private static final int INDEX = 0;

    @Override
    public TextComposite parse(String word) {
        TextComposite textComposite = new TextComposite();
        Pattern pattern = Pattern.compile(SYMBOL_PATTERN);
        Matcher matcher = pattern.matcher(word);
        while (matcher.find()){
            String symbol = matcher.group();
            textComposite.add(new Symbol(symbol.charAt(INDEX)));
        }
        return textComposite;
    }
}
