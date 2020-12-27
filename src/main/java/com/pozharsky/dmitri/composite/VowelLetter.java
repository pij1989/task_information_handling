package com.pozharsky.dmitri.composite;

public class VowelLetter implements Component {
    private String letter;

    public VowelLetter(String letter) {
        this.letter = letter;
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
