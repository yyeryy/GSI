package GSILabs.persistence;

import GSILabs.BModel.Direccion;
import static GSILabs.persistence.parser.parseDireccion;
import java.io.File;

/**
 * Clase TestDireccion
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 07.11.2023
 * Test de Dirección.
 */
public class TestDireccion {
    public static void main(String[] args) throws Exception{
        
        String localidad = "Pamplona";
        String provincia = "Navarra";
        String calle = "Avenida Cataluña";
        int portal = 1;
        
        //Generar Direccion
        Direccion direccion = new Direccion(localidad, provincia, calle, portal);
        
        //XMLRepresentable: toXML
        String XMLdireccion = direccion.toXML();
        System.out.println("String generado por direccion.toXML():\n" + XMLdireccion);
        
        //XMLRepresentable: saveToXML a partir de File
        String pathname = "XMLDireccion1.xml";
        File file = new File(pathname);
        boolean esFicheroCreado1 = direccion.saveToXML(file);
        if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
        else System.out.println("Error en la creacion del fichero 1");
        
        //XMLRepresentable: saveToXML a partir de su direccion
        String filepath = "XMLDireccion2.xml";
        boolean esFicheroCreado2 = direccion.saveToXML(filepath);
        if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
        else System.out.println("Error en la creacion del fichero 2");
        
        //Obtener objeto del String XML
        Direccion direccion2 = parseDireccion(XMLdireccion);
        System.out.println("\nTest 1 (String: toXML + parseDireccion): " + (direccion.equals(direccion2)));
        
        //Obtener objeto del fichero XML
        Direccion direccion3 = parseDireccion(new File("XMLDireccion2.xml"));
        System.out.println("Test 2 (File: toXML + parseDireccion(direccion)): " + (direccion.equals(direccion3)));
    }
}
