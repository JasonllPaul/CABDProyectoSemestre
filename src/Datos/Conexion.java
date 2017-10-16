/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;


import java.sql.*;

/**
 *
 * @author JasonllPaul
 */
public class Conexion {
    private String cadenaConexion;
    private Connection con;
    
    private void inicializarConexion()
    {
        try
        {
          cadenaConexion = "jdbc:oracle:thin:@localhost:1521:xe";
          con = DriverManager.getConnection(cadenaConexion, "CABDPROYECTO","oracle");
          System.out.println(con);
          System.out.println("se conecto a la base de datos correctamente");
        }
        catch(Exception ex)
        {
            System.out.println("No se pudo cargar el puente JDBC-ODBC "+ex.getMessage());
        }    
    }
    public Conexion()
    {
        inicializarConexion();
    
    }
    
    
    /****************************************************************
     * el método ejecutarDML recibe una consulta que fue enviada desde la capa de la logica.
     * este metodo le permite ejecutar un insert, delete , update. al final este
     * método devuelve un valor entero. 
     * *****************************************************************/
    public int ejecutarDML(String consulta)
    {
        int filasAfectadas = 0;
        try
        {
            Statement st = con.createStatement();
            filasAfectadas = st.executeUpdate(consulta);
            return filasAfectadas;
        }
        catch(Exception ex)
        {
            return 0;     
        
        }    
    
    }
    
    /****************************************************************
     * el método ejecutarSELECT recibe una consulta que fue enviada desde la capa de la logica.
     * este metodo le permite ejecutar solo consultas SELECT. al final este
     * método devuelve un conjunto de varios datos, un ResultSet.(recuerde que uns elect devulve varios 
     * campos por eso todos esos campos se guardan en un ResultSet
     * *****************************************************************/
    public ResultSet ejecutarSELECT(String consulta)
    {
        ResultSet res;
        try
        {
            Statement st = con.createStatement();
            res = st.executeQuery(consulta);
            return res;
        }
        catch(Exception ex)
        {
            return null;           
        }    
    
    }
    
}
