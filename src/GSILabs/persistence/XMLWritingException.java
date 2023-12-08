package GSILabs.persistence;

/**
 * Clase XMLParsingException
 * Clase que representa la exception que se va a lanzar cuando
 * haya problemas en la escritura de archivos XML.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.11.2023
 */
public class XMLWritingException extends Exception{
   
    /**
     * Constructor XMLWritingException
     * @param message Mensaje que lanzará la exception.
     */
    public XMLWritingException(String message) {
        super(message);
    }
}
