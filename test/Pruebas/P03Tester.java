package Pruebas;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import GSILabs.BSystem.BusinessSystem;
import GSILabs.persistence.XMLParsingException;

import java.io.File;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase BusinessSystem
 * Clase que define los métodos implementados de LeisureOffice y LookupService
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class P03Tester {

    @Test
    public void testParseXMLFile() {
        File xmlFile = new File("src/test/resources/test.xml"); //Poner ruta donde tenemos el XML de ejemplo
        
        try {
            //Prueba la función parseXMLFile
            BusinessSystem businessSystem = new BusinessSystem();
            BusinessSystem.parseXMLFile(xmlFile);
            
            assertNotNull(businessSystem);
        } catch (XMLParsingException e) {
            fail("Excepción inesperada: " + e.getMessage());
        }
    }
    
    @Test
    public void testLoadXMLFile() throws XMLParsingException {
        File xmlFile = new File("src/test/resources/test.xml"); //Poner ruta donde tenemos el XML de ejemplo

        //BusinessSystem businessSystem = new BusinessSystem();
        boolean result = BusinessSystem.loadXMLFile(xmlFile);

        assertTrue(result);
    }
}

