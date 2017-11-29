package utilidades;

/**
 *
 * @author JasonllPaul
 */
public class Validador {

    public void Validador() {

    }

    /**Valida si está vacío o no el campo de texto
     * @param txt Campo de texto a validar
     * @return regresa si es mayor a 0 el tamaño del campo de texto
    */
    public boolean validarVacio(javax.swing.JTextField txt) {
        return txt.getText().length() > 0;
    }

    public void validarTamanio(javax.swing.JTextField txt) {
        if (txt.getText().length() > 10) {
            txt.setText(txt.getText().substring(0, 10));
        }
    }

}
