package com.pozharsky.dmitri.composite;

import java.util.List;

public interface Component {
    void add(Component component);

    void remove(Component component);

    Component get(int index);

    List<Component> getAll();

    void setAll(List<Component> components);

    int length();

    String buildString();
}
