package com.pozharsky.dmitri.validator.impl;

import com.pozharsky.dmitri.validator.SymbolValidator;

public class VowelValidator implements SymbolValidator {
    private static final String VOWEL_PATTERN = "(?i)[aeiouyаоуэиыеяюё]";
    public static final VowelValidator INSTANCE = new VowelValidator();

    private VowelValidator() {
    }

    @Override
    public boolean validate(String symbol) {
        return symbol.matches(VOWEL_PATTERN);
    }
}
