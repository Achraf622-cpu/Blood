package com.donner.model.enums;

public enum SituationMedicale {
    CRITIQUE("Critique", 4),
    URGENT("Urgent", 3),
    NORMAL("Normal", 1);

    private final String displayName;
    private final int nombrePochesRequises;

    SituationMedicale(String displayName, int nombrePochesRequises) {
        this.displayName = displayName;
        this.nombrePochesRequises = nombrePochesRequises;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getNombrePochesRequises() {
        return nombrePochesRequises;
    }

    @Override
    public String toString() {
        return displayName;
    }
}

