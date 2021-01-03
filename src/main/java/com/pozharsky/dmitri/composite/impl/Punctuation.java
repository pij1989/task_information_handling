package com.pozharsky.dmitri.composite.impl;

import com.pozharsky.dmitri.composite.Component;

import java.util.List;

public class Punctuation implements Component {
    private Character punctuation;

    public Punctuation(Character punctuation) {
        this.punctuation = punctuation;
    }

    @Override
    public void add(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Component get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Component> getAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setAll(List<Component> components) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int length() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String buildString() {
        return Character.toString(punctuation);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Punctuation{");
        sb.append("punctuation=").append(punctuation);
        sb.append('}');
        return sb.toString();
    }
}
