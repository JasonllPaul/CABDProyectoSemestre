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
public class Modelo_Hotel extends Modelo{
    private int hotId;
    private int ciuId;
    private int empDni;
    private int catId;
    private String hotNombre;
    private String hotDireccion;

    public Modelo_Hotel(int hotId){
        this.hotId = hotId;
    }
    
    public Modelo_Hotel(int hotId, int ciuId, int empDni, int catId, String hotNombre, String direccion) {
        this.hotId = hotId;
        this.ciuId = ciuId;
        this.empDni = empDni;
        this.catId = catId;
        this.hotNombre = hotNombre;
        this.hotDireccion = direccion;
    }

    public int getHotId() {
        return hotId;
    }

    public void setHotId(int hotId) {
        this.hotId = hotId;
    }

    public int getCiuId() {
        return ciuId;
    }

    public void setCiuId(int ciuId) {
        this.ciuId = ciuId;
    }

    public int getEmpDni() {
        return empDni;
    }

    public void setEmpDni(int empDni) {
        this.empDni = empDni;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getHotNombre() {
        return hotNombre;
    }

    public void setHotNombre(String hotNombre) {
        this.hotNombre = hotNombre;
    }

    public String getHotDireccion() {
        return hotDireccion;
    }

    public void setHotDireccion(String hotDireccion) {
        this.hotDireccion = hotDireccion;
    }

    @Override
    public String registrar() {
        return "JasonllPaul";
    }
    
    
    
    
}
