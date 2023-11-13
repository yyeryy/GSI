/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GSILabs.persistence;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Reserva;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Review;
import GSILabs.BSystem.BusinessSystem;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author 34636
 */
public class TestBS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws XMLWritingException {
        // Crear distintos objetos que componen el sistema para realizar las pruebas
        Direccion direccion1 = new Direccion("Pamplona","Navarra","Calle Mayor",1);
        Direccion direccion2 = new Direccion("Pamplona","Navarra","Calle Amaya",2);
        Direccion direccion3 = new Direccion("Pamplona","Navarra","Avenida Cataluña",3);
        Direccion direccion4 = new Direccion("Villava","Navarra","Avenida Fermin Tirapu",4);
        Direccion direccion5 = new Direccion("Burlada","Navarra","Calle Landazabal",5);
        Direccion direccion6 = new Direccion("Barcelona","Barcelona","Plaza de España",6);
        Direccion direccion7 = new Direccion("Madrid","Madrid","Calle Alcala",7);
        Direccion direccion8 = new Direccion("Bilbao","Bizkaia","Gran Via",8);
        Direccion direccion9 = new Direccion("Tudela","Navarra", "Plaza Estación",9);
        Propietario propietario1 = new Propietario("Antonio","123456789",LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario2 = new Propietario("María Carmen","234567891", LocalDate.of(LocalDate.now().getYear()-19,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario3 = new Propietario("María","345678912",LocalDate.of(LocalDate.now().getYear()-20,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario4 = new Propietario("Manuel","456789123",LocalDate.of(LocalDate.now().getYear()-21,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario5 = new Propietario("Jose","567891234",LocalDate.of(LocalDate.now().getYear()-22,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario6 = new Propietario("Francisco","678912345",LocalDate.of(LocalDate.now().getYear()-23,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario7 = new Propietario("Carmen","789123456",LocalDate.of(LocalDate.now().getYear()-24,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario8 = new Propietario("David","891234567",LocalDate.of(LocalDate.now().getYear()-25,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario9 = new Propietario("Ana","912345678",LocalDate.of(LocalDate.now().getYear()-26,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Bar bar1 = new Bar("Nara",direccion1,"Bar antiguo cerca del Ayuntamiento", propietario1);
        Bar bar2 = new Bar("El Rincon Bohemio", direccion2, "Bar con música y decoracion local", propietario2);
        Bar bar3 = new Bar("Bar Eclipse", direccion3,"Luz tenue y buenos cocteles",propietario3);
        Restaurante restaurante1 = new Restaurante("Sotano encantado",direccion4,"Sotano con iluminacion de velas",propietario4,20,140,4);
        Restaurante restaurante2 = new Restaurante("Cantina estelar",direccion5,"Mejor restaurante para comer en los descansos",propietario5,8,250,10);
        Restaurante restaurante3 = new Restaurante("La cervecería mística",direccion6,"Disfruta de tus comidas acompañadas de la mejor cerveza",propietario6,15,75,8);
        Pub pub1 = new Pub("10:00","2:00","El refugio del Jazz",direccion7,"Sumergete entre copas y Jazz",propietario7);
        Pub pub2 = new Pub("11:00","3:00","Tabanco del Río",direccion8,"Tradición española acompañada de las mejores bebidas",propietario8);
        Pub pub3 = new Pub("10:00","13:00","Luna Roja",direccion9,"Pub con una ambientacion bohemia y encantadora",propietario9);
        bar1.addPropietario(propietario2);
        bar1.addPropietario(propietario3);
        bar2.addPropietario(propietario3);
        restaurante1.addPropietario(propietario5);
        restaurante1.addPropietario(propietario6);
        restaurante2.addPropietario(propietario6);
        pub1.addPropietario(propietario8);
        pub1.addPropietario(propietario9);
        pub2.addPropietario(propietario9);
        bar1.agregarEspecialidad("Mexicano");
        bar1.agregarEspecialidad("Italiano");
        bar2.agregarEspecialidad("Frances");
        Cliente cliente1 = new Cliente("Juan","987654321",LocalDate.of(LocalDate.now().getYear()-27,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Cliente cliente2 = new Cliente("David","876543219",LocalDate.of(LocalDate.now().getYear()-28,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Cliente cliente3 = new Cliente("Vanesa","765432198",LocalDate.of(LocalDate.now().getYear()-29,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Reserva reserva1 = new Reserva(cliente1,LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()),10);
        Reserva reserva2 = new Reserva(cliente1,LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()),20);
        Reserva reserva3 = new Reserva(cliente1,LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()),30);
        Reserva reserva4 = new Reserva(cliente2,LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()),10);
        Reserva reserva5 = new Reserva(cliente2,LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()),20);
        Reserva reserva6 = new Reserva(cliente3,LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()),10); 
        bar1.nuevaReserva(reserva1.getCliente(), reserva1.getFecha(), reserva1.getHora());
        bar1.nuevaReserva(reserva2.getCliente(), reserva2.getFecha(), reserva2.getHora());
        bar2.nuevaReserva(reserva3.getCliente(), reserva3.getFecha(), reserva3.getHora());
        restaurante1.nuevaReserva(reserva4.getCliente(), reserva4.getFecha(), reserva4.getHora());
        restaurante1.nuevaReserva(reserva5.getCliente(), reserva5.getFecha(), reserva5.getHora());
        restaurante2.nuevaReserva(reserva6.getCliente(), reserva6.getFecha(), reserva6.getHora());
        Review review1 = new Review(10,"Maravilloso local",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),bar1,cliente1);
        Review review2 = new Review(9,"Camareros majos",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),bar1,cliente2);
        Review review3 = new Review(8,"Comida deliciosa",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),bar1,cliente3);
        Review review4 = new Review(7,"Un poco lentos",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),pub1,cliente1);
        Review review5 = new Review(6,"Precio caro",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),pub2,cliente2);
        Review review6 = new Review(5,"muy justo",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),restaurante1,cliente1);
        Contestacion contestacion1 = new Contestacion("Muchas gracias por su opinion",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),bar1);
        Contestacion contestacion2 = new Contestacion("Disculpe por los incovenientes",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),pub1);
        Contestacion contestacion3 = new Contestacion("Sentimos el problema, mejoraremos en ese aspecto",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),restaurante1);
        review1.setContestacion(contestacion1);
        review3.setContestacion(contestacion2);
        review6.setContestacion(contestacion3);
        
        // Introducir los datos en el sistema
        BusinessSystem bs = new BusinessSystem();
        bs.nuevoUsuario(propietario1);
        bs.nuevoUsuario(propietario2);
        bs.nuevoUsuario(propietario3);
        bs.nuevoUsuario(propietario4);
        bs.nuevoUsuario(propietario5);
        bs.nuevoUsuario(propietario6);
        bs.nuevoUsuario(propietario7);
        bs.nuevoUsuario(propietario8);
        bs.nuevoUsuario(propietario9);
        bs.nuevoUsuario(cliente1);
        bs.nuevoUsuario(cliente2);
        bs.nuevoUsuario(cliente3);
        bs.nuevoLocal(bar1);
        bs.nuevoLocal(bar2);
        bs.nuevoLocal(bar3);
        bs.nuevoLocal(pub1);
        bs.nuevoLocal(pub2);
        bs.nuevoLocal(pub3);
        bs.nuevoLocal(restaurante1);
        bs.nuevoLocal(restaurante2);
        bs.nuevoLocal(restaurante3);
        bs.nuevaReview(review1);
        bs.nuevaReview(review2);
        bs.nuevaReview(review3);
        bs.nuevaReview(review4);
        bs.nuevaReview(review5);
        bs.nuevaReview(review6);
        bs.nuevaContestacion(contestacion1, review1);
        bs.nuevaContestacion(contestacion2, review3);
        bs.nuevaContestacion(contestacion3, review6);
        bs.nuevaReserva(reserva1.getCliente(), bar1, reserva1.getFecha(), reserva1.getHora());
        bs.nuevaReserva(reserva2.getCliente(), bar1, reserva2.getFecha(), reserva2.getHora());
        bs.nuevaReserva(reserva3.getCliente(), bar2, reserva3.getFecha(), reserva3.getHora());
        bs.nuevaReserva(reserva4.getCliente(), restaurante1, reserva4.getFecha(), reserva4.getHora());
        bs.nuevaReserva(reserva5.getCliente(), restaurante1, reserva5.getFecha(), reserva5.getHora());
        bs.nuevaReserva(reserva6.getCliente(), restaurante2, reserva6.getFecha(), reserva6.getHora());
        
        // Genero el XML del sistema
        File file = new File("bs.xml");
        bs.writeXMLFile(file);
    }
    
}
