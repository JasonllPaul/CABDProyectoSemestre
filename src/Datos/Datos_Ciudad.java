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
public class Datos_Ciudad {

    Conexion dt = new Conexion();

    public ResultSet consultarTodasCiudades() {
        String consulta = "select * from CIUDAD ORDER BY CIUNOMBRE ";
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

    public ResultSet consultarCiudadesDepartamento(int depid) {
        String consulta = "select * from ciudad\n"
                + "where DEPID = "+depid+"\n"
                + "order by ciuNombre";
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

}
