package com.yoplac.system;

import com.yoplac.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class SystemUI {
    private final static String SEPARATOR = "----------------------------------------------------";
    private final static Scanner input = new Scanner(System.in);

    public static String menuPrincipal() {
        mostrarTitulo("Menu Principal - Sistema Control Presupuesto");

        escrbir("1. Ingresar Datos");
        escrbir("2. Generar Reporte");
        escrbir("3. Mostrar Presupuesto");
        escrbir("4. Menu Mantenimiento");
        escrbir("0. Salir");

        return leerOpcion();
    }

    public static Registro menuIngresarDatos(Registro presupuestoBase) {
        mostrarTitulo("Ingresar Datos - Sistema Control Presupuesto");
        escrbir("Periodo actual -> " + SystemLogic.PERIODO);
        escrbir(SEPARATOR);

        List<Area> newAreas = new ArrayList<>();

        for(var area : presupuestoBase.getAreas()) {
            escrbir("Area -> " + area.getNombre());
            escrbir(SEPARATOR);

            Collection<Actividad> newActividades = new ArrayList<>();

            for (var actividad : area.getActividades()) {
                escrbir("Actividad: " + actividad.getNombre());

                Double[] montos = new Double[SystemLogic.PERIODO.cantInAnual()];

                for(int i = 0; i < montos.length; i++) {
                    System.out.print("Col " + (i + 1)  +" - Ingrese monto --> ");
                    montos[i] = Double.parseDouble(input.nextLine());
                }

                newActividades.add(Actividad.generateActividad(actividad.getNombre(), SystemLogic.PERIODO, montos));
            }

            Area newArea = new Area(area.getNombre());
            newArea.setActividades(area.getActividades());
            newAreas.add(newArea);

            escrbir(SEPARATOR);
        }

        System.out.println("Presione cualquier tecla para regresar");

        return new Registro(newAreas);
    }

    public static String menuMantenimiento() {
        mostrarTitulo("Menu Mantenimiento - Sistema Control Presupuesto");

        escrbir("1. Menu Areas");
        escrbir("2. Escoger Periodo");
        escrbir("0. Regresar");

        return leerOpcion();
    }

    public static String menuAreas(List<Area> areas) {
        mostrarTitulo("Menu Areas - Sistema Control Presupuesto");

        listarAreas(areas);
        escrbir((areas.size() + 1) + ". Agregar nueva area");
        escrbir("0. Regresar");

        return leerOpcion();
    }

    public static String mantenimientoArea(Area area) {
        var actividades = area.getActividades();

        mostrarTitulo("Mantenimiento Area - Sistema Control Presupuesto");

        escrbir("Area -- > " + area.getNombre() + "\n");
        listarActividades(actividades);
        escrbir((actividades.size() + 1) + ". Agregar nueva actividad");
        escrbir("0. Regresar");

        return leerOpcion();
    }

    public static String mantenimientoActividad(Actividad actividad) {
        mostrarTitulo("Mantenimiento Actividad - Sistema Control Presupuesto");

        escrbir("0. Regresar");
        return leerOpcion();
    }

    public static Actividad nuevaActividad() {
        escrbir(SEPARATOR);
        System.out.print("Ingresar nueva actividad --> ");
        String nombre = input.nextLine();
        escrbir(SEPARATOR);

        return new Actividad(nombre);
    }

    public static Area nuevaArea() {
        escrbir(SEPARATOR);
        System.out.print("Ingresar nueva area --> ");
        String nombre = input.nextLine();
        escrbir(SEPARATOR);

        return new Area(nombre);
    }

    public static String seleccionarPeriodo(Periodo periodoActual) {
        mostrarTitulo("Periodo Actual: " + periodoActual);

        for(int i = 0; i < Periodo.values().length; i++) {
            Periodo periodo = Periodo.values()[i];
            escrbir((i+1) + ". " + periodo);
        }
        escrbir("0. Regresar");

        return leerOpcion();
    }

    public static void mostrarReporte(Registro registro, Periodo periodo) {
        mostrarTitulo("Reporte " + periodo);
        // Construir la cabecera
        StringBuilder cabecera = new StringBuilder();
        cabecera.append(String.format("%-15s", "Áreas/Actividad"));

        for (int i = 0; i < periodo.cantInAnual(); i++) {
            cabecera.append(String.format("%15s", periodo.getAbreviacion() + " " + (i + 1)));
        }

        escrbir(cabecera);

        // Construir las filas
        for (Area area : registro.getAreas()) {
            StringBuilder fila = new StringBuilder();
            fila.append(String.format("%-25s", area.getNombre())).append("\n");

            for (Actividad actividad : area.getActividades()) {
                fila.append(String.format("%15s", actividad.getNombre()));

                Presupuesto presupuesto = actividad.getPresupuesto(periodo);

                for (Double monto : presupuesto.getMontos()) {
                    fila.append(String.format("%15.2f", monto));
                }

                fila.append("\n");
            }

            escrbir(fila);
        }

        pressToBack();
    }

    private static void pressToBack() {
        System.out.print("Presionar para regresar...");
        input.nextLine();
    }

    private static void listarAreas(List<Area> areas) {
        for (int i = 0; i < areas.size(); i++) {
            Area area = areas.get(i);
            escrbir((i + 1) + ". " + area.getNombre());
        }

        System.out.println(SEPARATOR);
    }

    private static void listarActividades(List<Actividad> actividades) {
        for (int i = 0; i < actividades.size(); i++) {
            Actividad actividad = actividades.get(i);
            escrbir((i + 1) + ". " + actividad.getNombre());
        }

        System.out.println(SEPARATOR);
    }

    private static void escrbir(Object parrafo) {
        System.out.println(parrafo);
    }

    private static void mostrarTitulo(String titulo) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println();
        System.out.println(SEPARATOR);
        System.out.println("\t\t" + titulo);
        System.out.println(SEPARATOR);
    }

    private static String leerOpcion() {
        return leerOpcion("Ingrese su opción");
    }

    private static String leerOpcion(String lectura) {
        System.out.println(SEPARATOR);
        System.out.print(lectura + " --> ");

        return input.nextLine();
    }
}
