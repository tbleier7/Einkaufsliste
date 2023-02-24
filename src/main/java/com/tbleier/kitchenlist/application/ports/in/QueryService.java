package com.tbleier.kitchenlist.application.ports.in;

public interface QueryService<T, R> {
    R execute(T query);
}
