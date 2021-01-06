package com.pozharsky.dmitri.composite.impl;

import com.pozharsky.dmitri.composite.Component;

import java.util.List;

public class Symbol implements Component {
    private Character symbol;

    public Symbol(Character symbol) {
        this.symbol = symbol;
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
        return Character.toString(symbol);
    }
}
