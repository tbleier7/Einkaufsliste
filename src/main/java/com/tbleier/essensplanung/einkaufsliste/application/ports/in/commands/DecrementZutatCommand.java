package com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands;

public class DecrementZutatCommand {
    private final long zutatId;

    public DecrementZutatCommand(long zutatId) {
        this.zutatId = zutatId;
    }

    public long getZutatId() {
        return zutatId;
    }
}
