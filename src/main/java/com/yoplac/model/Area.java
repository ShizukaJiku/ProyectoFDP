package com.yoplac.model;

import java.util.Collection;

public class Area {
    private String nombre;
    private Collection<Actividad> actividades;

    public Area(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(Collection<Actividad> actividades) {
        this.actividades = actividades;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(nombre + ":\n");

        for (Actividad actividad : actividades) {
            sb.append("\t" + actividad.getNombre() + "\n");
        }

        return sb.toString();
    }
}
