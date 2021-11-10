/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.ManejadoresAnalizador;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import os.Tokens.ErroresLexicos;
import os.InterfazGrafica.VentanaTablaErrores;
/**
 *
 * @author Oscar
 */
public class ManejadorTablaError {
    DefaultTableCellRenderer Apilar = new DefaultTableCellRenderer();
    DefaultTableModel modelo = new DefaultTableModel();
    int index = 0;
    /**
     *
     * Metodos Set para Llenado de la Tabla de Errores y llenado de Datos
     */
    public void llenarTabla(VentanaTablaErrores ventana){
        JTable tabla= ventana.getTable();
        this.setModelo(tabla);
        this.setDatos(tabla);
    }
      
    public void setModelo(JTable tabla){
        String[] columna= {"Tipo Token","Error Encontrado","Fila","Columna"};
        modelo.setColumnIdentifiers(columna);
        tabla.setModel(modelo);
    }

    public void setDatos(JTable tabla){
        Object[] datos= new Object[modelo.getColumnCount()];
        modelo.setRowCount(0);
        for (String cadena : ErroresLexicos.cadena) {
            modelo.addRow(new Object[]{cadena, ErroresLexicos.caracter.get(index), ErroresLexicos.fila.get(index), ErroresLexicos.columna.get(index)});
            index++;
        }
        Apilar.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(Apilar);
        tabla.getColumnModel().getColumn(1).setCellRenderer(Apilar);
        tabla.getColumnModel().getColumn(2).setCellRenderer(Apilar);
        tabla.getColumnModel().getColumn(3).setCellRenderer(Apilar);
        tabla.setModel(modelo);
    }
}
