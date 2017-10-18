/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Modelo.Modelo_Hotel;
import java.sql.ResultSet;

/**
 *
 * @author ALBERT
 */
public class Datos_Hotel extends Datos{

    public Datos_Hotel(Conexion conexion) {
        this.dt = conexion;
    }

    
    
    
    public int insertarHoteles(Modelo_Hotel hot) {
        String consulta;
        int result;
        consulta = "insert into HOTEL (HOTID,CIUID,EMPDNI,CATID,HOTNOMBRE,HOTDIRECCION) "
                + "VALUES (%s, %s, %s, %s, '%s', '%s')";
        consulta = String.format(consulta, hot.getHotId(), hot.getCiuId(), hot.getEmpDni(),
                hot.getCatId(), hot.getHotNombre(), hot.getHotDireccion());
        result = dt.ejecutarDML(consulta);
        return result;
    }

    @Override
    public ResultSet consultarTodo() {
        String consulta = "select * from HOTEL ORDER BY HOTID ";
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

    public int actualizarRegistroHoteles(Modelo_Hotel hot) {
        String consulta = "";

        if (hot.getHotId() > 0 && hot.getCiuId() > 0 && hot.getEmpDni() > 0 && hot.getCatId() > 0
                && hot.getHotNombre().length() > 0 && hot.getHotDireccion().length() > 0) {
            consulta = "update HOTEL SET CIUID = %s,EMPDNI = '%s', CATID = %s, HOTNOMBRE = '%s', HOTDIRECCION = '%s' where HOTID = %s ";
        }

        int result = dt.ejecutarDML(consulta);

        return result;
    }

    public ResultSet buscarRegistroHoteles(Modelo_Hotel hot) {
        String consulta = "select * from HOTEL where HOTID = %s";
        consulta = String.format(consulta, hot.getHotId());
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

    public ResultSet retornarHotId() {
        String consulta = "select HOTID,HOTNOMBRE from HOTEL";
        consulta = String.format(consulta);
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

    public int comprobarExistenciaHoteles() {
        String consulta = "select HOTID from HOTEL";
        consulta = String.format(consulta);
        int result = dt.ejecutarDML(consulta);
        return result;
    }

    public int borrarHotel(int id) {
        String consulta = "delete from hotel\n"
                + "where hotId = " + id;
        consulta = String.format(consulta);
        int result = dt.ejecutarDML(consulta);
        return result;
    }

}
