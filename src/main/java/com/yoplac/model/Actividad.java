package com.yoplac.model;

public class Actividad {
    private String nombre;
    private Presupuesto[] presupuestos = generatePresupuestos();

    public Actividad(String nombre) {
        this.nombre = nombre;
    }

    public void setMonto(Periodo periodo, Double monto, int index) {
        Presupuesto presupuesto = presupuestos[periodo.ordinal()];
        presupuesto.setMonto(index, monto);
    }

    private Presupuesto[] generatePresupuestos() {
        Presupuesto[] presupuestos = new Presupuesto[Periodo.values().length];

        for(Periodo periodo : Periodo.values()) {
            presupuestos[periodo.ordinal()] = new Presupuesto(periodo);
        }

        return presupuestos;
    }
    
    public static Actividad generateActividad(String nombre, Periodo periodoInicial, Double[] montos) {
        Actividad actividad = new Actividad(nombre);

        for(int i = 0; i < periodoInicial.cantInAnual(); i++) {
            actividad.setMonto(periodoInicial, montos[i], i);
        }

        int ordinal = periodoInicial.ordinal();

        for(var periodo : Periodo.values()) {
            int ordinalPer = periodo.ordinal();

            if(ordinal > ordinalPer) {
                int perInPer = periodo.cantInAnual() / periodoInicial.cantInAnual();

                for(int i = 0; i < periodoInicial.cantInAnual(); i++) {
                    for(int e = i * perInPer; e < perInPer * (i + 1); e++) {
                        actividad.setMonto(periodo, montos[i] / perInPer, e);
                    }

                }
            }

            if(ordinal < ordinalPer) {
                int perInPer = periodoInicial.cantInAnual() / periodo.cantInAnual();

                for(int i = 0; i < periodo.cantInAnual(); i++) {
                    Double montoTotal = 0.0;

                    for(int e = i * perInPer; e < perInPer * (i + 1); e++) {
                        montoTotal += montos[e];
                    }

                    actividad.setMonto(periodo, montoTotal, i);
                }
            }
        }
        
        return actividad;
    }

    public String getNombre() {
        return nombre;
    }

    public Presupuesto getPresupuesto(Periodo periodo) {
        return presupuestos[periodo.ordinal()];
    }
}
