package com.tbleier.essensplanung.application.ports.in;

public interface CommandService<T> {
    CommandResult execute(T command);
}
