/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author ALBERT
 */
public class Modelo_Empleado {
    private int empDni;
    private int hotId;
    private String empNombre;
    private String empDireccion;
    private Date empFechaInicio;
    private Date empFechaFin;

    public Modelo_Empleado(int empDni, int hotId, String empNombre, String empDireccion, Date empFechaInicio, Date empFechaFin) {
        this.empDni = empDni;
        this.hotId = hotId;
        this.empNombre = empNombre;
        this.empDireccion = empDireccion;
        this.empFechaInicio = empFechaInicio;
        this.empFechaFin = empFechaFin;
    }

    public int getEmpDni() {
        return empDni;
    }

    public void setEmpDni(int empDni) {
        this.empDni = empDni;
    }

    public int getHotId() {
        return hotId;
    }

    public void setHotId(int hotId) {
        this.hotId = hotId;
    }

    public String getEmpNombre() {
        return empNombre;
    }

    public void setEmpNombre(String empNombre) {
        this.empNombre = empNombre;
    }

    public String getEmpDireccion() {
        return empDireccion;
    }

    public void setEmpDireccion(String empDireccion) {
        this.empDireccion = empDireccion;
    }

    public Date getEmpFechaInicio() {
        return empFechaInicio;
    }

    public void setEmpFechaInicio(Date empFechaInicio) {
        this.empFechaInicio = empFechaInicio;
    }

    public Date getEmpFechaFin() {
        return empFechaFin;
    }

    public void setEmpFechaFin(Date empFechaFin) {
        this.empFechaFin = empFechaFin;
    }

   
    
}
