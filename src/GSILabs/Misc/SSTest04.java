package GSILabs.Misc;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Restaurante;
import GSILabs.BSystem.BusinessSystem;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

/**
 * Clase SSTest04
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 02.10.2023
 * cree una clase de nombre SSTest04 cuyo método main
 * almacene instancias de las clases que implementan Local en un fichero ODS.
 */
public class SSTest04 {

    public static void main(String[] args) {
        //Instancia de businessSystem
        BusinessSystem bs = new BusinessSystem();
        
        //Creación de la lista de direcciones
        List<Direccion> direcciones = new ArrayList<>();
        
        //Creación de direcciones
        Direccion direccion1 = new Direccion("Pamplona", "Navarra", "Calle Monasterio de Urdax", 22);
        Direccion direccion2 = new Direccion("Pamplona", "Navarra", "Calle Navarrería", 10);
        Direccion direccion3 = new Direccion("Pamplona", "Navarra", "Avenida Bayona", 41);
        Direccion direccion4 = new Direccion("Pamplona", "Navarra", "Calle Nueva", 125);
        Direccion direccion5 = new Direccion("Pamplona", "Navarra", "Paseo de Buztintxuri", 12);
        Direccion direccion6 = new Direccion("Mutilva Baja", "Navarra", "Calle B", 2);
        Direccion direccion7 = new Direccion("Pamplona", "Navarra", "Universidad Pública de Navarra", 0);
        Direccion direccion8 = new Direccion("Cordovilla", "Navarra", "Parque Comercial Galaria", 0);
        Direccion direccion9 = new Direccion("Pamplona", "Navarra", "Avenida Bayona", 2);
        Direccion direccion10 = new Direccion("Pamplona", "Navarra", "Calle Navas de Tolosa", 11);
        Direccion direccion11 = new Direccion("Pamplona", "Navarra", "Calle Yanguas y Miranda", 2);
        Direccion direccion12 = new Direccion("Pamplona", "Navarra", "Calle de Juan de Labrit", 0);
        
        //Creación de restaurantes, pubs y bares asociados a propietarios y direcciones
        //Creación de propietarios
        Propietario propietario1 = new Propietario("Miguel Artazcoz", "1234", LocalDate.of(LocalDate.now().getYear()-45, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        Propietario propietario2 = new Propietario("María Chen", "1234", LocalDate.of(LocalDate.now().getYear()-32, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        Propietario propietario3 = new Propietario("Cristina Ronalda", "1234", LocalDate.of(LocalDate.now().getYear()-28, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        Propietario propietario4 = new Propietario("Patxi Irizar", "1234", LocalDate.of(LocalDate.now().getYear()-56, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        Propietario propietario5 = new Propietario("Javier Mendoza", "1234", LocalDate.of(LocalDate.now().getYear()-55, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        Propietario propietario6 = new Propietario("Liun Xen", "1234", LocalDate.of(LocalDate.now().getYear()-31, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        Propietario propietario7 = new Propietario("Lorena Vergara", "1234", LocalDate.of(LocalDate.now().getYear()-22, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        Propietario propietario8 = new Propietario("Carmen Auto", "1234", LocalDate.of(LocalDate.now().getYear()-46, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        Propietario propietario9 = new Propietario("Nano Alonso", "1234", LocalDate.of(LocalDate.now().getYear()-33, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        Propietario propietario10 = new Propietario("Juan Etxeberria", "1234", LocalDate.of(LocalDate.now().getYear()-25, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        Propietario propietario11 = new Propietario("Ana Ibañez", "1234", LocalDate.of(LocalDate.now().getYear()-57, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        Propietario propietario12 = new Propietario("Jose Pastis", "1234", LocalDate.of(LocalDate.now().getYear()-40, LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth())); 
        
        //Creación de bares
        Bar bar1 = new Bar("Bar Danubio", direccion1, "Bar Danubio", propietario1);
        Bar bar2 = new Bar("Ezkia Taberna", direccion2, "Ezkia Taberna", propietario2);
        Bar bar3 = new Bar("Café Cream", direccion3, "Café Cream", propietario3);
        Bar bar4 = new Bar("Cervecería Baztán", direccion4, "Cervecería Baztán", propietario4);
        
        //Creación de restaurantes
        Restaurante restaurante1 = new Restaurante("Delorean", direccion5, "Restaurante Delorean", propietario5, 12.5, 70, 4);
        Restaurante restaurante2 = new Restaurante("Sakura", direccion6, "Restaurante Sakura", propietario6, 12.5, 70, 4);
        Restaurante restaurante3 = new Restaurante("Comedor UPNA", direccion7, "Comedor UPNA", propietario7, 12.5, 70, 4);
        Restaurante restaurante4 = new Restaurante("McDonalds", direccion8, "Restaurante McDonalds", propietario8, 12.5, 70, 4);
        
        //Creación de pubs
        Pub pub1 = new Pub("22:00", "06:00", "Canalla", direccion9, "Canalla", propietario9);
        Pub pub2 = new Pub("22:00", "03:00", "Kla-B", direccion10, "Kla-B", propietario10);
        Pub pub3 = new Pub("22:00", "06:00", "Indara", direccion11, "Indara", propietario11);
        Pub pub4 = new Pub("00:00", "08:00", "Cavas", direccion12, "Cavas", propietario12);
        
        //Adición de las direcciones al array de direcciones
        direcciones.add(direccion1);
        direcciones.add(direccion2);
        direcciones.add(direccion3);
        direcciones.add(direccion4);
        direcciones.add(direccion5);
        direcciones.add(direccion6);
        direcciones.add(direccion7);
        direcciones.add(direccion8);
        direcciones.add(direccion9);
        direcciones.add(direccion10);
        direcciones.add(direccion11);
        direcciones.add(direccion12);
        
        //Agregamos bares y sus especialidades
        bs.nuevoLocal(bar1);
        bar1.agregarEspecialidad("Tacos");
        bar1.agregarEspecialidad("Hamburguesas");
        bar1.agregarEspecialidad("Croquetas");
        
        bs.nuevoLocal(bar2);
        bar2.addPropietario(propietario3);
        bar2.agregarEspecialidad("Cervezas");
        bar2.agregarEspecialidad("Tortilla de patata");
        
        bs.nuevoLocal(bar3);
        bar3.agregarEspecialidad("Cervezas");
        bar3.agregarEspecialidad("Patatas");
        bar3.agregarEspecialidad("Nachos");
        
        bs.nuevoLocal(bar4);
        bar4.agregarEspecialidad("Cervezas");
        
        //Agregamos restaurantes y sus características
        bs.nuevoLocal(restaurante1);
        restaurante1.setCapacidad(120);
        restaurante1.setPrecioMenu(12.5);
        restaurante1.setCapacidadMesa(12);
        
        bs.nuevoLocal(restaurante2);
        restaurante2.setCapacidad(500);
        restaurante2.setPrecioMenu(17.5);
        restaurante2.setCapacidadMesa(6);
        
        bs.nuevoLocal(restaurante3);
        restaurante3.setCapacidad(250);
        restaurante3.setPrecioMenu(8.5);
        restaurante3.setCapacidadMesa(30);
        
        bs.nuevoLocal(restaurante4);
        restaurante4.setCapacidad(60);
        restaurante4.setPrecioMenu(9.0);
        restaurante4.setCapacidadMesa(4);
        
        //Agregamos pubs
        bs.nuevoLocal(pub1);
        bs.nuevoLocal(pub2);
        bs.nuevoLocal(pub3);
        bs.nuevoLocal(pub4);
        
        try {
            //Creación de un documento de hoja de cálculo ODS con 3 hojas
            SpreadSheet spreadSheet = SpreadSheet.create(3, 50, 50);
            
            //Creación de la primera hoja (Bar) dentro del documento .ods
            Sheet sheetBares = spreadSheet.getSheet(0);
            sheetBares.setName("Bar");
            
            //Creación de la segunda hoja (Restaurante) dentro del documento .ods
            Sheet sheetRestaurantes = spreadSheet.getSheet(1);
            sheetRestaurantes.setName("Restaurante");
            
            //Creación de la tercera hoja (Pub) dentro del documento .ods
            Sheet sheetPubs = spreadSheet.getSheet(2);
            sheetPubs.setName("Pub");
            
            //Establecimiento de los nombres de las columnas en la hoja Bar
            sheetBares.setValueAt("Nombre", 0, 0);
            sheetBares.setValueAt("Calle", 1, 0);
            sheetBares.setValueAt("Localidad", 2, 0);
            sheetBares.setValueAt("Provincia", 3, 0);
            sheetBares.setValueAt("Propietario", 4, 0);
            sheetBares.setValueAt("Especialidades", 6, 0);
            
            //Establecimiento de los nombres de las columnas en la hoja Restaurante
            sheetRestaurantes.setValueAt("Nombre", 0, 0);
            sheetRestaurantes.setValueAt("Calle", 1, 0);
            sheetRestaurantes.setValueAt("Localidad", 2, 0);
            sheetRestaurantes.setValueAt("Provincias", 3, 0);
            sheetRestaurantes.setValueAt("Propietarios", 4, 0);
            sheetRestaurantes.setValueAt("Precio Menu", 6, 0);
            sheetRestaurantes.setValueAt("Capacidad", 7, 0);
            sheetRestaurantes.setValueAt("Capacidad Mesa", 8, 0);
            
            //Establecimiento de los nombres de las columnas en la hoja Pub
            sheetPubs.setValueAt("Nombre", 0, 0);
            sheetPubs.setValueAt("Calle", 1, 0);
            sheetPubs.setValueAt("Localidad", 2, 0);
            sheetPubs.setValueAt("Provincias", 3, 0);
            sheetPubs.setValueAt("Propietarios", 4, 0);
            sheetPubs.setValueAt("Hora Apertura", 6, 0);
            sheetPubs.setValueAt("Hora Clausura", 7, 0);


            int j;
            //Llenado de la hoja de bares con los valores de los locales de tipo bar
            for (int i = 0; i < 4; i++) {
                Direccion direccion = direcciones.get(i);
                sheetBares.setValueAt(bs.obtenerLocal(direccion).getNombre(), 0, i+1);
                sheetBares.setValueAt(bs.obtenerLocal(direccion).getDireccion().getCalle() + " " + bs.obtenerLocal(direccion).getDireccion().getNumero(), 1, i+1);
                sheetBares.setValueAt(bs.obtenerLocal(direccion).getDireccion().getLocalidad(), 2, i+1);
                sheetBares.setValueAt(bs.obtenerLocal(direccion).getDireccion().getProvincia(), 3, i+1);
                for (j = 0; j < bs.obtenerLocal(direccion).getPropietarios().size(); j++) {
                    sheetBares.setValueAt(bs.obtenerLocal(direccion).getPropietarios().get(j).getNick(), 4 + j, i+1);
                }
                for (int k = 0; k < ((Bar)bs.obtenerLocal(direccion)).getEspecialidades().size(); k++) {
                    sheetBares.setValueAt(((Bar)bs.obtenerLocal(direccion)).getEspecialidades().get(k), 5 + j + k, i+1);
                }
            }
            
            //Llenado de la hoja de restaurantes con los valores de los locales de tipo restaurante
            for (int i = 4; i < 8; i++) {
                Direccion direccion = direcciones.get(i);
                sheetRestaurantes.setValueAt(bs.obtenerLocal(direccion).getNombre(), 0, i - 3);
                sheetRestaurantes.setValueAt(bs.obtenerLocal(direccion).getDireccion().getCalle() + " " + bs.obtenerLocal(direccion).getDireccion().getNumero(), 1, i - 3);
                sheetRestaurantes.setValueAt(bs.obtenerLocal(direccion).getDireccion().getLocalidad(), 2, i - 3);
                sheetRestaurantes.setValueAt(bs.obtenerLocal(direccion).getDireccion().getProvincia(), 3, i - 3);
                for (j = 0; j < bs.obtenerLocal(direccion).getPropietarios().size(); j++) {
                    sheetRestaurantes.setValueAt(bs.obtenerLocal(direccion).getPropietarios().get(j).getNick(), 4 + j, i - 3);
                }
                sheetRestaurantes.setValueAt(((Restaurante)bs.obtenerLocal(direccion)).getPrecioMenu(), 5 + j, i - 3);
                sheetRestaurantes.setValueAt(((Restaurante)bs.obtenerLocal(direccion)).getCapacidad(), 6 + j, i - 3);
                sheetRestaurantes.setValueAt(((Restaurante)bs.obtenerLocal(direccion)).getCapacidadMesa(), 7 + j, i - 3);
            }
            
            //Llenado de la hoja de pubs con los valores de los locales de tipo pub
            for (int i = 8; i < 12; i++) {
                Direccion direccion = direcciones.get(i);
                sheetPubs.setValueAt(bs.obtenerLocal(direccion).getNombre(), 0, i - 7);
                sheetPubs.setValueAt(bs.obtenerLocal(direccion).getDireccion().getCalle() + " " + bs.obtenerLocal(direccion).getDireccion().getNumero(), 1, i - 7);
                sheetPubs.setValueAt(bs.obtenerLocal(direccion).getDireccion().getLocalidad(), 2, i - 7);
                sheetPubs.setValueAt(bs.obtenerLocal(direccion).getDireccion().getProvincia(), 3, i - 7);
                for (j = 0; j < bs.obtenerLocal(direccion).getPropietarios().size(); j++) {
                    sheetPubs.setValueAt(bs.obtenerLocal(direccion).getPropietarios().get(j).getNick(), 4 + j, i - 7);
                }
                sheetPubs.setValueAt(((Pub)bs.obtenerLocal(direccion)).getHoraApertura(), 5 + j, i - 7);
                sheetPubs.setValueAt(((Pub)bs.obtenerLocal(direccion)).getHoraClausura(), 6 + j, i - 7);
            }
            
            //Almacenamiento del documento
            spreadSheet.saveAs(new File("test03.ods"));
            System.out.println("Locales guardados en test03.ods.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
