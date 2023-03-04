package com.tbleier.kitchenlist.application.ports.in;

public class CommandResult {
    public boolean isSuccessful() {
        return successful;
    }

    private final boolean successful;

    public CommandResult(boolean successful) {
        this.successful = successful;
    }
}
