package Pruebas;

import GSILabs.BSystem.BusinessSystem;
import GSILabs.persistence.XMLParsingException;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase BusinessSystem
 * Clase que define los métodos implementados de LeisureOffice y LookupService
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class P03Tester {

    public static void main(String[] args) throws IOException {
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

    /**
     * testParseXMLFile
     * @return Booleano para ver si se ha realizado el parseado correctamente.
     * @throws IOException 
     */
    public static boolean testParseXMLFile() throws IOException {
        try {
            File xmlFile = new File("bs.xml");
            BusinessSystem businessSystem = BusinessSystem.parseXMLFile(xmlFile);

            //Leer otra vez el fichero
            BufferedReader bufferedReader = new BufferedReader(new FileReader(xmlFile));
            
            //Leer fichero
            String contenido = "";
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {contenido += linea + "\n";}
            
            //Comprobar si esta vacio
            if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
            String str = contenido;

            //Conteo de lineas para comparar
            int lineasXml = contenido.split("\n").length;
            int lineasBS = businessSystem.toXML().split("\n").length;
            
            return lineasXml == lineasBS;
        } catch (XMLParsingException e) {
            return false;
        }
    }

    /**
     * testLoadXMLFile
     * @return Booleano para ver si se ha realizado el load correctamente.
     */
    public static boolean testLoadXMLFile() {
        try {
            File xmlFile = new File("bs.xml");

            return BusinessSystem.loadXMLFile(xmlFile);
        } catch (XMLParsingException ex) {
            return false;
        }
    }
}