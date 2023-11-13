package GSILabs.persistence;

import GSILabs.BModel.Usuario;
import GSILabs.BModel.Usuario.tipoUsuario;
import static GSILabs.BModel.Usuario.tipoUsuario.PROPIETARIO;
import static GSILabs.persistence.parser.parseUsuario;
import java.io.File;
import java.time.LocalDate;

public class TestUsuario {
    public static void main(String[] args) throws Exception{
        // Variables
        String nick = "Pepe";
        String contrasena = "1234";
        LocalDate fechaNacimiento = LocalDate.of(LocalDate.now().getYear() - 18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth());
        tipoUsuario tipo = PROPIETARIO;
        
        // Generar Usuario
        Usuario usuario = new Usuario(nick, contrasena, fechaNacimiento, tipo);
        
        // XMLRepresentable: toXML
        String XMLusuario = usuario.toXML();
        System.out.println("String generado por direccion.toXML():\n" + XMLusuario);
        
        // XMLRepresentable: saveToXML a partir de File
        String pathname = "XMLUsuario1.xml";
        File file = new File(pathname);
        boolean esFicheroCreado1 = usuario.saveToXML(file);
        if(esFicheroCreado1) {System.out.println("Fichero 1 creado con exito: " + pathname);}
        else System.out.println("Error en la creacion del fichero 1");
        
        // XMLRepresentable: saveToXML a partir de su direccion
        String filepath = "XMLUsuario2.xml";
        boolean esFicheroCreado2 = usuario.saveToXML(filepath);
        if(esFicheroCreado2) System.out.println("Fichero 2 creado con exito: " + filepath);
        else System.out.println("Error en la creacion del fichero 2");
        
        // Obtener objeto del String XML
        Usuario usuario2 = parseUsuario(XMLusuario);
        System.out.println("\nTest 1 (String: toXML + parseUsuario): " + (usuario.equals(usuario2)));
        
        // Obtener objeto del fichero XML
        Usuario usuario3 = parseUsuario(new File("XMLUsuario2.xml"));
        System.out.println("Test 2 (File: toXML + parseUsuario(usuario)): " + (usuario.equals(usuario3)));
    }
}
