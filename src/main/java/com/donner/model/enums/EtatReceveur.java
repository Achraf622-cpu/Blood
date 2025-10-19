package com.donner.model.enums;

public enum EtatReceveur {
    EN_ATTENTE("En attente"),
    SATISFAIT("Satisfait");

    private final String displayName;

    EtatReceveur(String displayName) {
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

