package Datos;

import Modelo.Modelo_Comprende;
import java.sql.ResultSet;

/**
 *
 * @author JasonllPaul
 */
public class Datos_Comprende extends Datos {

    public Datos_Comprende(Conexion conexion) {
        this.dt = conexion;
    }

    @Override
    public ResultSet consultarTodo() {
        String consulta = "select * from COMPRENDE";
        ResultSet result = this.dt.ejecutarSELECT(consulta);
        return result;
    }

     public int insertar(Modelo_Comprende comprende){
        String consulta;
        int result;
        consulta= "INSERT INTO COMPRENDE (RESID, HABID) VALUES ( %s , %s )";
        
        consulta=String.format(consulta,comprende.getResid(),comprende.getHabid());
        System.out.println("sql: "+consulta);
        result=dt.ejecutarDML(consulta);
        return result;
    }
    
}
