package com.yoplac.system;

import com.yoplac.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SystemLogic {
    public static Empresa EMPRESA = generateEmpresa();
    public static Periodo PERIODO = getDefaultPeriodo();

    public static Periodo getDefaultPeriodo() {
        return Periodo.TRIMESTRAL;
    }

    public static void start() {
        do {
            String opcion = SystemUI.menuPrincipal();

            switch (opcion) {
                case "1": EMPRESA.setRegistro(SystemUI.menuIngresarDatos(EMPRESA.getPresupuestoBase()));
                break;
                case "2":
                break;
                case "3": SystemUI.mostrarReporte(EMPRESA.getPresupuestoBase(), PERIODO);
                break;
                case "4": logicaMenuMantenimieto();
            }

            if(opcion.equals("0")) break;
        }while (true);
    }

    private static void logicaMenuMantenimieto() {
        do {
            String opcion = SystemUI.menuMantenimiento();

            switch (opcion) {
                case "1": menuAreas();
                    break;
                case "2": escogerPeriodo();
                    break;
            }

            if(opcion.equals("0")) break;
        }while (true);
    }

    private static void escogerPeriodo() {
        do {
            int ordinal = Integer.parseInt(String.valueOf(SystemUI.seleccionarPeriodo(PERIODO)));

            if(ordinal == 0) break;

            PERIODO = Periodo.values()[ordinal - 1];
        }while (true);
    }

    private static void menuAreas() {
        List<Area> areas = EMPRESA.getPresupuestoBase().getAreas();

        do {
            int opcion = Integer.parseInt(SystemUI.menuAreas(areas));

            if(opcion == 0) break;

            if(opcion == areas.size() + 1) {
                Area nuevaArea = SystemUI.nuevaArea();
                areas.add(nuevaArea);
                continue;
            }

            Area areaBuscada = areas.get(opcion - 1);

            logicaMantenimientoArea(areaBuscada);
        }while (true);
    }

    private static void logicaMantenimientoArea(Area areaBuscada) {
        do {
            int opcion = Integer.parseInt(SystemUI.mantenimientoArea(areaBuscada));

            if(opcion == 0) break;

            if(opcion == areaBuscada.getActividades().size() + 1) {
                Actividad nuevaActividad = SystemUI.nuevaActividad();
                areaBuscada.getActividades().add(nuevaActividad);
                continue;
            }

            Actividad actividadBuscada = areaBuscada.getActividades().get(opcion - 1);
            logicaMantenimientoActividad(actividadBuscada);
        }while (true);
    }

    private static void logicaMantenimientoActividad(Actividad actividadBuscada) {
        do {
            int opcion = Integer.parseInt(SystemUI.mantenimientoActividad(actividadBuscada));

            if(opcion == 0) break;
        }while (true);
    }

    private static Empresa generateEmpresa() {
        String nombre = "Mineria ABC";
        Registro presupuestoBase = generatePresupuestoBase();
        
        return new Empresa(nombre, presupuestoBase);
    }

    private static Registro generatePresupuestoBase() {
        List<Area> areas = generateAreas();
        return new Registro(areas);
    }

    private static List<Area> generateAreas() {
        List<Area> areas = new ArrayList<>();

        areas.add(generateArea("A"));
        areas.add(generateArea("B"));

        return areas;
    }

    private static Area generateArea(String nombreArea) {
        Area area = new Area("Area " + nombreArea);

        Periodo periodo = getDefaultPeriodo();

        List<Actividad> actividades = new ArrayList<>();

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
