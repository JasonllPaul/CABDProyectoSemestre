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
    
    public Datos_Categoria(Conexion conexion){
        this.nombreTabla = "CATEGORIA";
        this.dt = conexion;
    }
    
    @Override
    public ResultSet consultarTodo() {
        String consulta = "select * from "+nombreTabla;
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

}
