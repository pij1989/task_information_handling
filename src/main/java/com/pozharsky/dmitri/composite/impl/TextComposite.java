package com.pozharsky.dmitri.composite.impl;

import com.pozharsky.dmitri.composite.Component;
import com.pozharsky.dmitri.composite.TextType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextComposite implements Component {
    private static final String WHITESPACE = " ";
    private static final String LINE_BREAK = "\n";
    private static final String EMPTY = "";
    private TextType textType;
    private List<Component> components;

    public TextComposite(TextType textType) {
        this.textType = textType;
        components = new ArrayList<Component>();
    }

    @Override
    public void add(Component component) {
        components.add(component);
    }

    @Override
    public void remove(Component component) {
        components.remove(component);
    }

    @Override
    public Component get(int index) {
        return components.get(index);
    }

    @Override
    public List<Component> getAll() {
        return new ArrayList<>(components);
    }

    @Override
    public void setAll(List<Component> components) {
        this.components = new ArrayList<>(components);
    }

    @Override
    public int length() {
        return components.size();
    }

    @Override
    public String buildString() {
        String result = EMPTY;
        switch (textType) {
            case TEXT: {
                result = components.stream()
                        .map(e -> {
                            StringBuilder builder = new StringBuilder();
                            String line = e.buildString();
                            builder.append(WHITESPACE).append(WHITESPACE).append(WHITESPACE).append(line);
                            return builder;
                        })
                        .collect(Collectors.joining(LINE_BREAK));
                break;
            }
            case PARAGRAPH:
            case SENTENCE: {
                result = join(WHITESPACE);
                break;
            }
            case LEXEME:
            case WORD: {
                result = join(EMPTY);
                break;
            }
        }
        return result;
    }

    private String join(String delimiter) {
        return components.stream()
                .map(Component::buildString)
                .collect(Collectors.joining(delimiter));
    }
}
