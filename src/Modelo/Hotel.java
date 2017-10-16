/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Datos.Conexion;
import java.sql.ResultSet;
import java.util.Locale;

/**
 *
 * @author JasonllPaul
 */
public class Hotel {
    Conexion dt=new Conexion();

    public int insertarDatos( int NIT, String NOMBRE, String DIRECCION, int FECHAFUNDACION ){
        
        String consulta;
        int result;
        consulta = "insert into HOTEL (NIT,NOMBRE,DIRECCION,FECHAFUNDACION) VALUES (%s, '%s', '%s', %s)";
        consulta = String.format(consulta,NIT,NOMBRE,DIRECCION,FECHAFUNDACION);
        //Envío la consulta a la capa de acceso a datos
        result = dt.ejecutarDML(consulta);
        return result;
        
    }
    
    public ResultSet consultarTodos(){
    
    String consulta="select * from hotel order by hotId";
    
     ResultSet result = dt.ejecutarSELECT(consulta);
    
     return result;
    }
    
    
    public int actualizarRegistro(int n,String nom,String dir,int fecha){
        String consulta="";

        if(nom.length()>0 && dir.length()>0 && fecha>0){
        
        consulta="update EMPRESADISCOGRAFICA SET NOMBRE = '%s',DIRECCION = '%s',FECHAFUNDACION = %s where NIT = %s ";
        
        }
        else{
            
            if(nom.length()>0 && dir.length()==0 && fecha>0){
        
            consulta="update EMPRESADISCOGRAFICA SET NOMBRE = '%s',FECHAFUNDACION = %s where NIT = %s ";
            consulta = String.format(consulta,nom,fecha,n);
            

            }
            if(nom.length()==0 && dir.length()>0 && fecha>0){
        
            consulta="update EMPRESADISCOGRAFICA SET DIRECCION = '%s',FECHAFUNDACION = %s where NIT = %s ";
            consulta = String.format(consulta,dir,fecha,n);

            }
            
            if(nom.length()==0 && dir.length()==0 && fecha>0){

            consulta="update EMPRESADISCOGRAFICA SET FECHAFUNDACION = %s where NIT = %s ";
            consulta = String.format(consulta,fecha,n);

            }
            
            if(nom.length()==0 && dir.length()>0 && fecha==0){
        
            consulta="update EMPRESADISCOGRAFICA SET DIRECCION = '%s' where NIT = %s ";
            consulta = String.format(consulta,dir,n);
            }
            
            if( nom.length()>0 && dir.length()==0 && fecha==0){
            consulta="update EMPRESADISCOGRAFICA SET NOMBRE = '%s' where NIT = %s ";
            consulta = String.format(consulta,nom,n);
            }
            

        }
               
        int result = dt.ejecutarDML(consulta);
        
        return result;
    }
    
    
    public ResultSet buscarRegistro(int nit){
        
        String consulta="select * from EMPRESADISCOGRAFICA where nit = %s";
     consulta = String.format(consulta,nit);
     ResultSet result = dt.ejecutarSELECT(consulta);
    
     return result;
    }
    
    public ResultSet returnNit(){
    
        String consulta="select nit,nombre from empresadiscografica";
        consulta=String.format(consulta);
        ResultSet result =dt.ejecutarSELECT(consulta);
        return result;
    }
    
    public int comprobarExistencia(){
        
        String consulta="select nit from empresadiscografica";
     consulta = String.format(consulta);
     int result = dt.ejecutarDML(consulta);
    
     return result;
    }
    
}
