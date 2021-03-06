/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Modelo_Reserva;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 *
 * @author ALBERT
 */
public class Datos_Reserva extends Datos{
    
    public Datos_Reserva(Conexion conexion){
        this.dt = conexion;
    }
    
    public int insertarReservas(Modelo_Reserva res){
        String consulta;
        int result;
        consulta= "insert into RESERVA (RESID,CLIDNI,HOTID,RESPERSONAS,RESFECHAINICIO,RESFECHAFIN,RESTOTAL) "
                + "VALUES (%s, %s, %s, %s,TO_DATE('%s', 'YYYY-MM-DD HH24:MI:SS'),TO_DATE('%s', 'YYYY-MM-DD HH24:MI:SS'), %s)";
        
        String fechaInicio = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(res.getResFechaInicio());
        String fechaFin = "";
        if(res.getResFechaFin() != null){
        fechaFin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(res.getResFechaFin());}
        consulta=String.format(consulta,res.getResId(),res.getCliDni(),res.getHotId(),
                res.getResPersonas(),fechaInicio,fechaFin,res.getRestotal());
        System.out.println("sql: "+consulta);
        result=dt.ejecutarDML(consulta);
        return result;
    }
    
    
    public ResultSet selectSequence(){
        String consulta="select SEQ_RESERVA.NEXTVAL from dual";
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }
    
    @Override
    public ResultSet consultarTodo(){
        String consulta="select * from RESERVA ORDER BY RESID ";
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }
    
    public int actualizarRegistroHoteles(Modelo_Reserva res){
        String consulta="";

        if(res.getResId()>0 && res.getCliDni()>0 && res.getHotId()>0 && res.getResPersonas()>0 && 
           res.getResFechaInicio().toString().length()>0 && res.getResFechaFin().toString().length()>0 && res.getRestotal()>0){ 
        consulta="update RESERVA SET CLIDNI = %s, HOTID = %s, CATID = %s, RESPERSONAS = %s, RESFECHAINICIO = '%s',RESFECHAFIN = '%s', RESTOTAL = %s where RESID = %s ";
        }
        
        else{
            
           if(res.getResId()>0 && res.getCliDni()>0 && res.getHotId()>0 && res.getResPersonas()>0 && 
              res.getResFechaInicio().toString().length()>0 && res.getResFechaFin().toString().length()>0 && res.getRestotal()==0){
               consulta="update RESERVA SET CLIDNI = %s, HOTID = %s, CATID = %s, RESPERSONAS = %s, RESFECHAINICIO = '%s',RESFECHAFIN = '%s' where RESID = %s ";
               consulta = String.format(consulta,res.getResId(),res.getCliDni(),res.getHotId(),
                          res.getResPersonas(),res.getResFechaInicio(),res.getResFechaFin());
            }
            if(res.getResId()>0 && res.getCliDni()>0 && res.getHotId()>0 && res.getResPersonas()>0 && 
              res.getResFechaInicio().toString().length()>0 && res.getResFechaFin().toString().length()==0 && res.getRestotal()>0){
               consulta="update RESERVA SET CLIDNI = %s, HOTID = %s, CATID = %s, RESPERSONAS = %s, RESFECHAINICIO = '%s',RESTOTAL = %s where RESID = %s ";
               consulta = String.format(consulta,res.getResId(),res.getCliDni(),res.getHotId(),
                          res.getResPersonas(),res.getResFechaInicio(),res.getRestotal());
            }
            
            
            if(res.getResId()>0 && res.getCliDni()>0 && res.getHotId()>0 && res.getResPersonas()>0 && 
              res.getResFechaInicio().toString().length()>0 && res.getResFechaFin().toString().length()==0 && res.getRestotal()==0){
               consulta="update RESERVA SET CLIDNI = %s, HOTID = %s, CATID = %s, RESPERSONAS = %s, RESFECHAINICIO = '%s' where RESID = %s ";
               consulta = String.format(consulta,res.getResId(),res.getCliDni(),res.getHotId(),
                          res.getResPersonas(),res.getResFechaInicio());
            }
        }
            
           
        int result = dt.ejecutarDML(consulta);
        
        return result;
    }
    
    public int checkout(Modelo_Reserva res){
        String consulta;
        consulta = "UPDATE RESERVA SET RESESTADO = '%s' WHERE RESID = %s";
        consulta = String.format(consulta,res.getResestado(),res.getResId());
        int result = dt.ejecutarDML(consulta);
        return result;
    }
    
    public int insertFechaFin(Modelo_Reserva res){
        String consulta;
        consulta = "UPDATE RESERVA SET RESFECHAFIN = TO_DATE('%s', 'YYYY-MM-DD HH24:MI:SS') WHERE RESID = %s";
        String fechaFin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(res.getResFechaFin());
        consulta = String.format(consulta,fechaFin,res.getResId());
        System.out.println("sql: "+consulta);
        int result = dt.ejecutarDML(consulta);
        return result;
    }
    
    public ResultSet buscarRegistroReservas(Modelo_Reserva res){
        String consulta="select * from RESERVA where RESID = %s";
        consulta = String.format(consulta,res.getResId());
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }
    
    public ResultSet retornarResId(){
        String consulta="select RESID,CLIDNI from RESERVA";
        consulta=String.format(consulta);
        ResultSet result =dt.ejecutarSELECT(consulta);
        return result;
    }
    
    public int comprobarExistenciaReservas(){
        String consulta="select RESID from RESERVA";
        consulta = String.format(consulta);
        int result = dt.ejecutarDML(consulta);
        return result;
    }
    
}
