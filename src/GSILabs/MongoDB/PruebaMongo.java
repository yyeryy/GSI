package GSILabs.MongoDB;
import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Donacion;
import GSILabs.BModel.Local;
import static GSILabs.BModel.Local.tipoLocal.BAR;
import static GSILabs.BModel.Local.tipoLocal.RESTAURANTE;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Reservable;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import static GSILabs.BModel.Usuario.tipoUsuario.PROPIETARIO;
import GSILabs.BSystem.BusinessSystem;
import static GSILabs.MongoDB.ConexionBBDD.CargarDatos;
import static GSILabs.MongoDB.ConexionBBDD.CargarListaDonaciones;
import static GSILabs.MongoDB.ConexionBBDD.DescargarDatos;
import static GSILabs.MongoDB.ConexionBBDD.DescargarListaDonacion;
import static GSILabs.MongoDB.ConexionBBDD.actualizarDonacionUsuario;
import static GSILabs.MongoDB.ConexionBBDD.actualizarUsuario;
import static GSILabs.MongoDB.ConexionBBDD.descargarDonacionesDisponibles;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;
/*
 * COMO VER LA BASE DE DATOS *
    *1: Descargar MongoDB Compass
    *2: Introducir la siguiente direccion:
        mongodb+srv://GSI:G3GSI2023@gsi.lvvnusj.mongodb.net/
*/
public class PruebaMongo {
    public static void main(String[] args) throws UnsupportedEncodingException, IOException, ClassNotFoundException {
        /*CAMBIAR AL DE FERMIN CUANDO ESTE ACABADO*/
        BusinessSystem bsOriginal = crearBS2(); // Crea un businessSystem poblado
        CargarDatos(bsOriginal); // Sube los datos del BusinessSytem al MongoDB
        BusinessSystem bsNuevo = DescargarDatos(); // Obtiene los datos del MongoDB y los almacena en el BusinessSytem
        
        // Pruebas donaciones
        Usuario usuario1 = new Usuario("Prueba1", "1234", LocalDate.parse("1950-01-01"), CLIENTE);
        Usuario usuario2 = new Usuario("Prueba2", "1234", LocalDate.parse("1950-01-01"),CLIENTE);
        Propietario propietario1 = new Propietario("Prueba3", "1234", LocalDate.parse("1950-01-01"));
        Propietario propietario2 = new Propietario("Prueba4", "1234", LocalDate.parse("1950-01-01"));
        Direccion direccion1 = new Direccion("A","A","A",1);
        Direccion direccion2 = new Direccion("Z","Z","Z",99);
        Local local1 = new Local("Local1",direccion1,"A",BAR,propietario1);
        Local local2 = new Local("Local2",direccion2,"Z",RESTAURANTE,propietario2);

        Donacion donacion1 = new Donacion(local1,"ProductoA",10);
        Donacion donacion10 = new Donacion(local1,"ProductoA0",11);
        Donacion donacion11 = new Donacion(local1,"ProductoA1",12);
        Donacion donacion12 = new Donacion(local1,"ProductoA2",13);
        Donacion donacion13 = new Donacion(local1,"ProductoA3",14);
        Donacion donacion14 = new Donacion(local1,"ProductoA4",15);

        Donacion donacion2 = new Donacion(local2,"ProductoB",20);
        Donacion donacion20 = new Donacion(local2,"ProductoB0",21);
        Donacion donacion21 = new Donacion(local2,"ProductoB1",22);
        Donacion donacion22 = new Donacion(local2,"ProductoB2",23);
        Donacion donacion23 = new Donacion(local2,"ProductoB3",24);
        Donacion donacion24 = new Donacion(local2,"ProductoB4",25);
        donacion1.setUsuario(usuario1);
        
        // meter en el business
        bsNuevo.donaciones.add(donacion1);
        bsNuevo.donaciones.add(donacion10);
        bsNuevo.donaciones.add(donacion11);
        bsNuevo.donaciones.add(donacion12);
        bsNuevo.donaciones.add(donacion13);
        bsNuevo.donaciones.add(donacion14);

        bsNuevo.donaciones.add(donacion2);
        bsNuevo.donaciones.add(donacion20);
        bsNuevo.donaciones.add(donacion21);
        bsNuevo.donaciones.add(donacion22);
        bsNuevo.donaciones.add(donacion23);
        bsNuevo.donaciones.add(donacion24);
        // meter en la base de datos
        CargarListaDonaciones(bsNuevo.donaciones);
        System.out.println("Lista con usuario1: " + descargarDonacionesDisponibles(usuario1));
        System.out.println("Lista sin usuario: " + descargarDonacionesDisponibles(null));
       
        // Prueba actualizar
        donacion2.setUsuario(usuario2);
        actualizarDonacionUsuario(donacion2);
        System.out.println("Lista con usuario2: " + descargarDonacionesDisponibles(usuario2));
        System.out.println("Lista sin usuario: " + descargarDonacionesDisponibles(null));
    }
    
