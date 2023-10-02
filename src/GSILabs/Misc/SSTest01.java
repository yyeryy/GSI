package GSILabs.Misc;

import java.io.File;
import java.io.IOException;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
/**
 * Clase Tester
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */

public class SSTest01 {
    public static void main(String[] args) {
        // Crear una matriz de 4x6 números enteros (como en el ejemplo anterior)
        int[][] matriz = new int[4][6];
        for (int fila = 0; fila < 4; fila++) {
            for (int columna = 0; columna < 6; columna++) {
                matriz[fila][columna] = fila * 10 + columna;
            }
        }
        
        try {
            // Crear un documento de hoja de cálculo ODS
            SpreadSheet spreadSheet = SpreadSheet.create(1, matriz[0].length, matriz.length);
            Sheet sheet = spreadSheet.getSheet(0);

            // Llenar la hoja de cálculo con los valores de la matriz
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    sheet.setValueAt(matriz[i][j], j, i);
                }
            }

            // Guardar el documento en un archivo
            spreadSheet.saveAs(new File("test01.ods"));
            System.out.println("Matriz guardada en test01.ods.");
        } catch (IOException e) {
            e.printStackTrace();
        }


        

    }
}