package GSILabs.persistence;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import static GSILabs.persistence.parser.parseContestacion;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase TestContestacion
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 07.11.2023
 * Test de Contestacion.
 */
public class TestContestacion {
    public static void main(String[] args) throws IOException {
        try {
            String comentario = "Tendremos en cuenta su opinion";
            LocalDate fechaReview = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
            
            //Local
            ArrayList<Propietario> propietarios = new ArrayList<Propietario>();
            Propietario propietario1 = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
            propietarios.add(propietario1);
            Propietario propietario2 = new Propietario("Pepa", "4321", LocalDate.of(LocalDate.now().getYear()-50,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
            propietarios.add(propietario2);
            Direccion direccion = new Direccion("Burlada", "Melilla", "Pereza", 33);
            Bar local = new Bar("Santa chula", direccion, "Restaurante deluxe", propietarios.get(0));
            local.addPropietario(propietarios.get(1));
            
            //Generar Constestacion
            Contestacion contestacion = new Contestacion(comentario, fechaReview, local);
            
            //XMLRepresentable: toXML
            String XMLcontestacion = contestacion.toXML();
            System.out.println("String generado por contestacion.toXML():\n" + XMLcontestacion);
            
            //XMLRepresentable: saveToXML a partir de File
            String pathname = "XMLContestacion1.xml";
            File file = new File(pathname);
            boolean esFicheroCreado1 = contestacion.saveToXML(file);
            if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
            else System.out.println("Error en la creacion del fichero 1");
            
            //XMLRepresentable: saveToXML a partir de su contestacion
            String filepath = "XMLContestacion2.xml";
            boolean esFicheroCreado2 = contestacion.saveToXML(filepath);
            if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
            else System.out.println("Error en la creacion del fichero 2");
            
            Contestacion contestacion2 = parseContestacion(XMLcontestacion);
            System.out.println("\nTest 1 (String: toXML + parseContestacion): " + (contestacion.equals(contestacion2)));
            
            //Obtener objeto del fichero XML
            Contestacion contestacion3 = parseContestacion(new File("XMLContestacion2.xml"));
            System.out.println("Test 2 (File: toXML + parseContestacion(contestacion)): " + (contestacion.equals(contestacion3)));
        } catch (XMLParsingException ex) {
            Logger.getLogger(TestContestacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
