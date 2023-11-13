package GSILabs.persistence;

import GSILabs.BModel.Cliente;
import GSILabs.BModel.Reserva;
import static GSILabs.persistence.parser.parseReserva;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestReserva {
    public static void main(String[] args) throws IOException {
        try {
            // Variables
            Cliente cliente = new Cliente("Yeray", "6666", LocalDate.of(LocalDate.now().getYear() - 20,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
            LocalDate fecha = LocalDate.of(LocalDate.now().getYear() + 1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
            LocalTime hora = LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute());
            int descuento = 10;
            
            // Generar Reserva
            Reserva reserva = new Reserva(cliente, fecha, hora, descuento);
            
            // XMLRepresentable: toXML
            String XMLreserva = reserva.toXML();
            System.out.println("String generado por reserva.toXML():\n" + XMLreserva);
            
            String pathname = "XMLReserva1.xml";
            File file = new File(pathname);
            boolean esFicheroCreado1 = reserva.saveToXML(file);
            if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
            else System.out.println("Error en la creacion del fichero 1");
            
            String filepath = "XMLReserva2.xml";
            boolean esFicheroCreado2 = reserva.saveToXML(filepath);
            if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
            else System.out.println("Error en la creacion del fichero 2");
            
            // Obtener objeto del String XML
            Reserva reserva2 = parseReserva(XMLreserva);
            System.out.println("\nTest 1 (String: toXML + parseReserva): " + (reserva.equals(reserva2)));
            
            // Obtener objeto del fichero XML
            Reserva reserva3 = parseReserva(new File("XMLReserva2.xml"));
            System.out.println("Test 2 (File: toXML + parseReserva(reserva)): " + (reserva.equals(reserva3)));
        } catch (XMLParsingException ex) {
            Logger.getLogger(TestReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
