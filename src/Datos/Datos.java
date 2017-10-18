package Datos;

import java.sql.ResultSet;

/**
 *
 * @author JasonllPaul-PC
 */
public abstract class Datos {

    public Conexion dt;
    public String nombreTabla;

    public abstract ResultSet consultarTodo();

    public Conexion getConexion() {
        return this.dt;
    }

}
