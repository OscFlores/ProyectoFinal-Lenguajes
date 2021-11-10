/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Tokens;
import java.util.ArrayList;
/**
 *
 * @author Oscar
 */
public class Token {

    private ArrayList<Character> signosAgrupacion;
    private ArrayList<Character> operadoresMatematicos;
    private ArrayList<Character> caracteresEspeciales;
    private ArrayList<Character> signosPuntuacion;
    private String lexema;
    private String tipoToken;
    private int fila;
    private int columna;
    private String descripcion;

    public Token() {
        this.agrupacionTokens();
    }
    /**
    *
    * Metodos Get & Set de los distintos Tipos de Tokens
    */
    public ArrayList<Character> getSignosAgrupacion() {
        return signosAgrupacion;
    }

    public void setSignosAgrupacion(ArrayList<Character> signosAgrupacion) {
        this.signosAgrupacion = signosAgrupacion;
    }

    public ArrayList<Character> getOperadoresMatematicos() {
        return operadoresMatematicos;
    }

    public void setOperadoresMatematicos(ArrayList<Character> operadoresMatematicos) {
        this.operadoresMatematicos = operadoresMatematicos;
    }

    public ArrayList<Character> getCaracteresEspeciales() {
        return caracteresEspeciales;
    }

    public void setCaracteresEspeciales(ArrayList<Character> caracteresEspeciales) {
        this.caracteresEspeciales = caracteresEspeciales;
    }

    public ArrayList<Character> getSignosPuntuacion() {
        return signosPuntuacion;
    }

    public void setSignosPuntuacion(ArrayList<Character> signosPuntuacion) {
        this.signosPuntuacion = signosPuntuacion;
    }

    public String getLexema() {
        return lexema;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public int getFila() {
        return fila;
    }

   public int getColumna() {
        return columna;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
     /**
    *
    * Constructores de las diferentes Combinaciones en los Tokens
    */
      public Token(String tipoToken, String lexema, int fila, int columna) {
        this.lexema = lexema;
        this.tipoToken = tipoToken;
        this.fila = fila;
        this.columna = columna;
    }
    
     private void agrupacionTokens() {
        tiposSignosAgrupacion();
        tiposOperadoresMatematicos();
        tiposSignosPuntuacion();
        tiposCaractereesEspeciales();
    }
     
     /**
    *
    * Metodos de Tokens
    */
    public void tiposCaractereesEspeciales() {
        this.caracteresEspeciales = new ArrayList<>();
        this.caracteresEspeciales.add('t');
        this.caracteresEspeciales.add('f');
        this.caracteresEspeciales.add('n');
        this.caracteresEspeciales.add('r');    
    }

    private void tiposSignosAgrupacion() {
        signosAgrupacion = new ArrayList<>();
        this.signosAgrupacion.add(')');
        this.signosAgrupacion.add('(');       
    }

    private void tiposSignosPuntuacion() {
        signosPuntuacion = new ArrayList<>();
        this.signosPuntuacion.add('.');
        this.signosPuntuacion.add(':');
        this.signosPuntuacion.add(';');
        this.signosPuntuacion.add(',');
        this.signosPuntuacion.add('<');
        this.signosPuntuacion.add('<');
        this.signosPuntuacion.add('‘');
        this.signosPuntuacion.add('\'');
        this.signosPuntuacion.add('>');
    }

    private void tiposOperadoresMatematicos() {
        operadoresMatematicos = new ArrayList<>();
        this.operadoresMatematicos.add('*');
        this.operadoresMatematicos.add('%');
        this.operadoresMatematicos.add('+');
    }
}
