package com.tbleier.essensplanung.einkaufsliste.application.ports.in;

public interface CommandService<T> {
    CommandResult execute(T command);
}
