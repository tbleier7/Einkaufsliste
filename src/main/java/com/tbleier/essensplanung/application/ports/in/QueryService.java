package com.tbleier.essensplanung.application.ports.in;

public interface QueryService<T, R> {
    R execute(T query);
}
