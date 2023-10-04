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
 * Clase SSTest02
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 02.10.2023
 * cree una clase ejecutable llamada SSTest02. En el
 * método main de dicha clase debe crear un array bidimensional de 4 × 6 números 
 * enteros. Esta matriz se almacenará en un fichero de nombre test02.ods, 
 * dejando que las primeras 5 filas y 3 columnas estén vacías. Es decir, el 
 * elemento de la primera fila y columna (de la matriz) aparecerá en la cuarta 
 * fila, sexta columna (de la primera página de la hoja de cálculo). Además, el 
 * color de fondo de las celdas donde se almacena el array bidimensional estará 
 * determinado por el valor de los enteros. En caso de que los números sean 
 * mayores o iguales que 10, el fondo será azul, siendo rojo en caso contrario. 
 * Cualquier tono de azul y rojo será valido.
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
