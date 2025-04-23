package ru.nsu.ccfit.kupzov.lab3.observer;

public interface Observer<C> {
    void update(C context);
}
