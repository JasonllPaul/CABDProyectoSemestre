package Datos;

import Modelo.Modelo_Reserva;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

/**
 *
 * @author JasonllPaul
 */
public class Datos_Consultas extends Datos {

    public Datos_Consultas(Conexion conexion) {
        this.dt = conexion;
    }

    public ResultSet joinReservaHotel(int id) {
        String consulta = "select resid as \"Número de reserva\", hotnombre as \"Hotel\", resfechainicio as \"Fecha de Inicio\", resfechafin as \"Fecha Fin\", restotal as \"Total\"\n"
                + "from reserva inner join hotel on reserva.hotid = hotel.hotid\n"
                + "where reserva.clidni = %s and reserva.resestado = 'Pendiente'";
        consulta = String.format(consulta,id);
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

    public ResultSet joinReservaComprende(int resid) {
        String consulta = "select count(*) as \"Número de habitaciones\" from comprende inner join reserva on comprende.resid = reserva.resid where reserva.resid = %s";
        consulta = String.format(consulta,resid);
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }
    
    public ResultSet joinReservaComprendeHabId(int resid) {
        String consulta = "select habid from comprende inner join reserva on comprende.resid = reserva.resid where reserva.resid = %s";
        consulta = String.format(consulta,resid);
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }
    
    @Override
    public ResultSet consultarTodo() {
        String consulta = "select * from Hotel";
        ResultSet result = dt.ejecutarSELECT(consulta);
        return result;
    }

}
