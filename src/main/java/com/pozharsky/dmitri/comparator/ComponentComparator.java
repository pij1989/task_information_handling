package com.pozharsky.dmitri.comparator;

import com.pozharsky.dmitri.composite.Component;
import com.pozharsky.dmitri.composite.impl.TextComposite;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum ComponentComparator implements Comparator<Component> {
    SENTENCE_AMOUNT {
        @Override
        public int compare(Component o1, Component o2) {
            List<Component> sentences1 = o1.getAll().stream()
                    .filter(e -> e instanceof TextComposite)
                    .collect(Collectors.toList());
            List<Component> sentences2 = o2.getAll().stream()
                    .filter(e -> e instanceof TextComposite)
                    .collect(Collectors.toList());
            return Integer.compare(sentences1.size(), sentences2.size());
        }
    }
}
