package com.tbleier.kitchenlist.application.ports.in;

public interface CommandService<T> {
    void execute(T command);
}
