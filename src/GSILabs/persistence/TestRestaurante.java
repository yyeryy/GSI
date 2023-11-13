package GSILabs.persistence;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Restaurante;
import static GSILabs.persistence.parser.parseRestaurante;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestRestaurante {
    public static void main(String[] args) throws IOException {
        try {
            // Variables
            Double precioMenu = 15.99;
            Integer capacidad = 100;
            Integer capacidadMesa = 4;
            String nombre = "The Beer Garden";
            Direccion direccion = new Direccion("Pamplona", "Navarra", "Calle Ermitagaña", 19);
            String descripcion = "Restaurante con los mejores cocteles de la ciudad";
            ArrayList<Propietario> propietarios = new ArrayList<Propietario>();
            
            Propietario propietario1 = new Propietario("Pedro", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
            propietarios.add(propietario1);
            Propietario propietario2 = new Propietario("Anna", "4321", LocalDate.of(LocalDate.now().getYear()-50,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
            propietarios.add(propietario2);
            
            // Generar Restaurante
            Restaurante restaurante = new Restaurante(nombre, direccion, descripcion, propietarios.get(0), precioMenu, capacidad, capacidadMesa);
            
            // XMLRepresentable: toXML
            String XMLrestaurante = restaurante.toXML();
            System.out.println("String generado por restaurante.toXML():\n" + XMLrestaurante);
            
            // XMLRepresentable: saveToXML a partir de File
            String pathname = "XMLRestaurante1.xml";
            File file = new File(pathname);
            boolean esFicheroCreado1 = restaurante.saveToXML(file);
            if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
            else System.out.println("Error en la creacion del fichero 1");
            
            // XMLRepresentable: saveToXML a partir de su restaurante
            String filepath = "XMLRestaurante2.xml";
            boolean esFicheroCreado2 = restaurante.saveToXML(filepath);
            if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
            else System.out.println("Error en la creacion del fichero 2");
            
            // Obtener objeto del String XML
            Restaurante restaurante2 = parseRestaurante(XMLrestaurante);
            System.out.println("\nTest 1 (String: toXML + parseRestaurante): " + (restaurante.equals(restaurante2)));
            
            // Obtener objeto del fichero XML
            Restaurante restaurante3 = parseRestaurante(new File("XMLRestaurante2.xml"));
            System.out.println("Test 2 (File: toXML + parseRestaurante(restaurante)): " + (restaurante.equals(restaurante3)));
            //System.out.println(local2.toString());
        } catch (XMLParsingException ex) {
            Logger.getLogger(TestRestaurante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
