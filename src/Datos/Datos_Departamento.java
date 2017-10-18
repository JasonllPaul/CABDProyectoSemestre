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
public class Datos_Departamento extends Datos {

    public Datos_Departamento(Conexion conexion){
        this.dt = conexion;
    }
    
    @Override
    public ResultSet consultarTodo() {
        String consulta = "select * from DEPARTAMENTO ";
        ResultSet result = this.dt.ejecutarSELECT(consulta);
        return result;
    }

}
