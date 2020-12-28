package com.pozharsky.dmitri.parser;

public interface Parser<R,T> {
    R parse(T data);
}
