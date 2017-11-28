/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.ResultSet;

/**
 *
 * @author JasonllPaul
 */
public class Datos_Habitacion extends Datos {

    public Datos_Habitacion(Conexion conexion) {
        this.dt = conexion;
    }

    public ResultSet consultarHabitacion(String hab,int hotid) {
        String consulta = "select * from habitacion where habitacion.HAB = '"+hab+"' AND habitacion.HOTID = "+hotid+" ORDER BY HABID";
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
