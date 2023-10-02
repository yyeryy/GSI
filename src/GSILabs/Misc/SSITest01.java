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

public class SSITest01 {
    public static void main(String[] args) {
        // Crear una matriz de 4x6 números enteros (como en el ejemplo anterior)
        int[][] matriz = new int[4][6];
        for (int fila = 0; fila < 4; fila++) {
            for (int columna = 0; columna < 6; columna++) {
                matriz[fila][columna] = fila * 10 + columna;
            }
        }

        // Crear un documento de hoja de cálculo ODS
        SpreadSheet doc = SpreadSheet.create(4, 6, 1); // 4 filas, 6 columnas, 1 hoja
        Sheet sheet = doc.getSheet(0);

        // Llenar la hoja de cálculo con los valores de la matriz
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                sheet.setValueAt(matriz[i][j], i, j);
            }
        }

        // Guardar el documento en un archivo ODS llamado "test01.ods"
        try {
            doc.saveAs(new File("test01.ods"));
            System.out.println("Matriz guardada en test01.ods.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}