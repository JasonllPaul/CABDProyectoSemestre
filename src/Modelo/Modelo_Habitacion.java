package Modelo;

/**
 * @author JasonllPaul
 */
public class Modelo_Habitacion extends Modelo{
    private int habid;
    private int hotid;
    private int tipid;
    private String hab;
    private String habdescripcion;
    private int habnumerocamas;
    private int numerotv;
    private String habubicacion;
    private String habaireacondicionado;

    public Modelo_Habitacion(int habid, int hotid, int tipid, String hab, String habdescripcion, int habnumerocamas, int numerotv, String habubicacion, String habaireacondicionado) {
        this.habid = habid;
        this.hotid = hotid;
        this.tipid = tipid;
        this.hab = hab;
        this.habdescripcion = habdescripcion;
        this.habnumerocamas = habnumerocamas;
        this.numerotv = numerotv;
        this.habubicacion = habubicacion;
        this.habaireacondicionado = habaireacondicionado;
    }

    
    
    public int getHabid() {
        return habid;
    }

    public void setHabid(int habid) {
        this.habid = habid;
    }

    public int getHotid() {
        return hotid;
    }

    public void setHotid(int hotid) {
        this.hotid = hotid;
    }

    public int getTipid() {
        return tipid;
    }

    public void setTipid(int tipid) {
        this.tipid = tipid;
    }

    public String getHab() {
        return hab;
    }

    public void setHab(String hab) {
        this.hab = hab;
    }

    public String getHabdescripcion() {
        return habdescripcion;
    }

    public void setHabdescripcion(String habdescripcion) {
        this.habdescripcion = habdescripcion;
    }

    public int getHabnumerocamas() {
        return habnumerocamas;
    }

    public void setHabnumerocamas(int habnumerocamas) {
        this.habnumerocamas = habnumerocamas;
    }

    public int getNumerotv() {
        return numerotv;
    }

    public void setNumerotv(int numerotv) {
        this.numerotv = numerotv;
    }

    public String getHabubicacion() {
        return habubicacion;
    }

    public void setHabubicacion(String habubicacion) {
        this.habubicacion = habubicacion;
    }

    public String getHabaireacondicionado() {
        return habaireacondicionado;
    }

    public void setHabaireacondicionado(String habaireacondicionado) {
        this.habaireacondicionado = habaireacondicionado;
    }

    
    
    
    @Override
    public String registrar() {
        return "JasonllPaul";
    }
    
    
    
    
}
