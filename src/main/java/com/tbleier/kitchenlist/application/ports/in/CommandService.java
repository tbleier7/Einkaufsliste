package com.tbleier.kitchenlist.application.ports.in;

public interface CommandService<T> {
    CommandResult execute(T command);
}
