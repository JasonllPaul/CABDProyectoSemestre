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
public class Modelo_Reserva {
    private int resId;
    private int cliDni;
    private int hotId;
    private int resPersonas;
    private Date resFechaInicio;
    private Date resFechaFin;
    private float restotal;

    public Modelo_Reserva(int resId, int cliDni, int hotId, int resPersonas, Date resFechaInicio) {
        this.resId = resId;
        this.cliDni = cliDni;
        this.hotId = hotId;
        this.resPersonas = resPersonas;
        this.resFechaInicio = resFechaInicio;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getCliDni() {
        return cliDni;
    }

    public void setCliDni(int cliDni) {
        this.cliDni = cliDni;
    }

    public int getHotId() {
        return hotId;
    }

    public void setHotId(int hotId) {
        this.hotId = hotId;
    }

    public int getResPersonas() {
        return resPersonas;
    }

    public void setResPersonas(int resPersonas) {
        this.resPersonas = resPersonas;
    }

    public Date getResFechaInicio() {
        return resFechaInicio;
    }

    public void setResFechaInicio(Date resFechaInicio) {
        this.resFechaInicio = resFechaInicio;
    }

    public Date getResFechaFin() {
        return resFechaFin;
    }

    public void setResFechaFin(Date resFechaFin) {
        this.resFechaFin = resFechaFin;
    }

    public float getRestotal() {
        return restotal;
    }

    public void setRestotal(float restotal) {
        this.restotal = restotal;
    }

    
    
    
    
}
