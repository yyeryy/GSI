package GSILabs.Misc;

import java.io.File;
import java.io.IOException;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 * Clase SSTest01
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 02.10.2023
 * Cree una clase ejecutable llamada SSTest01. En el método main de dicha clase 
 * debe crear un array bidimensional de 4 × 6 números enteros. Esta matriz se 
 * almacenará en un fichero de nombre test01.ods, tal que la matriz ocupe las 
 * primeras 4 filas y 6 columnas de la primera de sus hojas.
 */
public class SSTest01 {
    public static void main(String[] args) {
        //Creación de matriz de 4x6 números enteros
        int[][] matriz = {
                {7, 4, 7, 5, 9, 5},
                {4, 7, 6, 8, 1, 6},
                {3, 1, 6, 5, 8, 9},
                {9, 8, 7, 2, 7, 3}
        };
        
        try {
            //Creación de un documento de hoja de cálculo ODS
            SpreadSheet spreadSheet = SpreadSheet.create(1, matriz[0].length, matriz.length);
            Sheet sheet = spreadSheet.getSheet(0);

            //Llenado de la hoja de cálculo con los valores de la matriz
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    sheet.setValueAt(matriz[i][j], j, i);
                }
            }

            //Almacenamiento del documento
            spreadSheet.saveAs(new File("test01.ods"));
            System.out.println("Matriz guardada en test01.ods.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}