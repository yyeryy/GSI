package GSILabs.persistence;

import GSILabs.BModel.Propietario;
import static GSILabs.persistence.parser.parsePropietario;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase TestPropietario
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 07.11.2023
 * Test de Propietario.
 */
public class TestPropietario {
    public static void main(String[] args) throws IOException {
        try {
            String nick = "marcos";
            String contrasena = "4321134";
            LocalDate fechaNacimiento = LocalDate.of(LocalDate.now().getYear() - 22,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
            
            //Generar Cliente
            Propietario propietario = new Propietario(nick, contrasena, fechaNacimiento);
            
            //XMLRepresentable: toXML
            String XMLpropietario = propietario.toXML();
            System.out.println("String generado por direccion.toXML():\n" + XMLpropietario);
            
            //XMLRepresentable: saveToXML a partir de File
            String pathname = "XMLPropietario.xml";
            File file = new File(pathname);
            boolean esFicheroCreado1 = propietario.saveToXML(file);
            if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
            else System.out.println("Error en la creacion del fichero 1");
            
            //XMLRepresentable: saveToXML a partir de su direccion
            String filepath = "XMLPropietario2.xml";
            boolean esFicheroCreado2 = propietario.saveToXML(filepath);
            if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
            else System.out.println("Error en la creacion del fichero 2");
            
            //Obtener objeto del String XML
            Propietario propietario2 = parsePropietario(XMLpropietario);
            System.out.println("\nTest 1 (String: toXML + parsePropietario): " + (propietario.equals(propietario2)));
            
            //Obtener objeto del fichero XML
            Propietario propietario3 = parsePropietario(new File("XMLPropietario2.xml"));
            System.out.println("Test 2 (File: toXML + parsePropietario(propietario)): " + (propietario.equals(propietario3)));
        } catch (XMLParsingException ex) {
            Logger.getLogger(TestPropietario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
