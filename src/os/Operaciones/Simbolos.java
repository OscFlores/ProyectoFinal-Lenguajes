/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Operaciones;
/**
 *
 * @author Oscar
 */
public class Simbolos {
    
    private String Nombre;
    private int Valor;
    
    /**
    *
    * Metodos que nos permitiran manejar el get y set de los diferente Valores a Analizar
    */
    public int getValor() {
        return Valor;
    }
    
    public void setValor(int Valor) {
        this.Valor = Valor;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "Simbolo{" + "Valor=" + Valor + ", Nombre=" + Nombre + '}';
    }
}
