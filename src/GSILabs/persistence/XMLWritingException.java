/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.persistence;

/**
 * Clase XMLParsingException
 * Clase que representa la exception que se va a lanzar cuando
 * haya problemas en la escritura de archivos XML.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 13.11.2023
 */
public class XMLWritingException extends Exception{
   
    public XMLWritingException(String message) {
        super(message);
    }
}
