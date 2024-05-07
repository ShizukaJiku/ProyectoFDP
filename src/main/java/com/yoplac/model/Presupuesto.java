package com.yoplac.model;

public class Presupuesto {
    private double[] montos;

    public Presupuesto(Periodo periodo) {
        this.montos = new double[periodo.cantInAnual()];
    }

    public double[] getMontos() {
        return montos;
    }


    public void setMonto(int index, Double monto) {
        montos[index] = monto;
    }
}
