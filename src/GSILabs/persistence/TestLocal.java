package GSILabs.persistence;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Local.tipoLocal;
import static GSILabs.BModel.Local.tipoLocal.PUB;
import GSILabs.BModel.Propietario;
import static GSILabs.persistence.parser.parseLocal;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestLocal {
    public static void main(String[] args) throws IOException {
        try {
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
            String XMLlocal = local.toXML();
            System.out.println("String generado por local.toXML():\n" + XMLlocal);
            
            // XMLRepresentable: saveToXML a partir de File
            String pathname = "XMLLocal1.xml";
            File file = new File(pathname);
            boolean esFicheroCreado1 = local.saveToXML(file);
            if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
            else System.out.println("Error en la creacion del fichero 1");
     
            // XMLRepresentable: saveToXML a partir de su local
            String filepath = "XMLLocal2.xml";
            boolean esFicheroCreado2 = local.saveToXML(filepath);
            if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
            else System.out.println("Error en la creacion del fichero 2");
            
            // Obtener objeto del String XML
            Local local2 = parseLocal(XMLlocal);
            System.out.println("\nTest 1 (String: toXML + parseLocal): " + (local.equals(local2)));
            
            // Obtener objeto del fichero XML
            Local local3 = parseLocal(new File("XMLLocal2.xml"));
            System.out.println("Test 2 (File: toXML + parseLocal(local)): " + (local.equals(local3)));
            //System.out.println(local2.toString());
        } catch (XMLParsingException ex) {
            Logger.getLogger(TestLocal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}