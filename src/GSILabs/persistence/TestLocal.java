package GSILabs.persistence;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Local.tipoLocal;
import static GSILabs.BModel.Local.tipoLocal.PUB;
import GSILabs.BModel.Propietario;
import static GSILabs.persistence.parser.parseLocal;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestLocal {
    public static void main(String[] args) throws IOException {
        // Variables
        String nombre = "Pepe";
        Direccion direccion = new Direccion("Burlada", "Melilla", "Pereza", 33);
        String descripcion = "El mejor local de Pamplona";
        tipoLocal tipo = PUB;
        ArrayList<Propietario> propietarios = new ArrayList<Propietario>();
        
        Propietario propietario1 = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        propietarios.add(propietario1);
        Propietario propietario2 = new Propietario("Pepa", "4321", LocalDate.of(LocalDate.now().getYear()-50,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth())); 
        propietarios.add(propietario2);
        
        // Generar Local
        Local local = new Local(nombre, direccion, descripcion, tipo, propietarios.get(0));
        local.addPropietario(propietarios.get(1));
        
        // XMLRepresentable: toXML
        /*String XMLlocal = local.toXML();
        System.out.println("String generado por direccion.toXML():\n" + XMLlocal);*/
        System.out.println("local.toXML() NO EXISTE AUN");
        
        // XMLRepresentable: saveToXML a partir de File
        /*String pathname = "XMLLocal1.txt";
        File file = new File(pathname);
        boolean esFicheroCreado1 = local.saveToXML(file);
        if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
        else System.out.println("Error en la creacion del fichero 1");*/
        System.out.println("local.saveToXML() NO EXISTE AUN");
        
        /* A BORRAR CUANDO LAS FUNCIONES ANTERIORES ESTEN */
        // Los .toXML ya tienen el <algo>   </algo>
        // Las substring son para quital el  <?xml version=\"1.0\" encoding=\"UTF-8\"?>
        String XMLLocal = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        XMLLocal += "<Local>\n";
        XMLLocal += "<nombre>" + nombre + "</nombre>\n";
        XMLLocal += "" + direccion.toXML().substring(39, direccion.toXML().length()) + "\n";
        XMLLocal += "<descripcion>" + descripcion + "</descripcion>\n";
        XMLLocal += "<tipo>" + tipo.toString() + "</tipo>\n";
        XMLLocal += "" + propietarios.get(0).toXML().substring(39, propietarios.get(0).toXML().length())  + "\n";
        XMLLocal += "" + propietarios.get(1).toXML().substring(39, propietarios.get(1).toXML().length()) + "\n";
        XMLLocal += "</Local>\n";
        System.out.println("XMLLocal temporal:\n" + XMLLocal);
        /* FIN A BORRAR */
        
        // XMLRepresentable: saveToXML a partir de su direccion
        /*String filepath = "XMLLocal2.txt";
        boolean esFicheroCreado2 = direccion.saveToXML(filepath);
        if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
        else System.out.println("Error en la creacion del fichero 2");*/
        
        /* A BORRAR CUANDO LAS FUNCIONES ANTERIORES ESTEN */
        File file = new File("XMLLocal2.txt");
        try (FileWriter fileWriter = new FileWriter(file)) {
        fileWriter.write(XMLLocal);
        } catch (IOException e) {
            System.out.println("No se ha podido escribir");
        }
        /* FIN A BORRAR */
        
        // Obtener objeto del String XML
        Local local2 = parseLocal(XMLLocal);
        System.out.println("\nTest 1 (String: toXML + parseLocal): " + (local.equals(local2)));
        
        // Obtener objeto del fichero XML
        Local local3 = parseLocal(new File("XMLLocal2.txt"));
        System.out.println("Test 2 (File: toXML + parseLocal(local)): " + (local.equals(local3)));
        //System.out.println(local2.toString());
    }
}