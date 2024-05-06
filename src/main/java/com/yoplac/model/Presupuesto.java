package com.yoplac.model;

public class Presupuesto {
    private Periodo periodo;
    private Double[] montos;

    public Presupuesto(Periodo periodo) {
        this.periodo = periodo;
        this.montos = new Double[periodo.cantInAnual()];
    }

    public Double[] getMontos() {
        return montos;
    }

    public Double getMontoTotal() {
        Double montoTotal = 0.0;

        for (Double monto : montos) {
            montoTotal += monto;
        }

        return montoTotal;
    }

    public Double getMonto(int index) {
        return montos[index];
    }

    public void setMonto(int index, Double monto) {
        montos[index] = monto;
    }
}
