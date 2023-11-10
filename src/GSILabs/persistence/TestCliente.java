package GSILabs.persistence;

import GSILabs.BModel.Cliente;
import static GSILabs.persistence.parser.parseCliente;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestCliente {
    public static void main(String[] args) throws IOException {
        try {
            // Variables
            String nick = "juana";
            String contrasena = "4321";
            LocalDate fechaNacimiento = LocalDate.of(LocalDate.now().getYear() - 20,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
            
            // Generar Cliente
            Cliente cliente = new Cliente(nick, contrasena, fechaNacimiento);
            
            // XMLRepresentable: toXML
            String XMLcliente = cliente.toXML();
            System.out.println("String generado por direccion.toXML():\n" + XMLcliente);
            
            // XMLRepresentable: saveToXML a partir de File
            String pathname = "XMLCliente1.txt";
            File file = new File(pathname);
            boolean esFicheroCreado1 = cliente.saveToXML(file);
            if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
            else System.out.println("Error en la creacion del fichero 1");
            
            // XMLRepresentable: saveToXML a partir de su direccion
            String filepath = "XMLCliente2.txt";
            boolean esFicheroCreado2 = cliente.saveToXML(filepath);
            if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
            else System.out.println("Error en la creacion del fichero 2");
            
            // Obtener objeto del String XML
            Cliente cliente2 = parseCliente(XMLcliente);
            System.out.println("\nTest 1 (String: toXML + parseCliente): " + (cliente.equals(cliente2)));
            // Obtener objeto del fichero XML
            Cliente cliente3 = parseCliente(new File("XMLCliente2.txt"));
            System.out.println("Test 2 (File: toXML + parseCliente(cliente)): " + (cliente.equals(cliente3)));
        } catch (XMLParsingException ex) {
            Logger.getLogger(TestCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
