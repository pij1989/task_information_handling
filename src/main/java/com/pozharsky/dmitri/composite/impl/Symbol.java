package com.pozharsky.dmitri.composite.impl;

import com.pozharsky.dmitri.composite.Component;

public class Symbol implements Component {
    private String symbol;

    public Symbol(String symbol) {
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
    public String buildString() {
        return symbol;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Symbol{");
        sb.append("symbol='").append(symbol).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