    // FERMIN: Añadir más cosas, cuando este terminado donaciones, meter tbn donaciones.
    public static BusinessSystem crearBS(){
        BusinessSystem bs = new BusinessSystem();

        Random random = new Random();
        Propietario propietario = null;
        Cliente cliente = null;
        
        // Creación de 100 direcciones
        for (int i = 1; i <= 150; i++) {
            Direccion direccion = new Direccion("Ciudad" + i, "Provincia" + i, "Calle" + i, i);
            
            if (i % 3 < 2){
                propietario = new Propietario("Propietario" + i, "1234", LocalDate.now().minusYears(20));
                bs.nuevoUsuario(propietario);

                cliente = new Cliente("Cliente" + i, "1234", LocalDate.now().minusYears(20));
                bs.nuevoUsuario(cliente);
            }
            
            if(i < 33){ // generamos bares
                Bar bar = new Bar("Bar" + i, direccion, "Descripción del Bar" + i, propietario);
                bar.agregarEspecialidad("Especialidad" + i);
                bs.nuevoLocal(bar);
                
                Review review = new Review(random.nextInt(11),"Comentario review "+i,LocalDate.now(),bs.obtenerLocal(bar.getDireccion()),(Cliente) bs.obtenerUsuario(cliente.getNick()));
                bs.nuevaReview(review);
                
                if (i % 2 == 0){
                    Contestacion contestacion = new Contestacion("Comentario contestacion"+i,LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),bar);
                    bs.nuevaContestacion(contestacion, review);
                }
                
                if(i % 5 == 0){
                    bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente.getNick()), (Reservable) bs.obtenerLocal(bar.getDireccion()), LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));
                }
            }
            else if((i > 33) && (i < 66)){ // generamos restaurantes
                Restaurante restaurante = new Restaurante("Restaurante" + i, direccion, "Descripción del Restaurante" + i, propietario, 20, 100, 5);
                bs.nuevoLocal(restaurante);
                
                Review review = new Review(random.nextInt(11),"Comentario review "+i,LocalDate.now(),bs.obtenerLocal(restaurante.getDireccion()),(Cliente) bs.obtenerUsuario(cliente.getNick()));
                bs.nuevaReview(review);
                
                if (i % 2 == 0){
                    Contestacion contestacion = new Contestacion("Comentario contestacion "+i,LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),restaurante);
                    bs.nuevaContestacion(contestacion, review);
                }
                
                if(i % 5 == 0){
                    bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente.getNick()), (Reservable) bs.obtenerLocal(restaurante.getDireccion()), LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));
                }
            }
            else{ // generamos pubs
                Pub pub = new Pub("10:00", "2:00", "Pub" + i, direccion, "Descripción del Pub" + i, propietario);
                bs.nuevoLocal(pub);
                
                Review review = new Review(random.nextInt(11),"Comentario review "+i,LocalDate.now(),bs.obtenerLocal(pub.getDireccion()),(Cliente) bs.obtenerUsuario(cliente.getNick()));
                bs.nuevaReview(review);
                
                if (i % 2 == 0){
                    Contestacion contestacion = new Contestacion("Comentario contestacion "+i,LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),pub);
                    bs.nuevaContestacion(contestacion, review);
                }
                
                /*if(i % 5 == 0){
                    bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente.getNick()), (Reservable) bs.obtenerLocal(pub.getDireccion()), LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));
                }*/
            }
        }

        return bs;
    }
    public static BusinessSystem crearBS2(){ // no la utilizamos pero viene bien tenerla presente, la quietaremos en la entrega
        Direccion direccion1 = new Direccion("Pamplona","Navarra","Calle Mayor",1);
        Direccion direccion2 = new Direccion("Pamplona","Navarra","Calle Amaya",2);
        Direccion direccion3 = new Direccion("Pamplona","Navarra","Avenida Cataluña",3);
        Direccion direccion4 = new Direccion("Villava","Navarra","Avenida Fermin Tirapu",4);
        Direccion direccion5 = new Direccion("Burlada","Navarra","Calle Landazabal",5);
        Direccion direccion6 = new Direccion("Barcelona","Barcelona","Plaza de España",6);
        Direccion direccion7 = new Direccion("Madrid","Madrid","Calle Alcala",7);
        Direccion direccion8 = new Direccion("Bilbao","Bizkaia","Gran Via",8);
        Direccion direccion9 = new Direccion("Tudela","Navarra", "Plaza Estación",9);
        Direccion direccion10 = new Direccion("Barcelona","Barcelona","Sant Adria",10);
        Direccion direccion11 = new Direccion("Madrid","Madrid","Pozuelo",11);
        Direccion direccion12 = new Direccion("Getafe","Madrid","Avenida de la Hispanidad",11);
        Direccion direccion13 = new Direccion("Alsasua","Navarra","Kalea Kalea",11);
        Direccion direccion14 = new Direccion("Vigo","Pontevedra","Rias Baixas",11);
        Direccion direccion15 = new Direccion("Logroño","La Rioja","Calle Laurel",11);
        Direccion direccion16 = new Direccion("Burgos","Burgos","Plaza de la Catedral",11);
        Direccion direccion17 = new Direccion("Almeria","Almeria","Boulevard Sur",11);
        

        
        //Creación de propietarios
        Propietario propietario1 = new Propietario("Antonio","123456789",LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario2 = new Propietario("María Carmen","234567891", LocalDate.of(LocalDate.now().getYear()-19,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario3 = new Propietario("María","345678912",LocalDate.of(LocalDate.now().getYear()-20,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario4 = new Propietario("Manuel","456789123",LocalDate.of(LocalDate.now().getYear()-21,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario5 = new Propietario("Jose","567891234",LocalDate.of(LocalDate.now().getYear()-22,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario6 = new Propietario("Francisco","678912345",LocalDate.of(LocalDate.now().getYear()-23,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario7 = new Propietario("Carmen","789123456",LocalDate.of(LocalDate.now().getYear()-24,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario8 = new Propietario("David","891234567",LocalDate.of(LocalDate.now().getYear()-25,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Propietario propietario9 = new Propietario("Ana","912345678",LocalDate.of(LocalDate.now().getYear()-26,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        
        //Creación de bares
        Bar bar1 = new Bar("Nara",direccion1,"Bar antiguo cerca del Ayuntamiento", propietario1);
        Bar bar2 = new Bar("El Rincon Bohemio", direccion2, "Bar con música y decoracion local", propietario2);
        Bar bar3 = new Bar("Bar Txola", direccion3,"Luz tenue y buenos cocteles",propietario3);
        Bar bar4 = new Bar("Hulo",direccion4,"Bar antiguo cerca del Ayuntamiento", propietario1);
        Bar bar5 = new Bar("El Rincon de Luis", direccion5, "Bar con música y decoracion local", propietario2);
        Bar bar6 = new Bar("Bar Gaga", direccion6,"Luz tenue y buenos cocteles",propietario3);
        Bar bar7 = new Bar("Mejiros",direccion7,"Bar antiguo cerca del Ayuntamiento", propietario1);
        Bar bar8 = new Bar("El juli Bohemio", direccion8, "Bar con música y decoracion local", propietario2);
        Bar bar9 = new Bar("Txola manin", direccion9,"Luz tenue y buenos cocteles",propietario3);
        Bar bar10 = new Bar("El juli Romano", direccion10, "Bar con música y decoracion local", propietario2);
        Bar bar11 = new Bar("Txola roncola", direccion11,"Luz tenue y buenos cocteles",propietario3);
        
        //Creación de restaurantes
        Restaurante restaurante1 = new Restaurante("Sotano encantado",direccion12,"Sotano con iluminacion de velas",propietario4,20,140,4);
        Restaurante restaurante2 = new Restaurante("Cantina estelar",direccion13,"Mejor restaurante para comer en los descansos",propietario5,8,250,10);
        Restaurante restaurante3 = new Restaurante("La cervecería mística",direccion14,"Disfruta de tus comidas acompañadas de la mejor cerveza",propietario6,15,75,8);
        
        //Creación de pubs
        Pub pub1 = new Pub("10:00","2:00","El refugio del Jazz",direccion15,"Sumergete entre copas y Jazz",propietario7);
        Pub pub2 = new Pub("11:00","3:00","Tabanco del Río",direccion16,"Tradición española acompañada de las mejores bebidas",propietario8);
        Pub pub3 = new Pub("10:00","13:00","Luna Roja",direccion17,"Pub con una ambientacion bohemia y encantadora",propietario9);
        
        //Adición de Propiedades
        bar1.addPropietario(propietario2);
        bar1.addPropietario(propietario3);
        bar2.addPropietario(propietario1);
        bar3.addPropietario(propietario4);
        bar4.addPropietario(propietario5);
        bar5.addPropietario(propietario6);
        bar6.addPropietario(propietario7);
        bar7.addPropietario(propietario8);
        bar8.addPropietario(propietario9);
        bar9.addPropietario(propietario3);
        bar10.addPropietario(propietario9);
        bar11.addPropietario(propietario9);
        
        restaurante1.addPropietario(propietario5);
        restaurante1.addPropietario(propietario6);
        restaurante2.addPropietario(propietario6);
        
        pub1.addPropietario(propietario8);
        pub1.addPropietario(propietario9);
        pub2.addPropietario(propietario9);
        
        bar1.agregarEspecialidad("Mexicano");
        bar1.agregarEspecialidad("Mexicano2");
        bar2.agregarEspecialidad("Italiano");
        bar3.agregarEspecialidad("Frances");
        bar4.agregarEspecialidad("Frances");
        bar5.agregarEspecialidad("Frances");
        bar6.agregarEspecialidad("Frances");
        bar7.agregarEspecialidad("Frances");
        bar8.agregarEspecialidad("Frances");
        bar9.agregarEspecialidad("Frances");
        bar10.agregarEspecialidad("Frances");
        bar11.agregarEspecialidad("Frances");
        
        Cliente cliente1 = new Cliente("Juan","987654321",LocalDate.of(LocalDate.now().getYear()-27,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Cliente cliente2 = new Cliente("Ivan","876543219",LocalDate.of(LocalDate.now().getYear()-28,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));
        Cliente cliente3 = new Cliente("Vanesa","765432198",LocalDate.of(LocalDate.now().getYear()-29,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()));

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
        bs.nuevoLocal(bar4);
        bs.nuevoLocal(bar5);
        bs.nuevoLocal(bar6);
        bs.nuevoLocal(bar7);
        bs.nuevoLocal(bar8);
        bs.nuevoLocal(bar9);
        bs.nuevoLocal(bar10);
        bs.nuevoLocal(bar11);
        
        bs.nuevoLocal(pub1);
        bs.nuevoLocal(pub2);
        bs.nuevoLocal(pub3);
        bs.nuevoLocal(restaurante1);
        bs.nuevoLocal(restaurante2);
        bs.nuevoLocal(restaurante3);

        Review review1 = new Review(5,"Maravilloso local",LocalDate.parse("2050-12-27"),bs.obtenerLocal(bar1.getDireccion()),(Cliente) bs.obtenerUsuario(cliente1.getNick()));
        Review review2 = new Review(3,"Camareros majos",LocalDate.parse("2055-12-27"),bs.obtenerLocal(bar2.getDireccion()),(Cliente) bs.obtenerUsuario(cliente2.getNick()));
        Review review3 = new Review(2,"Comida deliciosa",LocalDate.parse("2060-12-27"),bs.obtenerLocal(bar3.getDireccion()),(Cliente) bs.obtenerUsuario(cliente3.getNick()));
        Review review4 = new Review(1,"Un poco lentos",LocalDate.parse("2065-12-27"),bs.obtenerLocal(pub1.getDireccion()),(Cliente) bs.obtenerUsuario(cliente1.getNick()));
        Review review5 = new Review(3,"Precio caro",LocalDate.parse("2070-12-27"),bs.obtenerLocal(pub1.getDireccion()),(Cliente) bs.obtenerUsuario(cliente2.getNick()));
        Review review6 = new Review(5,"muy justo",LocalDate.parse("2080-12-27"),bs.obtenerLocal(restaurante1.getDireccion()),(Cliente) bs.obtenerUsuario(cliente3.getNick()));
        Review review7 = new Review(5,"Maravilloso local",LocalDate.parse("2050-12-17"),bs.obtenerLocal(bar4.getDireccion()),(Cliente) bs.obtenerUsuario(cliente1.getNick()));
        Review review8 = new Review(5,"Camareros majos",LocalDate.parse("2055-12-17"),bs.obtenerLocal(bar5.getDireccion()),(Cliente) bs.obtenerUsuario(cliente2.getNick()));
        Review review9 = new Review(5,"Comida deliciosa",LocalDate.parse("2060-12-17"),bs.obtenerLocal(bar6.getDireccion()),(Cliente) bs.obtenerUsuario(cliente3.getNick()));
        Review review10 = new Review(5,"Comida deliciosa",LocalDate.parse("2060-12-18"),bs.obtenerLocal(bar7.getDireccion()),(Cliente) bs.obtenerUsuario(cliente3.getNick()));
        Review review11 = new Review(5,"Comida deliciosa",LocalDate.parse("2060-12-19"),bs.obtenerLocal(bar8.getDireccion()),(Cliente) bs.obtenerUsuario(cliente3.getNick()));
        Review review12 = new Review(5,"Comida deliciosa",LocalDate.parse("2060-12-20"),bs.obtenerLocal(bar9.getDireccion()),(Cliente) bs.obtenerUsuario(cliente3.getNick()));
        Review review13 = new Review(5,"Comida deliciosa",LocalDate.parse("2060-12-21"),bs.obtenerLocal(bar10.getDireccion()),(Cliente) bs.obtenerUsuario(cliente3.getNick()));
        Review review14 = new Review(5,"Comida deliciosa",LocalDate.parse("2060-12-22"),bs.obtenerLocal(bar11.getDireccion()),(Cliente) bs.obtenerUsuario(cliente3.getNick()));
        
        
        bs.nuevaReview(review1);
        bs.nuevaReview(review2);
        bs.nuevaReview(review3);
        bs.nuevaReview(review4);
        bs.nuevaReview(review5);
        bs.nuevaReview(review6);
        bs.nuevaReview(review7);
        bs.nuevaReview(review8);
        bs.nuevaReview(review9);
        bs.nuevaReview(review10);
        bs.nuevaReview(review11);
        bs.nuevaReview(review12);
        bs.nuevaReview(review13);
        bs.nuevaReview(review14);

        Contestacion contestacion1 = new Contestacion("Muchas gracias por su opinion",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),bar1);
        Contestacion contestacion2 = new Contestacion("Disculpe por los incovenientes",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),pub1);
        Contestacion contestacion3 = new Contestacion("Sentimos el problema, mejoraremos en ese aspecto",LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),restaurante1);


        bs.nuevaContestacion(contestacion1, review1);
        bs.nuevaContestacion(contestacion2, review3);
        bs.nuevaContestacion(contestacion3, review6);


        bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente1.getNick()), (Reservable) bs.obtenerLocal(bar1.getDireccion()), LocalDate.parse("2050-12-27"), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));

        bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente1.getNick()), (Reservable) bs.obtenerLocal(bar1.getDireccion()), LocalDate.parse("2060-12-27"), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));     
        bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente2.getNick()), (Reservable) bs.obtenerLocal(bar2.getDireccion()), LocalDate.parse("2060-12-15"), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));
        bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente3.getNick()), (Reservable) bs.obtenerLocal(bar3.getDireccion()), LocalDate.parse("2070-12-01"), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));

        bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente1.getNick()), (Reservable) bs.obtenerLocal(restaurante1.getDireccion()), LocalDate.parse("2050-12-27"), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));
        bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente2.getNick()), (Reservable) bs.obtenerLocal(restaurante2.getDireccion()), LocalDate.parse("2060-12-15"), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));
        bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente3.getNick()), (Reservable) bs.obtenerLocal(restaurante3.getDireccion()), LocalDate.parse("2070-12-01"), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));
        
        return bs;
    }
}