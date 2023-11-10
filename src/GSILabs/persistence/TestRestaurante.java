package GSILabs.persistence;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Restaurante;
import static GSILabs.persistence.parser.parsePub;
import static GSILabs.persistence.parser.parseRestaurante;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestRestaurante {
    public static void main(String[] args) throws IOException {
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
        /*String XMLrestaurante = restaurante.toXML();
        System.out.println("String generado por restaurante.toXML():\n" + XMLrestaurante);*/
        System.out.println("restaurante.toXML() TENEIS QUE ARREGLARLO");
        
        // XMLRepresentable: saveToXML a partir de File
        /*String pathname = "XMLRestaurante1.txt";
        File file = new File(pathname);
        boolean esFicheroCreado1 = restaurante.saveToXML(file);
        if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
        else System.out.println("Error en la creacion del fichero 1");*/
        System.out.println("restaurante.saveToXML() TENEIS QUE ARREGLARLO");
        
        /* A BORRAR CUANDO LAS FUNCIONES ANTERIORES ESTEN */
        // Los .toXML ya tienen el <algo>   </algo>
        // Las substring son para quital el  <?xml version=\"1.0\" encoding=\"UTF-8\"?>
        String XMLRestaurante = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        XMLRestaurante += "<Restaurante>\n";
        XMLRestaurante += "<precioMenu>" + restaurante.getPrecioMenu() + "</precioMenu>\n";
        XMLRestaurante += "<capacidad>" + restaurante.getCapacidad() + "</capacidad>\n";
        XMLRestaurante += "<capacidadMesa>" + restaurante.getCapacidadMesa() + "</capacidadMesa>\n";
        XMLRestaurante += "<nombre>" + restaurante.getNombre() + "</nombre>\n";
        XMLRestaurante += "" + direccion.toXML().substring(39, direccion.toXML().length()) + "\n";
        XMLRestaurante += "<descripcion>" + descripcion + "</descripcion>\n";
        XMLRestaurante += "" + propietarios.get(0).toXML().substring(39, propietarios.get(0).toXML().length())  + "\n";
        XMLRestaurante += "" + propietarios.get(1).toXML().substring(39, propietarios.get(1).toXML().length()) + "\n";
        XMLRestaurante += "</Restaurante>\n";
        System.out.println("XMLRestaurante temporal:\n" + XMLRestaurante);
        /* FIN A BORRAR */
        
        // XMLRepresentable: saveToXML a partir de su restaurante
        /*String filepath = "XMLRestaurante2.txt";
        boolean esFicheroCreado2 = direccion.saveToXML(filepath);
        if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
        else System.out.println("Error en la creacion del fichero 2");*/
        
        /* A BORRAR CUANDO LAS FUNCIONES ANTERIORES ESTEN */
        File file = new File("XMLRestaurante2.txt");
        try (FileWriter fileWriter = new FileWriter(file)) {
        fileWriter.write(XMLRestaurante);
        } catch (IOException e) {
            System.out.println("No se ha podido escribir");
        }
        /* FIN A BORRAR */
        
        // Obtener objeto del String XML
        Restaurante restaurante2 = parseRestaurante(XMLRestaurante);
        System.out.println("\nTest 1 (String: toXML + parseRestaurante): " + (restaurante.equals(restaurante2)));
        
        // Obtener objeto del fichero XML
        Restaurante restaurante3 = parseRestaurante(new File("XMLRestaurante2.txt"));
        System.out.println("Test 2 (File: toXML + parseRestaurante(pub)): " + (restaurante.equals(restaurante3)));
        //System.out.println(local2.toString());
    }
}
