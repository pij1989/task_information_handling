package com.pozharsky.dmitri.composite.impl;

import com.pozharsky.dmitri.composite.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextComposite implements Component {
    private List<Component> components = new ArrayList<>();

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
    public String buildString() {
        return components.stream()
                .map(Component::buildString)
                .collect(Collectors.joining(" "));
    }
}
