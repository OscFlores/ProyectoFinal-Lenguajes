/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Operaciones;
import os.Enums.Estados;
import java.util.Stack;
import javax.swing.JOptionPane;
/**
 *
 * @author Oscar
 */
public class AutomataPila {
    private Stack automataPila = new Stack();

    public AutomataPila() {
        this.automataPila.push(Estados.FINALIZAR.name());
        this.automataPila.push(Estados.INICIAR);
    }

    private boolean terminalExistenteEnPila(String tokenEvaluando) {
        return this.automataPila.contains(tokenEvaluando);
    }
    
    public Stack getPila() {
        return automataPila;
    }

    public void setPila(Stack automataPila) {
        this.automataPila = automataPila;
    }
    
    public boolean comprobarExistenciaProduccion(Object object) {
        boolean includidoEnumProduccion = false;
        Estados[] produccion = Estados.values();
        for (Estados producciones : produccion) {
            includidoEnumProduccion = producciones.equals(object);
            if (includidoEnumProduccion) {
                break;
            }
        }
        return includidoEnumProduccion;
    }
    
    public void llenarPila(String palabra, String token) {
        if (palabra != null) {
            if (!palabra.equals("Ɛ")) {
                automataPila.pop();
                switch (palabra) {
                    case "ES":
                        automataPila.push(Estados.INICIAR);
                        automataPila.push(Estados.ESCRITURA);
                        break;
                    case "AS":
                        automataPila.push(Estados.INICIAR);
                        automataPila.push(Estados.ASIGNACION);
                        break;
                    case "RS":
                        automataPila.push(Estados.INICIAR);
                        automataPila.push(Estados.REPETIR);
                        break;
                    case "CS":
                        automataPila.push(Estados.INICIAR);
                        automataPila.push(Estados.CONDICINAL);
                        break;
                    case "$":
                        automataPila.push(Estados.EPSILON);
                        break;
                    case "ESCRIBIR L FIN E":
                        automataPila.push(Estados.ESCRITURA);
                        automataPila.push("FIN");
                        automataPila.push(Estados.LEXEMA);
                        automataPila.push("ESCRIBIR");
                        break;   
                    case "Literal":
                        automataPila.push("Literal");
                        break;
                    case "Numero":
                        automataPila.push("Numero");
                        break;
                    case "id":
                        automataPila.push("id");
                        break;
                    case "REPETIR H INICIAR E FIN R":
                        automataPila.push(Estados.REPETIR);
                        automataPila.push("FIN");
                        automataPila.push(Estados.ESCRITURA);
                        automataPila.push("INICIAR");
                        automataPila.push(Estados.TERMINALH);
                        automataPila.push("REPETIR");
                        break;    
                    case "Si B ENTONCES E FIN C":
                        automataPila.push(Estados.CONDICINAL);
                        automataPila.push("FIN");
                        automataPila.push(Estados.ESCRITURA);
                        automataPila.push("ENTONCES");
                        automataPila.push(Estados.VALORBOOLEANO);
                        automataPila.push("SI");
                        break;
                    case "VERDADERO":
                        automataPila.push("VERDADERO");
                        break;
                    case "FALSO":
                        automataPila.push("FALSO");
                        break;
                    case "TX’":
                        automataPila.push(Estados.PRODUCCIONXP);
                        automataPila.push(Estados.PRODUCCIONT);
                        break;
                    case "+TX’":
                        automataPila.push(Estados.PRODUCCIONXP);
                        automataPila.push(Estados.PRODUCCIONT);
                        automataPila.push("+");
                        break;    
                    case "FT’":
                        automataPila.push(Estados.PRODUCCIONTP);
                        automataPila.push(Estados.PRODUCCIONF);
                        break;    
                    case "*FT’":
                        automataPila.push(Estados.PRODUCCIONTP);
                        automataPila.push(Estados.PRODUCCIONF);
                        automataPila.push("*");
                        break;
                    case "(X)":
                        automataPila.push(")");
                        automataPila.push(Estados.PRODUCCIONX);
                        automataPila.push("(");
                        break;    
                    case "id = X FIN A":
                        automataPila.push(Estados.ASIGNACION);
                        automataPila.push("FIN");
                        automataPila.push(Estados.PRODUCCIONX);
                        automataPila.push("=");
                        automataPila.push("id");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Estructuras Semanticas no reconocidas por el automata de pila", "ERROR", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } else {
                if (terminalExistenteEnPila(token)) {
                    while (comprobarExistenciaProduccion(this.automataPila.peek())) {
                        this.automataPila.pop();
                    }
                } else {
                    if (this.automataPila.contains(Estados.PRODUCCIONXP)) {
                        while (!Estados.PRODUCCIONXP.equals(this.automataPila.peek())) {
                            this.automataPila.pop();
                        }
                    } else {
                        while (!Estados.INICIAR.equals(this.automataPila.peek())) {
                            this.automataPila.pop();
                        }
                    }
                }
            }
        } 
    }
}
