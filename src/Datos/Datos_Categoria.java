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
public class Datos_Categoria extends Datos {

    Conexion dt = new Conexion();
    
    public Datos_Categoria(){
        this.nombreTabla = "CATEGORIA";
    }
    
    @Override
    public ResultSet consultarTodo() {
        String consulta = "select * from "+nombreTabla;
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

}
