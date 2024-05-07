package com.yoplac.model;

import java.util.ArrayList;
import java.util.List;

public class Area {
    private String nombre;
    private List<Actividad> actividades = new ArrayList<>();

    public Area(String nombre) {
        this.nombre = nombre;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
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
