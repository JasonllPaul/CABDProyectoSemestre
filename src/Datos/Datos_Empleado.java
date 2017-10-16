/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Modelo_Empleado;
import java.sql.ResultSet;

/**
 *
 * @author ALBERT
 */
public class Datos_Empleado {
     Conexion dt=new Conexion();
    //Modelo_Cliente cl=new Modelo_Cliente();
    
    public int insertarEmpleados(Modelo_Empleado emp){
        String consulta;
        int result;
        consulta= "insert into EMPLEADO (EMPDNI,HOTID,EMPNOMBRE,EMPDIRECCION,EMPFECHAINICIO,EMPFECHAFIN) "
                + "VALUES (%s, %s, '%s', '%s', '%s', '%s')";
        consulta=String.format(consulta,emp.getEmpDni(),emp.getHotId(),emp.getEmpNombre(),
                emp.getEmpDireccion(),emp.getEmpFechaInicio(),emp.getEmpFechaFin());
        result=dt.ejecutarDML(consulta);
        return result;
    }
    
    public ResultSet consultarTodosEmpleados(){
        String consulta="select * from EMPLEADO ORDER BY EMPDNI ";
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }
    
    public int actualizarRegistroEmpleados(Modelo_Empleado emp){
        String consulta="";

        if(emp.getHotId()>0 && emp.getEmpNombre().length()>0 && emp.getEmpDireccion().length()>0
                && emp.getEmpFechaInicio().toString().length()>0 && emp.getEmpFechaFin().toString().length()>0){ 
        consulta="update EMPLEADO SET HOTID = %s,EMPNOMBRE = '%s', EMPDIRECCION = '%s', EMPFECHAINICIO = '%s', EMPEFECHAFIN = '%s' where EMPDNI = %s ";
        }
        else{
            
            if(emp.getHotId()>0 && emp.getEmpNombre().length()>0 && emp.getEmpDireccion().length()>0
                && emp.getEmpFechaInicio().toString().length()>0 && emp.getEmpFechaFin().toString().length()==0){
                consulta="update EMPLEADO SET HOTID = %s, EMPNOMBRE = '%s', EMPDIRECCION = '%s', EMPFECHAINICIO = '%s' where EMPDNI = %s ";
                consulta = String.format(consulta,emp.getHotId(),emp.getEmpNombre(),
                           emp.getEmpDireccion(),emp.getEmpFechaInicio(),emp.getEmpDni());
            }
            if(emp.getHotId()>0 && emp.getEmpNombre().length()>0 && emp.getEmpDireccion().length()==0
                && emp.getEmpFechaInicio().toString().length()>0 && emp.getEmpFechaFin().toString().length()==0){
                consulta="update EMPLEADO SET HOTID = %s,EMPNOMBRE = '%s', EMPFECHAINICIO = '%s' where EMPDNI = %s ";
                consulta = String.format(consulta,emp.getHotId(),emp.getEmpNombre(),
                           emp.getEmpFechaInicio(),emp.getEmpDni());
            }
            
            if(emp.getHotId()>0 && emp.getEmpNombre().length()>0 && emp.getEmpDireccion().length()==0
                && emp.getEmpFechaInicio().toString().length()>0 && emp.getEmpFechaFin().toString().length()>0){
                consulta="update EMPLEADO SET HOTID = %s,EMPNOMBRE = '%s', EMPFECHAINICIO = '%s',EMPEFECHAFIN = '%s' where EMPDNI = %s ";
                consulta = String.format(consulta,emp.getHotId(),emp.getEmpNombre(),
                           emp.getEmpFechaInicio(),emp.getEmpFechaFin(),emp.getEmpDni());
            }
            
             if(emp.getHotId()==0 && emp.getEmpNombre().length()>0 && emp.getEmpDireccion().length()>0
                && emp.getEmpFechaInicio().toString().length()>0 && emp.getEmpFechaFin().toString().length()>0){
                consulta="update EMPLEADO SET EMPNOMBRE = '%s', EMPDIRECCION = '%s', EMPFECHAINICIO = '%s',EMPEFECHAFIN = '%s' where EMPDNI = %s ";
                consulta = String.format(consulta,emp.getEmpNombre(),
                           emp.getEmpDireccion(),emp.getEmpFechaInicio(),emp.getEmpFechaFin(),emp.getEmpDni());
            }
            
              if(emp.getHotId()==0 && emp.getEmpNombre().length()>0 && emp.getEmpDireccion().length()==0
                && emp.getEmpFechaInicio().toString().length()>0 && emp.getEmpFechaFin().toString().length()>0){
                consulta="update EMPLEADO SET  EMPNOMBRE = '%s', EMPFECHAINICIO = '%s',EMPEFECHAFIN = '%s' where EMPDNI = %s ";
                consulta = String.format(consulta,emp.getEmpNombre(),
                           emp.getEmpFechaInicio(),emp.getEmpFechaFin(),emp.getEmpDni());
            }
              
            if(emp.getHotId()==0 && emp.getEmpNombre().length()>0 && emp.getEmpDireccion().length()>0
                && emp.getEmpFechaInicio().toString().length()>0 && emp.getEmpFechaFin().toString().length()==0){
                consulta="update EMPLEADO SET EMPNOMBRE = '%s', EMPDIRECCION = '%s', EMPFECHAINICIO = '%s'where EMPDNI = %s ";
                consulta = String.format(consulta,emp.getEmpNombre(),
                           emp.getEmpDireccion(),emp.getEmpFechaInicio(),emp.getEmpDni());
            }  
            
            if(emp.getHotId()==0 && emp.getEmpNombre().length()>0 && emp.getEmpDireccion().length()==0
                && emp.getEmpFechaInicio().toString().length()>0 && emp.getEmpFechaFin().toString().length()==0){
                consulta="update EMPLEADO SET EMPNOMBRE = '%s', EMPFECHAINICIO = '%s' where EMPDNI = %s ";
                consulta = String.format(consulta,emp.getEmpNombre(),
                           emp.getEmpFechaInicio(),emp.getEmpDni());
            }
              
             
    
        }
               
        int result = dt.ejecutarDML(consulta);
        
        return result;
    }
    
    public ResultSet buscarRegistroEmpleados(Modelo_Empleado emp){
        String consulta="select * from EMPLEADO where EMPDNI = %s";
        consulta = String.format(consulta,emp.getEmpDni());
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }
    
    public ResultSet retornarEmpDni(){
        String consulta="select EMPDNI,EMPNOMBRE from EMPLEADO";
        consulta=String.format(consulta);
        ResultSet result =dt.ejecutarSELECT(consulta);
        return result;
    }
    
    public int comprobarExistenciaEmpleados(){
        String consulta="select EMPDNI from EMPLEADO";
        consulta = String.format(consulta);
        int result = dt.ejecutarDML(consulta);
        return result;
    }
    
}
