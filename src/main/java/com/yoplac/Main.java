package com.yoplac;

import com.yoplac.model.Empresa;
import com.yoplac.model.Periodo;
import com.yoplac.model.Registro;
import com.yoplac.system.SystemLogic;
import com.yoplac.system.SystemUI;

public class Main {
    public static void main(String[] args) {
        Empresa empresa = SystemLogic.EMPRESA;

        Registro presupuestoBase = empresa.getPresupuestoBase();

        SystemUI.generarReporte(presupuestoBase, Periodo.MENSUAL);
        SystemUI.generarReporte(presupuestoBase, Periodo.TRIMESTRAL);
        SystemUI.generarReporte(presupuestoBase, Periodo.SEMESTRAL);
        SystemUI.generarReporte(presupuestoBase, Periodo.ANUAL);

        Registro registro = SystemUI.menuIngresarDatos(empresa.getPresupuestoBase());

        empresa.setRegistro(registro);

        SystemUI.generarReporte(registro, Periodo.MENSUAL);
        SystemUI.generarReporte(registro, Periodo.TRIMESTRAL);
        SystemUI.generarReporte(registro, Periodo.SEMESTRAL);
        SystemUI.generarReporte(registro, Periodo.ANUAL);
    }
}