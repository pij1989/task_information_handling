package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.comparator.ComponentComparator;
import com.pozharsky.dmitri.composite.Component;
import com.pozharsky.dmitri.composite.impl.TextComposite;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TextCompositeService {

    public void sortParagraphBySentenceAmount(TextComposite textComposite, Comparator<Component> componentComparator) {
        List<Component> paragraphs = textComposite.getAll();
        List<Component> sortParagraphs = paragraphs.stream()
                .sorted(componentComparator)
                .collect(Collectors.toList());
        textComposite.setAll(sortParagraphs);
    }

    public List<Component> findSentenceWithMaxLengthWord(TextComposite textComposite) {
        List<Component> paragraphs = textComposite.getAll();
        List<Component> sentences = paragraphs.stream()
                .flatMap(e -> e.getAll().stream())
                .collect(Collectors.toList());
        List<Component> maxWordSentences = findMaxWordSentences(sentences);
        return maxWordSentences;
    }

    private List<Component> findMaxWordSentences(List<Component> sentences) {
        List<Component> maxWordSentences = new LinkedList<>();
        int maxWordLength = 0;
        int count = 0;
        for (int i = 0; i < sentences.size(); i++) {
            Component sentence = sentences.get(i);
            Component component = findMaxWordInSentence(sentence).orElseThrow();
            int wordLength = component.length();
            if (wordLength > maxWordLength) {
                maxWordLength = wordLength;
                if (!maxWordSentences.isEmpty()) {
                    while (count > 0) {
                        maxWordSentences.remove(--count);
                    }
                }
                maxWordSentences.add(sentence);
                count++;
            } else {
                if (wordLength == maxWordLength) {
                    maxWordSentences.add(sentence);
                    count++;
                }
            }
        }
        return maxWordSentences;
    }

    private Optional<Component> findMaxWordInSentence(Component sentence) {
        return sentence.getAll().stream()
                .flatMap(e -> e.getAll().stream())
                .filter(e -> e instanceof TextComposite)
                .max(ComponentComparator.WORD_LENGTH);
    }
}
