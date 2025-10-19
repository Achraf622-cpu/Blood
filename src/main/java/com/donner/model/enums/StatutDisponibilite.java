package com.donner.model.enums;

public enum StatutDisponibilite {
    DISPONIBLE("Disponible"),
    NON_DISPONIBLE("Non disponible"),
    NON_ELIGIBLE("Non éligible");

    private final String displayName;

    StatutDisponibilite(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

