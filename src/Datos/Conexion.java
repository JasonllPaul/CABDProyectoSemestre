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
          con = DriverManager.getConnection(cadenaConexion, "system","oracle");
        
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
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
     * @param consulta cadena que contiene la consulta
     * @return  regresa un entero que indica si fue hecha o no la consulta
     *****************************************************************/
    public int ejecutarDML(String consulta)
    {
        int filasAfectadas = 0;
        try
        {
            Statement st = con.createStatement();
            filasAfectadas = st.executeUpdate(consulta);
            return filasAfectadas;
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
            return 0;     
        
        }    
    
    }
    
    /****************************************************************
     * el método ejecutarSELECT recibe una consulta que fue enviada desde la capa de la logica.
     * este metodo le permite ejecutar solo consultas SELECT. al final este
     * método devuelve un conjunto de varios datos, un ResultSet.(recuerde que uns elect devulve varios 
     * campos por eso todos esos campos se guardan en un ResultSet
     *
     * @param consulta cadena que contiene la consulta
     * @return  retorna un dato ResultSet que contiene la información del SELECT
     *****************************************************************/
    public ResultSet ejecutarSELECT(String consulta)
    {
        ResultSet res;
        try
        {
            Statement st = con.createStatement();
            res = st.executeQuery(consulta);
            return res;
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
            return null;           
        }    
    
    }
    
}
