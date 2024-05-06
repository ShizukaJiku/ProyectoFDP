package com.yoplac.model;

public enum Periodo {
    MENSUAL,
    TRIMESTRAL,
    SEMESTRAL,
    ANUAL;

    public int cantInAnual() {
        return switch (this) {
            case MENSUAL -> 12;
            case TRIMESTRAL -> 4;
            case SEMESTRAL -> 2;
            case ANUAL -> 1;
        };
    }
}
