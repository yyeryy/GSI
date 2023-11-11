/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pruebas;

import GSILabs.BSystem.BusinessSystem;
import GSILabs.persistence.XMLParsingException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.fail;

/**
 *
 * @author yeray
 */
public class P03Tester {
    public void main(String[] args) {
        if(testParseXMLFile()){
            System.out.println("Test de ParseXMLFile completado");
        }else{
            System.out.println("Test de ParseXMLFile no completado");
        }if(testLoadXMLFile()){
            System.out.println("Test de LoadXMLFile completado");
        }else{
            System.out.println("Test de LoadXMLFile no completado");
        }
    }

    public boolean testParseXMLFile() {
        File xmlFile = new File("src/test/resources/test.xml"); //Poner ruta donde tenemos el XML de ejemplo
        
        try {
            //Prueba la función parseXMLFile
            BusinessSystem businessSystem = BusinessSystem.parseXMLFile(xmlFile);
            
            return businessSystem != null;
        } catch (XMLParsingException e) {
            fail("Excepción inesperada: " + e.getMessage());
            return false;
        }
    }
    
    public boolean testLoadXMLFile() {
        try {
            File xmlFile = new File("src/test/resources/test.xml"); //Poner ruta donde tenemos el XML de ejemplo

            return BusinessSystem.loadXMLFile(xmlFile);
        } catch (XMLParsingException ex) {
            Logger.getLogger(P03Tester.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
