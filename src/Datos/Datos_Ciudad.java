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
public class Datos_Ciudad extends Datos {

    public Datos_Ciudad(Conexion conexion) {
        this.dt = conexion;
    }

    @Override
    public ResultSet consultarTodo() {
        String consulta = "select * from CIUDAD ORDER BY CIUNOMBRE ";
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

    public ResultSet consultarCiudadesDepartamento(int depid) {
        String consulta = "select * from ciudad\n"
                + "where DEPID = " + depid + "\n"
                + "order by ciuNombre";
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

    public ResultSet consultarCiuId(String nombre) {
        String consulta = "select ciuid\n"
                + "from ciudad\n"
                + "where CIUNOMBRE = '"+nombre+"'";
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

}
