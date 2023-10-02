/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GSILabs.Misc;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 *
 * @author yeray
 */
public class SSTest02 {

    public static void main(String[] args) {
        // Crear una matriz de 4x6 números enteros (como en el ejemplo anterior)
        int[][] matriz = {
                {7, 4, 7, 5, 19, 5},
                {4, 7, 6, 18, 1, 6},
                {3, 11, 6, 15, 8, 9},
                {9, 8, 7, 22, 7, 13}
        };
        
        try {
            // Crear un documento de hoja de cálculo ODS
            SpreadSheet spreadSheet = SpreadSheet.create(1, matriz[0].length + 3, matriz.length + 5);
            Sheet sheet = spreadSheet.getSheet(0);

            // Llenar la hoja de cálculo con los valores de la matriz
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    sheet.setValueAt(matriz[i][j], j + 3, i + 5);
                    
                    // Establecer el color de fondo según el valor
                    if(matriz[i][j] >= 10){
                        sheet.getCellAt(j + 3, i + 5).setBackgroundColor(Color.BLUE);
                    }else{
                        sheet.getCellAt(j + 3, i + 5).setBackgroundColor(Color.RED);
                    }
                }
            }
            
            // Guardar el documento en un archivo
            spreadSheet.saveAs(new File("test02.ods"));
            System.out.println("Matriz guardada en test02.ods.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
