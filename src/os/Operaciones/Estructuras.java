/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Operaciones;
import os.Enums.Estados;
import java.util.ArrayList;
import os.Tokens.*;
/**
 *
 * @author Oscar
 */
public class Estructuras {
    
    private final TablaSimbolos tablaSimbolos = new TablaSimbolos();
    private boolean recolectando = false;
    private String tokenGuia;
    private String cadena;
    private String documentoSalida;
    private boolean error = false;
    private ArrayList<String> cadenas = new ArrayList<>();
    private int ciclo;
    private String iniciar;
    private String tokenSubGuia;
    private String tokenAccion;
    
    /**
    *
    * Metodods de Get y Set de Metodos que usaremos para identificar y recolectar los Valores
    */ 
     public boolean isRecolectando() {
        return recolectando;
    }

    public void setRecolectando(boolean recolectando) {
        this.recolectando = recolectando;
    }

     public String getDocumento() {
        return documentoSalida;
    }

     
    public TablaSimbolos getTabla() {
        return tablaSimbolos;
    }

    /**
    *
    * Metodods para Funcionamientos de Datos
    */ 
    public void recolectarEscribir(Token token, Estados produccion, TablaSimbolos tabla, Integer repiticiones){
        if (produccion.equals(Estados.ESCRITURA) && token.getTipoToken().equalsIgnoreCase("ESCRIBIR")) {
            recolectando = true;
        }
    }   
    
    public void recolectorFucionesSalida(Estados produccion, Token token) {
        tablaSimbolos.recolectorAsignaciones(produccion, token);
        this.recolectarProducciones(token.getTipoToken(), token, tablaSimbolos);
        
    }
    
     public void impirmirCiclo() {
        for (int i = 0; i < ciclo; i++) {
            for (int j = 0; j < cadenas.size(); j++) {
                documentoSalida += "\n" + cadenas.get(j);
            }
        }
    }
     
    /**
    *
    * Metodods de Analizis de Estructuras y facilitacion de Analisis
    */ 
    public String quitarComillas(Token token, TablaSimbolos tabla) {
        String salida = "";
        switch (token.getTipoToken()) {
            case "id":
                salida += tabla.valorenSimbolo(token.getLexema());
                break;    
            case "Literal":
                salida += token.getLexema().replace("\"", "");
                break;
            default:
                salida += token.getLexema();
                break;
        }
        return salida;
    }
    
    public void recolectarProducciones(String tipoToken, Token token, TablaSimbolos tabla) {
        if (!tipoToken.equals("ERROR")) {
            if (tokenGuia == null) {
                if (tipoToken.equals("ESCRIBIR")) {
                    tokenGuia = tipoToken;
                } else if (tipoToken.equals("REPETIR")) {
                    tokenGuia = tipoToken;   
                } else if (tipoToken.equals("SI")) {
                    tokenGuia = tipoToken;
                }
            } else {
                if (tokenGuia.equals("ESCRIBIR")) {
                    if (tipoToken.equals("FIN")) {
                        documentoSalida += "\n" + cadena;
                        tokenGuia = null;
                        cadena = null;
                    } else {
                        cadena = quitarComillas(token, tabla);
                    }
                } else if (tokenGuia.equals("REPETIR")) {
                    if (tipoToken.equals("Numero") || tipoToken.equals("id")) {
                        ciclo = tabla.valorenSimbolo(token.getLexema());
                    }
                    if (tipoToken.equals("INICIAR")) {
                        iniciar = "INICIAR";
                    }
                    if (iniciar!= null && tipoToken.equals("ESCRIBIR")) {
                        tokenAccion = "ESCRIBIR";
                    }
                    if (tipoToken.equals("Literal")) {
                        cadenas.add(quitarComillas(token, tabla));
                    }
                    if (tipoToken.equals("FIN") && tokenAccion != null) {
                        tokenAccion = null;
                    } else if (tipoToken.equals("FIN") && tokenAccion == null) {
                        impirmirCiclo();
                        cadenas.clear();
                        cadena = null;
                        tokenGuia = null;
                        tokenAccion = null;
                        tokenGuia = null;
                    }
                } else if (tokenGuia.equals("SI")) {
                    if (tipoToken.equals("VERDADERO")) {
                        tokenSubGuia = "VERDADERO";
                    }
                    if (tipoToken.equals("FALSO")) {
                        tokenSubGuia = "FALSO";
                    }
                    if (tipoToken.equals("ESCRIBIR")) {
                        tokenAccion = "ESCRIBIR";
                    }
                    if (tipoToken.equals("Numero") || tipoToken.equals("Literal")) {
                        cadena = quitarComillas(token, tabla);
                    }
                    if (tipoToken.equals("FIN") && tokenAccion != null) {
                        tokenAccion = null;  
                    } else if (tipoToken.equals("FIN") && tokenAccion == null) {
                        if (tokenSubGuia.equals("VERDADERO")) {
                            documentoSalida += "\n" + cadena;
                        } 
                        cadena = null;
                        tokenGuia = null;
                        tokenAccion = null;
                        tokenSubGuia = null;
                    }
                }
            }
        } else {
            error = true;
        }
    }
}
