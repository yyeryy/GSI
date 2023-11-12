package GSILabs.persistence;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Reserva;
import static GSILabs.persistence.parser.parseBar;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBar {
    public static void main(String[] args) throws IOException {
        try {
            // Variables
            String nombre = "Ana";
            Direccion direccion = new Direccion("Chusmalandia", "Marruecos", "PerezaDeVida", 66);
            String descripcion = "Quiero dormir";
            ArrayList<Propietario> propietarios = new ArrayList<Propietario>();
            Propietario propietario1 = new Propietario("Maria", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
            propietarios.add(propietario1);
            Propietario propietario2 = new Propietario("Carmen", "4321", LocalDate.of(LocalDate.now().getYear()-50,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
            propietarios.add(propietario2);
            // Generar Bar
            Bar bar = new Bar(nombre, direccion, descripcion, propietarios.get(0));
            bar.addPropietario(propietarios.get(1));
            // Añadir especialidades, esto es opcional
            String especialidad1 = "Mexicano";
            String especialidad2 = "Italiano";
            bar.agregarEspecialidad(especialidad1);
            bar.agregarEspecialidad(especialidad2);
            
            // Añadir Reservas, esto es opcional
            Cliente cliente = new Cliente("Alfonso", "1234", LocalDate.of(2000,1,1));
            Reserva reserva = new Reserva(cliente,  LocalDate.of(2050,1,1), LocalTime.MIN, 0);
            Reserva reserva1 = new Reserva(cliente,  LocalDate.of(2070,1,1), LocalTime.MIN, 0);
            bar.nuevaReserva(cliente,  LocalDate.of(2050,1,1), LocalTime.MIN);
            bar.nuevaReserva(cliente,  LocalDate.of(2070,1,1), LocalTime.MIN);
            
            // XMLRepresentable: toXML
            String XMLbar = bar.toXML();
            System.out.println("String generado por bar.toXML():\n" + XMLbar);
            // XMLRepresentable: saveToXML a partir de File
            String pathname = "XMLBar1.txt";
            File file = new File(pathname);
            boolean esFicheroCreado1 = bar.saveToXML(file);
            if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
            else System.out.println("Error en la creacion del fichero 1");
            // XMLRepresentable: saveToXML a partir de su direccion
            String filepath = "XMLBar2.txt";
            boolean esFicheroCreado2 = bar.saveToXML(filepath);
            if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
            else System.out.println("Error en la creacion del fichero 2");
            // Obtener objeto del String XML
            Bar bar2 = parseBar(XMLbar);
            System.out.println("\nTest 1 (String: toXML + parseBar): " + (bar.equals(bar2)));
            // Obtener objeto del fichero XML
            Bar bar3 = parseBar(new File("XMLBar2.txt"));
            System.out.println("Test 2 (File: toXML + parseBar(bar)): " + (bar.equals(bar3)));
        } catch (XMLParsingException ex) {
            Logger.getLogger(TestBar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
} 