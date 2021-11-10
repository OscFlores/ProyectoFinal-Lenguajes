/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Tokens;
/**
 *
 * @author Oscar
 */
public class TextoIDE {
    private String Acccion;
    private String TextoToken;
    /**
     *
     * Constructor de Clase
     */
    public TextoIDE(String textoToken, String accion) {
        this.TextoToken = textoToken;
        this.Acccion=accion;
    }
    /**
     *
     * Metodos Get & Set de Variables
     */
    public String getAcccion() {
        return Acccion;
    }

    public void setAcccion(String Acccion) {
        this.Acccion = Acccion;
    }

    public String getTextoToken() {
        return TextoToken;
    }

    public void setTextoToken(String TextoToken) {
        this.TextoToken = TextoToken;
    }

}
