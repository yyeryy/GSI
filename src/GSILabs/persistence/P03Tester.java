/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GSILabs.persistence;

import GSILabs.BSystem.BusinessSystem;
import java.io.File;

/**
 *
 * @author yeray
 */
public class P03Tester {

    public static void main(String[] args) {
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

    public static boolean testParseXMLFile() {
        try {
            File xmlFile = new File("XMLCliente1.txt");
            BusinessSystem businessSystem = BusinessSystem.parseXMLFile(xmlFile);
            
            return businessSystem != null;
        } catch (XMLParsingException e) {
            return false;
        }
    }
    
    public static boolean testLoadXMLFile() {
        try {
            File xmlFile = new File("XMLCliente1.txt");

            return BusinessSystem.loadXMLFile(xmlFile);
        } catch (XMLParsingException ex) {
            return false;
        }
    }
    
}
