package GSILabs.persistence;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import static GSILabs.persistence.parser.parseReview;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase TestReview
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 07.11.2023
 * Test de Review.
 */
public class TestReview {
    public static void main(String[] args) throws IOException {
        try {
            int valoracion = 9;
            String comentario = "Muy buen local";
            LocalDate fechaReview = LocalDate.of(LocalDate.now().getYear() - 1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
            
            //Local
            Direccion direccion = new Direccion("Burlada", "Melilla", "Pereza", 33);
            ArrayList<Propietario> propietarios = new ArrayList<Propietario>();
            Propietario propietario1 = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
            propietarios.add(propietario1);
            Propietario propietario2 = new Propietario("Pepa", "4321", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
            propietarios.add(propietario2);
            Bar local = new Bar("Local estandar", direccion, "Descripcion generica", propietarios.get(0));
            local.addPropietario(propietarios.get(1));
            
            Usuario usuario = new Usuario("Diego", "6996", LocalDate.of(LocalDate.now().getYear() - 18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), CLIENTE);
            Contestacion contestacion = new Contestacion("Me parece adecuado", LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth()), local);
            
            //Generar Reserva
            Review review = new Review(valoracion, comentario, fechaReview, local, usuario);
            review.setContestacion(contestacion);
            
            //XMLRepresentable: toXML
            String XMLreview = review.toXML();
            System.out.println("String generado por review.toXML():\n" + XMLreview);
            
            //XMLRepresentable: saveToXML a partir de File
            String pathname = "XMLReview1.xml";
            File file = new File(pathname);
            boolean esFicheroCreado1 = review.saveToXML(file);
            if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
            else System.out.println("Error en la creacion del fichero 1");
            
            //XMLRepresentable: saveToXML a partir de su review
            String filepath = "XMLReview2.xml";
            boolean esFicheroCreado2 = review.saveToXML(filepath);
            if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
            else System.out.println("Error en la creacion del fichero 2");
            
            //Obtener objeto del String XML
            Review review2 = parseReview(XMLreview);
            System.out.println("\nTest 1 (String: toXML + parseReview): " + (review.equals(review2)));
            
            //Obtener objeto del fichero XML
            Review review3 = parseReview(new File("XMLReview2.xml"));
            System.out.println("Test 2 (File: toXML + parseReview(review)): " + (review.equals(review3)));
        } catch (XMLParsingException ex) {
            Logger.getLogger(TestReview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}