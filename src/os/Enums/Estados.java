/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Enums;
/**
 *
 * @author Oscar
 */
public enum Estados {
    
    /**
     * Las Estados representan ESTADOS/ESTRUCTURAS/TERMINALES/RESULTADOS
     */
    FINALIZAR("FINALIZAR"),
    INICIAR("ESCRIBIR, SI, REPETIR, O ID"),
    ESCRITURA("ESCRIBIR"),
    LEXEMA("LITERAL, ID, O NUMERO"),
    REPETIR("REPETIR"),
    TERMINALH("NUMERO O ID"),
    CONDICINAL("SI"),
    VALORBOOLEANO("VERDADERO O FALSO"),
    PRODUCCIONX("NUMERO, ID, +, * (, ), O FIN"),
    PRODUCCIONXP("NUMERO, ID, +, * (, ), O FIN"),
    PRODUCCIONT("NUMERO, ID, +, * (, ), FIN"),
    PRODUCCIONTP("NUMERO, ID, +, * (, ), FIN"),
    PRODUCCIONF("NUMERO, ID, +, * (, ), O FIN"),
    ASIGNACION("ID"),
    EPSILON("NUMERO, ID, +, * (, ), O FIN");
   
   private String esperarEstados;
   
   private Estados(String esperarEstados){
       this.esperarEstados = esperarEstados;
   }

    public String getEsperarEstados() {
        return esperarEstados;
    }

    public void setEsperarEstados(String esperarEstados) {
        this.esperarEstados = esperarEstados;
    } 
}
