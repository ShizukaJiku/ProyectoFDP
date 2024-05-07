package com.yoplac.model;

public enum Periodo {
    MENSUAL("MES"),
    TRIMESTRAL("TRIME"),
    SEMESTRAL("SEMES"),
    ANUAL("ANUAL");

    private final String abreviacion;

    Periodo(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public int cantInAnual() {
        return switch (this) {
            case MENSUAL -> 12;
            case TRIMESTRAL -> 4;
            case SEMESTRAL -> 2;
            case ANUAL -> 1;
        };
    }
}
