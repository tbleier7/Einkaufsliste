package com.tbleier.essensplanung.einkaufsliste.application.ports.in.commands;

public class MoveZutatCommand {
    private final long zutatId;
    private final int destinationIndex;

    public MoveZutatCommand(long zutatId, int destinationIndex) {
        this.zutatId = zutatId;
        this.destinationIndex = destinationIndex;
    }

    public long getZutatId() {
        return zutatId;
    }

    public int getDestinationIndex() {
        return destinationIndex;
    }
}
