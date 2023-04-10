package com.tbleier.essensplanung.einkaufsliste.application.ports.in;

public interface QueryService<T, R> {
    R execute(T query);
}
