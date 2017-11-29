/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Modelo_Habitacion;
import java.sql.ResultSet;

/**
 *
 * @author JasonllPaul
 */
public class Datos_Habitacion extends Datos {

    public Datos_Habitacion(Conexion conexion) {
        this.dt = conexion;
    }

    public ResultSet consultarHabitacion(String hab, int hotid) {
        String consulta = "select * from habitacion where habitacion.HAB = '" + hab + "' AND habitacion.HOTID = " + hotid + " ORDER BY HABID";
        ResultSet result = this.dt.ejecutarSELECT(consulta);
        return result;
    }

    public int actualizarEstadoHabitacion(Modelo_Habitacion h) {
        String consulta;
        consulta = "UPDATE HABITACION SET HAB = '%s' WHERE HABID = %s";
        consulta = String.format(consulta, h.getHab(), h.getHabid());
        System.out.println("sql: " + consulta);
        int result = dt.ejecutarDML(consulta);
        System.out.println("int " + result);
        return result;
    }

    public ResultSet consultarPrecio(int habid) {
        String consulta = "select TIPPRECIO as \"Precio\"\n"
                + "from TIPO_HABITACION\n"
                + "where TIPID = (select tipid from habitacion where habid = " + habid + ")";

        System.out.println("sql: " + consulta);
        ResultSet result = this.dt.ejecutarSELECT(consulta);
        return result;
    }

    public ResultSet consultarHabitaciones(int resid) {
        String consulta = "select HABID\n"
                + "from comprende\n"
                + "where resid = " + resid;

        System.out.println("sql: " + consulta);
        ResultSet result = this.dt.ejecutarSELECT(consulta);
        return result;
    }

    @Override
    public ResultSet consultarTodo() {
        String consulta = "select * from HABITACION";
        ResultSet result = this.dt.ejecutarSELECT(consulta);
        return result;
    }

}
