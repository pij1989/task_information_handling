package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.comparator.ComponentComparator;
import com.pozharsky.dmitri.composite.Component;
import com.pozharsky.dmitri.composite.impl.TextComposite;

import java.util.*;
import java.util.stream.Collectors;

public class TextCompositeService {

    public void sortParagraphBySentenceAmount(Component textComposite, Comparator<Component> componentComparator) {
        List<Component> paragraphs = textComposite.getAll();
        List<Component> sortParagraphs = paragraphs.stream()
                .sorted(componentComparator)
                .collect(Collectors.toList());
        textComposite.setAll(sortParagraphs);
    }

    public List<Component> findSentenceWithMaxLengthWord(Component textComposite) {
        List<Component> paragraphs = textComposite.getAll();
        List<Component> sentences = paragraphs.stream()
                .flatMap(e -> e.getAll().stream())
                .collect(Collectors.toList());
        return findMaxWordSentences(sentences);
    }

    public List<Component> removeSentencesAmountWordLess(Component textComposite, int amount) {
        List<Component> paragraphs = textComposite.getAll();
        List<Component> removeSentences = new ArrayList<>();
        for (Component paragraph : paragraphs) {
            List<Component> sentences = paragraph.getAll();
            for (Component sentence : sentences) {
                List<Component> words = sentenceWithoutPunctuation(sentence);
                if (words.size() < amount) {
                    removeSentences.add(sentence);
                }
            }
            removeSentences.forEach(sentences::remove);
            paragraph.setAll(sentences);
        }
        return removeSentences;
    }

    public Map<String, Integer> defineAmountSameWord(Component textComposite) {
        Map<String, Integer> result = new HashMap<>();
        List<Component> paragraphs = textComposite.getAll();
        for (Component paragraph : paragraphs) {
            List<Component> sentences = paragraph.getAll();
            for (Component sentence : sentences) {
                List<Component> words = sentenceWithoutPunctuation(sentence);
                for (Component word : words) {
                    String wordLowerCase = word.buildString().toLowerCase();
                    if (result.get(wordLowerCase) != null) {
                        int count = result.get(wordLowerCase);
                        result.put(wordLowerCase, ++count);
                    } else {
                        result.put(wordLowerCase, 1);
                    }
                }
            }
        }
        return result.entrySet().stream()
                .filter(e -> e.getValue() != 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Component> findMaxWordSentences(List<Component> sentences) {
        List<Component> maxWordSentences = new LinkedList<>();
        int maxWordLength = 0;
        int count = 0;
        for (Component sentence : sentences) {
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

    private List<Component> sentenceWithoutPunctuation(Component sentence) {
        return sentence.getAll().stream()
                .flatMap(e -> e.getAll().stream())
                .filter(e -> e instanceof TextComposite)
                .collect(Collectors.toList());
    }
}
