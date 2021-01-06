package com.pozharsky.dmitri.validator.impl;

import com.pozharsky.dmitri.validator.SymbolValidator;

public class ConsonantValidator implements SymbolValidator {
    private static final String CONSONANT_PATTERN = "(?i)[qwrtplkjhgfdszxcvbnmйцкнгшщзхфвпрлджбтмсч]";
    public static final ConsonantValidator INSTANCE = new ConsonantValidator();

    private ConsonantValidator() {
    }

    @Override
    public boolean validate(String symbol) {
        return symbol.matches(CONSONANT_PATTERN);
    }
}
