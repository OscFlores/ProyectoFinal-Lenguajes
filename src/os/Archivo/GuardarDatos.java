/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.Archivo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Oscar
 */
public class GuardarDatos {
    FileOutputStream salida;
    public String GuardarArchivo(File archivo, String texto) throws IOException{
        String mensaje=null;
        salida= new FileOutputStream(archivo);
        byte[] bytxt=texto.getBytes();
        salida.write(bytxt);
        mensaje="Archivo Guardado Exitosamente";
        return mensaje;
    }

    public void GuardarArchivoExistente(String texto, File archivo) throws IOException{
        try (FileWriter fw = new FileWriter(archivo)) {
            fw.write(texto);
        }
    }
}
