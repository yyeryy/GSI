/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.persistence;

/**
 * Clase XMLParsingException
 * Clase que representa la exception que se va a lanzar cuando
 * haya problemas en el análisis de archivos XML.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.11.2023
 */
public class XMLParsingException extends Exception{
   
    public XMLParsingException(String message) {
        super(message);
    }
}
