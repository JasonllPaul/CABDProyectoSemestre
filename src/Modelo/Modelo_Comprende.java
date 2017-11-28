package Modelo;

/**
 *
 * @author JasonllPaul
 */
public class Modelo_Comprende {
    
    private int resid;
    private int habid;

    public Modelo_Comprende() {
        this.resid = 0;
        this.habid = 0;
    }
    
    public Modelo_Comprende(int resid, int habid) {
        this.resid = resid;
        this.habid = habid;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public int getHabid() {
        return habid;
    }

    public void setHabid(int habid) {
        this.habid = habid;
    }
    
}
