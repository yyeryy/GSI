package GSILabs.persistence;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import static GSILabs.persistence.parser.parsePub;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase TestPub
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 07.11.2023
 * Test de Pub.
 */
public class TestPub {
    public static void main(String[] args) throws IOException {
        try {
            String horaApertura = "23:00";
            String horaClausura = "04:00";
            String nombre = "Santa Chula";
            Direccion direccion = new Direccion("Villava", "Navarra", "Poligono", 69);
            String descripcion = "No eres consciente del navajazo que te van a meter aqui";
            ArrayList<Propietario> propietarios = new ArrayList<Propietario>();
            
            Propietario propietario1 = new Propietario("Carlos", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
            propietarios.add(propietario1);
            Propietario propietario2 = new Propietario("Aimar", "4321", LocalDate.of(LocalDate.now().getYear()-50,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
            propietarios.add(propietario2);
            
            //Generar Pub
            Pub pub = new Pub(horaApertura, horaClausura, nombre, direccion, descripcion, propietarios.get(0));
            pub.addPropietario(propietarios.get(1));
            
            //XMLRepresentable: toXML
            String XMLpub = pub.toXML();
            System.out.println("String generado por pub.toXML():\n" + XMLpub);
            
            //XMLRepresentable: saveToXML a partir de File
            String pathname = "XMLPub1.xml";
            File file = new File(pathname);
            boolean esFicheroCreado1 = pub.saveToXML(file);
            if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
            else System.out.println("Error en la creacion del fichero 1");
            
            //XMLRepresentable: saveToXML a partir de su pub
            String filepath = "XMLPub2.xml";
            boolean esFicheroCreado2 = pub.saveToXML(filepath);
            if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
            else System.out.println("Error en la creacion del fichero 2");
            
            //Obtener objeto del String XML
            Pub pub2 = parsePub(XMLpub);
            System.out.println("\nTest 1 (String: toXML + parsePub): " + (pub.equals(pub2)));
            
            //Obtener objeto del fichero XML
            Pub pub3 = parsePub(new File("XMLPub2.xml"));
            System.out.println("Test 2 (File: toXML + parsePub(pub)): " + (pub.equals(pub3)));
            
        } catch (XMLParsingException ex) {
            Logger.getLogger(TestPub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
