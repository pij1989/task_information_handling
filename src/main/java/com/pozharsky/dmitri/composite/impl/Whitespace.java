package com.pozharsky.dmitri.composite.impl;

import com.pozharsky.dmitri.composite.Component;

import java.util.List;

public class Whitespace implements Component {
    private Character whitespace;

    public Whitespace(Character whitespace) {
        this.whitespace = whitespace;
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
    public String buildString() {
        return Character.toString(whitespace);
    }
}
