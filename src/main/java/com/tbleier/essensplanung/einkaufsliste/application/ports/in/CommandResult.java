package com.tbleier.essensplanung.einkaufsliste.application.ports.in;

public class CommandResult {
    public boolean isSuccessful() {
        return successful;
    }

    private final boolean successful;

    private final long id;

    public CommandResult(boolean successful, long id) {
        this.successful = successful;
        this.id = id;
    }

    public CommandResult(boolean successful) {
        this.successful = successful;
        this.id = 0;
    }

    public long getId() {
        return id;
    }
}
