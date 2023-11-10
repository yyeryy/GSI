package GSILabs.persistence;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import static GSILabs.persistence.parser.parsePub;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestPub {
    public static void main(String[] args) throws IOException {
        try {
            // Variables
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
            
            // Generar Pub
            Pub pub = new Pub(horaApertura, horaClausura, nombre, direccion, descripcion, propietarios.get(0));
            
            // XMLRepresentable: toXML
            /*String XMLpub = pub.toXML();
            System.out.println("String generado por pub.toXML():\n" + XMLpub);*/
            System.out.println("pub.toXML() TENEIS QUE ARREGLARLO");
            
            // XMLRepresentable: saveToXML a partir de File
            /*String pathname = "XMLPub1.txt";
            File file = new File(pathname);
            boolean esFicheroCreado1 = pub.saveToXML(file);
            if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
            else System.out.println("Error en la creacion del fichero 1");*/
            System.out.println("local.saveToXML() TENEIS QUE ARREGLARLO");
            
            /* A BORRAR CUANDO LAS FUNCIONES ANTERIORES ESTEN */
            // Los .toXML ya tienen el <algo>   </algo>
            // Las substring son para quital el  <?xml version=\"1.0\" encoding=\"UTF-8\"?>
            String XMLPub = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
            XMLPub += "<Pub>\n";
            XMLPub += "<horaApertura>" + pub.getHoraApertura() + "</horaApertura>\n";
            XMLPub += "<horaClausura>" + pub.getHoraClausura() + "</horaClausura>\n";
            XMLPub += "<nombre>" + pub.getNombre() + "</nombre>\n";
            XMLPub += "" + direccion.toXML().substring(39, direccion.toXML().length()) + "\n";
            XMLPub += "<descripcion>" + descripcion + "</descripcion>\n";
            XMLPub += "" + propietarios.get(0).toXML().substring(39, propietarios.get(0).toXML().length())  + "\n";
            XMLPub += "" + propietarios.get(1).toXML().substring(39, propietarios.get(1).toXML().length()) + "\n";
            XMLPub += "</Pub>\n";
            System.out.println("XMLPub temporal:\n" + XMLPub);
            /* FIN A BORRAR */
            
            // XMLRepresentable: saveToXML a partir de su pub
            /*String filepath = "XMLPub2.txt";
            boolean esFicheroCreado2 = direccion.saveToXML(filepath);
            if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
            else System.out.println("Error en la creacion del fichero 2");*/
            
            /* A BORRAR CUANDO LAS FUNCIONES ANTERIORES ESTEN */
            File file = new File("XMLPub2.txt");
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(XMLPub);
            } catch (IOException e) {
                System.out.println("No se ha podido escribir");
            }
            /* FIN A BORRAR */
            
            // Obtener objeto del String XML
            Pub pub2 = parsePub(XMLPub);
            System.out.println("\nTest 1 (String: toXML + parsePub): " + (pub.equals(pub2)));
            
            // Obtener objeto del fichero XML
            Pub pub3 = parsePub(new File("XMLPub2.txt"));
            System.out.println("Test 2 (File: toXML + parsePub(pub)): " + (pub.equals(pub3)));
            //System.out.println(local2.toString());
        } catch (XMLParsingException ex) {
            Logger.getLogger(TestPub.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* FIN A BORRAR */
    }
}
