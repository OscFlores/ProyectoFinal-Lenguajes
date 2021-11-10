/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Operaciones;
import os.Enums.*;
import java.util.*;
import os.Tokens.*;
/**
 *
 * @author Oscar
 */
public class TablaSimbolos {
    private ArrayList<Simbolos> Simbolos = new ArrayList<>();
    private final ArrayList<Token> Tokens = new ArrayList<>();
    private boolean recolector = false;
    /**
     *
     * Metodos para la Obtencion y Creacion de la Tabla
     */
    public int valorenSimbolo(String token) {
        int valor = 0;
        try {
            valor = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            for (Simbolos simbolo : Simbolos) {
                if (simbolo.getNombre().equals(token)) {
                    valor = simbolo.getValor();
                    break;
                }
            }
        }
        return valor;
    }

    private void capturarSimbolo(Token token) {
        this.Tokens.add(token);
    }
    
    private ArrayList<String> reasignar(ArrayList<String> valores, int I, int F) {
        ArrayList<String> auxiliar = new ArrayList<>();
        for (int i = 0; i < I + 1; i++) {
            auxiliar.add(valores.get(i));
        }
        for (int i = F + 1; i < valores.size(); i++) {
            auxiliar.add(valores.get(i));
        }
        return auxiliar;
    }
    
    private ArrayList<String> lexemasdeTokens() {
        int index = 0;
        ArrayList<String> lexemas = new ArrayList<>();
        for (Token token : Tokens) {
            if (!token.getLexema().equalsIgnoreCase("=") && index != 0) {
                lexemas.add(token.getLexema());
            }
            index++;
        }
        return lexemas;
    }
    
    public void recolectorAsignaciones(Estados produccion, Token token) {
        if (produccion.equals(produccion.ASIGNACION) && token.getTipoToken().equalsIgnoreCase("id")) {
            recolector = true;
        }
        if (recolector) {
            if (token.getTipoToken().equalsIgnoreCase("FIN")) {
                calcularValorSimbolo();
                recolector = false;
            } else {
                this.capturarSimbolo(token);
            }
        }
    }

    public void calcularValorSimbolo() {
        ArrayList<String> valores = this.lexemasdeTokens();
        valores = this.operacionesconParentesis(valores);    
        Simbolos simbolo = new Simbolos();
        simbolo.setNombre(this.Tokens.get(0).getLexema());
        simbolo.setValor(Integer.parseInt(valores.get(0)));
        Simbolos.add(simbolo);
        this.Tokens.clear();
    }

    private ArrayList<String> operacionLinealMultiplicidad(ArrayList<String> valores) {
        int producto = 0;
        while (valores.contains("*")) {
            for (int i = 0; i < valores.size(); i++) {
                if (valores.get(i).equalsIgnoreCase("*")) {
                    producto = valorenSimbolo(valores.get(i + 1)) * valorenSimbolo(valores.get(i - 1));
                    valores.set(i, producto + "");
                    valores.remove(i + 1);
                    valores.remove(i - 1);
                    break;
                }
            }
        }
       return valores;
    }

    private ArrayList<String> operacionlinealSuma(ArrayList<String> valores) {
        int suma = 0;
        for (String valor : valores) {
            suma += this.valorenSimbolo(valor);
        }
        valores.clear();
        valores.add(suma + "");
        return valores;
    }
    
    private ArrayList<String> operacionesconParentesis(ArrayList<String> valores) {
        if (valores.contains("(")) {
            while (valores.contains("(")) {
                int temI = 0;
                int temF = 0;
                ArrayList<String> auxiliar = new ArrayList<>();
                for (int i = 0; i < valores.size(); i++) {
                    if (valores.get(i).equalsIgnoreCase("(")) {
                        temI = i;
                    }
                }
                for (int i = temI; i < valores.size(); i++) {
                    if (valores.get(i).equalsIgnoreCase(")")) {
                        temF = i;
                        break;
                    }
                }
                for (int i = temI + 1; i < temF; i++) {
                    auxiliar.add(valores.get(i));
                }
                auxiliar = operacionLinealMultiplicidad(auxiliar);
                auxiliar = operacionlinealSuma(auxiliar);
                valores.set(temI, auxiliar.get(0));
                valores = this.reasignar(valores, temI, temF);
            }
            valores = this.operacionLinealMultiplicidad(valores);
            valores = this.operacionlinealSuma(valores);
        } else {
            valores = this.operacionLinealMultiplicidad(valores);
            valores = this.operacionlinealSuma(valores);
        }
        return valores;
    }
}
