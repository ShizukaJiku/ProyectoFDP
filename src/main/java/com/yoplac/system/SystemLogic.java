package com.yoplac.system;

import com.yoplac.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class SystemLogic {
    public static final Empresa EMPRESA = generateEmpresa();
    public static Periodo PERIODO = getDefaultPeriodo();

    public static Periodo getDefaultPeriodo() {
        return Periodo.TRIMESTRAL;
    }

    private static Empresa generateEmpresa() {
        String nombre = "Mineria ABC";
        Registro presupuestoBase = generatePresupuestoBase();
        
        return new Empresa(nombre, presupuestoBase);
    }

    private static Registro generatePresupuestoBase() {
        Collection<Area> areas = generateAreas();
        return new Registro(areas);
    }

    private static Collection<Area> generateAreas() {
        Collection<Area> areas = new ArrayList<>();

        areas.add(generateArea("A"));
        areas.add(generateArea("B"));
        areas.add(generateArea("C"));
        areas.add(generateArea("D"));
        areas.add(generateArea("E"));

        return areas;
    }

    private static Area generateArea(String nombreArea) {
        Area area = new Area("Area " + nombreArea);

        Periodo periodo = getDefaultPeriodo();

        Collection<Actividad> actividades = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            Double[] montos = generateMontos(periodo);
            actividades.add(Actividad.generateActividad(nombreArea + " - " + i, periodo, montos));
        }

        area.setActividades(actividades);

        return area;
    }

    private static Double[] generateMontos(Periodo periodo) {
        Double[] montos = new Double[periodo.cantInAnual()];

        Random random = new Random();

        for (int i = 0; i < periodo.cantInAnual(); i++) {
            montos[i] = random.nextDouble() * 10000;
        }

        return montos;
    }
}
