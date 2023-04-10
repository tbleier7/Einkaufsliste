package com.tbleier.essensplanung.einkaufsliste.application.ports.out;

public class NonUniqueException extends RuntimeException{
    public NonUniqueException(String message) {
        super(message);
    }
}
