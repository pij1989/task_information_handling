package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.composite.Component;
import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.composite.impl.Whitespace;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TextCompositeService {
    private static final Character ENTER = '\n';

    public void sortParagraphBySentenceAmount(TextComposite textComposite, Comparator<Component> componentComparator) {
        List<Component> paragraphs = textComposite.getAll().stream()
                .filter(e -> e instanceof TextComposite)
                .collect(Collectors.toList());
        List<Component> sortParagraphs = paragraphs.stream()
                .sorted(componentComparator)
                .collect(Collectors.toList());
        List<Component> result = sortParagraphs.stream()
                .peek(e -> e.add(new Whitespace(ENTER)))
                .collect(Collectors.toList());
        textComposite.setAll(result);
    }
}
