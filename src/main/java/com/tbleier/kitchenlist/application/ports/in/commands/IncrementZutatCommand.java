package com.tbleier.kitchenlist.application.ports.in.commands;

public class IncrementZutatCommand {
    private final long zutatId;

    public IncrementZutatCommand(long zutatId) {
        this.zutatId = zutatId;
    }

    public long getZutatId() {
        return zutatId;
    }
}
