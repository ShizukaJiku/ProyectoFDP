package com.yoplac.model;

public class Empresa {
    private String nombre;
    private Registro presupuestoBase;
    private Registro registro;
    private Periodo periodo = Periodo.MENSUAL;

    public Empresa(String nombre, Registro presupuestoBase) {
        this.nombre = nombre;
        this.presupuestoBase = presupuestoBase;
    }

    public String getNombre() {
        return nombre;
    }

    public Registro getPresupuestoBase() {
        return presupuestoBase;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }
}
