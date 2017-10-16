/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author ALBERT
 */
public class Modelo_Cliente {
    private int cliDni;
    private String cliNombre;
    private String cliApellido;

   // public Modelo_Cliente() {
    //}
    
    public Modelo_Cliente(int cliDni, String cliNombre, String cliApellido) {
        this.cliDni = cliDni;
        this.cliNombre = cliNombre;
        this.cliApellido = cliApellido;
    }

    public int getCliDni() {
        return cliDni;
    }

    public void setCliDni(int cliDni) {
        this.cliDni = cliDni;
    }

    public String getCliNombre() {
        return cliNombre;
    }

    public void setCliNombre(String cliNombre) {
        this.cliNombre = cliNombre;
    }

    public String getCliApellido() {
        return cliApellido;
    }

    public void setCliApellido(String cliApellido) {
        this.cliApellido = cliApellido;
    }
    
    
    
    
    
}
