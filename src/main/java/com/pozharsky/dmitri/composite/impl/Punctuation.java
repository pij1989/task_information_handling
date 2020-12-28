package com.pozharsky.dmitri.composite.impl;

import com.pozharsky.dmitri.composite.Component;

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
    public String buildString() {
        return null;
    }
}