/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.Misc;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author luisazcoitipena
 */
public class SSTest03 {
    public static void main(String[] args) {
        // Cargar el documento desde el archivo
        File file = new File("test02.ods");
        try {
            SpreadSheet spreadSheet = SpreadSheet.createFromFile(file);

            // Obtener la hoja de la hoja de cálculo
            Sheet sheet = spreadSheet.getSheet(0);

            // Obtener la matriz desde la hoja de cálculo
            int[][] matriz = new int[sheet.getRowCount() - 5][sheet.getColumnCount() - 3];
            
            for (int i = 5; i < sheet.getRowCount(); i++) {
                for (int j = 3; j < sheet.getColumnCount(); j++) {
                    matriz[i - 5][j - 3] = Integer.parseInt(sheet.getValueAt(j, i).toString());
                }
            }

            // Imprimir la matriz recuperada
            for (int[] row : matriz) {
                for (int value : row) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
