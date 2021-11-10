/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Archivo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import os.InterfazGrafica.VentanaPrincipal;
/**
 *
 * @author Oscar
 */
public class CargaDatos {
    public static File archivoAProcesar;
 
    public CargaDatos(File archivoAProcesar){
        this.archivoAProcesar = archivoAProcesar;
    }
    
    private void leerArchivo() throws FileNotFoundException, IOException, ArrayIndexOutOfBoundsException{
        VentanaPrincipal.ventana.getAreaTexto().setText(""); 
        BufferedReader lector = new BufferedReader(new FileReader(this.archivoAProcesar));
        String auxiliar = lector.readLine();
        String temporal="";
        while(auxiliar != null){
            temporal=temporal+auxiliar;
            if(temporal!=""){
               VentanaPrincipal.ventana.getAreaTexto().append(temporal);
               temporal="";
            }
            auxiliar = lector.readLine();
            if(auxiliar!=null){
               VentanaPrincipal.ventana.getAreaTexto().append("\n");
               VentanaPrincipal.ventana.getAreaTexto().append("\n"); 
            }        
        }       
        JOptionPane.showMessageDialog(null, "Datos cargados Exitosamente.", "CARGA DE DATOS", JOptionPane.INFORMATION_MESSAGE);
        lector.close();
    }   
    
      public void analizarTexto(){
        try {
            leerArchivo();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            System.err.println(e); 
        }
    }
}
