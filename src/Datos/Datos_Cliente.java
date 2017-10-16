/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Datos.Conexion;
import java.sql.ResultSet;
import java.util.Locale;
import Modelo.*;
/**
 *
 * @author ALBERT
 */
public class Datos_Cliente {
    
    
    Conexion dt=new Conexion();
    //Modelo_Cliente cl=new Modelo_Cliente();
    
    public int insertarClientes(Modelo_Cliente cl){
        String consulta;
        int result;
        //consulta= "insert into CLIENTE (EMPDNI,HOTID,EMPNOMBRE,EMPDIRECCION,EMPFECHAINICIO,EMPFECHAFIN) VALUES (%s, %s, '%s', '%s', '%s', '%s')";
        consulta= "insert into CLIENTE (CLIDNI,CLINOMBRE,CLIAPELLIDO) VALUES (%s,'%s', '%s')";
        consulta=String.format(consulta, cl.getCliDni(),cl.getCliNombre(),cl.getCliApellido());
        result=dt.ejecutarDML(consulta);
        return result;
    }
    
    public ResultSet consultarTodosClientes(){
        String consulta="select * from CLIENTE ORDER BY CLIDNI ";
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }
    
    public int actualizarRegistroClientes(Modelo_Cliente cl){
        String consulta="";

        if(cl.getCliNombre().length()>0 && cl.getCliApellido().length()>0){
        
        consulta="update CLIENTE SET CLINOMBRE = '%s',CLIAPELLIDO = '%s' where CLIDNI = %s ";
        
        }
        /*else{
            
            if(cl.getCliNombre().length()>0 && cl.getCliApellido().length()==0){
                consulta="update CLIENTE SET CLINOMBRE = '%s' where CLIDNI = %s ";
                consulta = String.format(consulta,cl.getCliNombre(),cl.getCliDni());
            }
            if(cl.getCliNombre().length()==0 && cl.getCliApellido().length()>0){
                consulta="update CLIENTE SET CLIAPELLIDO = '%s' where CLIDNI = %s ";
                consulta = String.format(consulta,cl.getCliApellido(),cl.getCliDni());
            }
    
        }*/  //se comenta debido a que estos campos nunca seran nulos
               
        int result = dt.ejecutarDML(consulta);
        
        return result;
    }
    
    public ResultSet buscarRegistroClientes(Modelo_Cliente cl){
        String consulta="select * from CLIENTE where CLIDNI = %s";
        consulta = String.format(consulta,cl.getCliDni());
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }
    
    public ResultSet retornarCliDni(){
        String consulta="select CLIDNI,CLINOMBRE from CLIENTE";
        consulta=String.format(consulta);
        ResultSet result =dt.ejecutarSELECT(consulta);
        return result;
    }
    
    public int comprobarExistenciaClientes(){
        String consulta="select CLIDNI from CLIENTE";
        consulta = String.format(consulta);
        int result = dt.ejecutarDML(consulta);
        return result;
    }
    
    
}
