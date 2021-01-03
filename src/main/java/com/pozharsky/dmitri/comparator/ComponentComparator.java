package com.pozharsky.dmitri.comparator;

import com.pozharsky.dmitri.composite.Component;

import java.util.Comparator;

public enum ComponentComparator implements Comparator<Component> {
    SENTENCE_AMOUNT {
        @Override
        public int compare(Component o1, Component o2) {
            return Integer.compare(o1.length(), o2.length());
        }
    },
    WORD_LENGTH {
        @Override
        public int compare(Component o1, Component o2) {
            return Integer.compare(o1.length(), o2.length());
        }
    }
}
