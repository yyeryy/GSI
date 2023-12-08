/*
 * This document is part of the lab material for the subject:
 * Gestion de Sistemas de Informacion
 * to be released at the
 * Universidad Publica de Navarra
 * during the first semester of the Academic Year 2015-2016
 */
 
package GSILabs.serializable;

import java.io.File;

/**
 * XMLRepresentable
 * This interface embodies the ability of a a class of objects to represent itself
 * using XML. 
 * Note that it refers, in no manner, to its ability to create instances from such a representation.
 *
 * @author carlos.lopez
 * @version 1.0 (06/08/2013)    
 */
public interface XMLRepresentable {
    
    /**
     * Generación de una representación XML del objeto
     * que implementa la interfaz.
     * @return Representación XML del objeto en forma de cadena
     */
    public String toXML();
    
    /**
     * Guardado de la representación XML del objeto que implementa la interfaz
     * en el fichero indicado por parámetro.
     * @param f Fichero XML en el que se guarda la representación XML del objeto
     * @return Booleano que indica si el fichero se ha guardado exitosamente.
     */
    public boolean saveToXML(File f);
    
    /**
     * Guardado de la representación XML del objeto que implementa la interfaz
     * en un fichero XML que se almacenará en la ruta indicada por parámetro.
     * @param filePath Ruta del fichero donde se va a guardar la reprentación XML.
     * @return Booleano que indica si el fichero se ha guardado exitosamente.
     */
    public boolean saveToXML(String filePath);
    
}
