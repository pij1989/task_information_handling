package com.pozharsky.dmitri.composite.impl;

import com.pozharsky.dmitri.composite.Component;

public class Symbol implements Component {
    private Character symbol;

    public Symbol(Character symbol) {
        this.symbol = symbol;
    }

    @Override
    public void add(Component component) {

    }

    @Override
    public void remove(Component component) {

    }

    @Override
    public Component get(int index) {
        return null;
    }

    @Override
    public String buildString() {
        return null;
    }
}
