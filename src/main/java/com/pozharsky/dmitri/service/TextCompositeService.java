package com.pozharsky.dmitri.service;

import com.pozharsky.dmitri.comparator.ComponentComparator;
import com.pozharsky.dmitri.composite.Component;
import com.pozharsky.dmitri.composite.impl.TextComposite;
import com.pozharsky.dmitri.validator.SymbolValidator;
import com.pozharsky.dmitri.validator.impl.ConsonantValidator;
import com.pozharsky.dmitri.validator.impl.VowelValidator;

import java.util.*;
import java.util.stream.Collectors;

public class TextCompositeService {
    private static final int ZERO = 0;
    private static final int ONE = 1;

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
                        result.put(wordLowerCase, ONE);
                    }
                }
            }
        }
        return result.entrySet().stream()
                .filter(e -> e.getValue() != ONE)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public int defineAmountVowels(Component textComposite) {
        return defineAmountSymbol(textComposite, VowelValidator.INSTANCE);
    }

    public int defineAmountConsonants(Component textComposite) {
        return defineAmountSymbol(textComposite, ConsonantValidator.INSTANCE);
    }

    private List<Component> findMaxWordSentences(List<Component> sentences) {
        List<Component> maxWordSentences = new LinkedList<>();
        int maxWordLength = ZERO;
        int count = ZERO;
        for (Component sentence : sentences) {
            Component component = findMaxWordInSentence(sentence).orElseThrow();
            int wordLength = component.length();
            if (wordLength > maxWordLength) {
                maxWordLength = wordLength;
                if (!maxWordSentences.isEmpty()) {
                    while (count > ZERO) {
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

    private int defineAmountSymbol(Component textComposite, SymbolValidator validator) {
        int count = ZERO;
        List<Component> paragraphs = textComposite.getAll();
        for (Component paragraph : paragraphs) {
            List<Component> sentences = paragraph.getAll();
            for (Component sentence : sentences) {
                List<Component> words = sentenceWithoutPunctuation(sentence);
                for (Component word : words) {
                    List<Component> symbols = word.getAll();
                    for (Component symbol : symbols) {
                        String letter = symbol.buildString();
                        if (validator.validate(letter)) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
