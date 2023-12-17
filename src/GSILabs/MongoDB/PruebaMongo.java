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
        BusinessSystem bsOriginal = crearBS(); // Crea un businessSystem poblado
        CargarDatos(bsOriginal); // Sube los datos del BusinessSytem al MongoDB
        BusinessSystem bsNuevo = DescargarDatos(); // Obtiene los datos del MongoDB y los almacena en el BusinessSytem
    }
    
    public static BusinessSystem crearBS(){
        BusinessSystem bs = new BusinessSystem();

        Random random = new Random();
        Propietario propietario = null;
        Cliente cliente = null;
 
        String[] nombresLocales = {"La Basílica de la Sagrada Familia, Barcelona","El Palacio Real de Madrid","La Catedral de Sevilla",
        "La Plaza Mayor de Madrid","El Museo del Prado de Madrid","La Alhambra de Granada","La Mezquita-Catedral de Córdoba",
        "El Parque Güell de Barcelona","El Caminito del Rey, Málaga","El Taj Mahal, India","El Gran Cañón, Estados Unidos",
        "La Torre de Pisa, Italia","El Partenón, Grecia","El Gran Buda de Leshan, China","El Cristo Redentor, Brasil",
        "La Ópera de Sídney, Australia","La Estatua de la Libertad, Estados Unidos","El Gran Barrera de Coral, Australia",
        "El Parque Nacional de Yellowstone, Estados Unidos","El Parque Nacional de Yosemite, Estados Unidos",
        "El Amazonas, Sudamérica","El Kilimanjaro, Tanzania","Las Cataratas del Niágara, Estados Unidos/Canadá",
        "El Monte Everest, Nepal/China","El Lago Baikal, Rusia","La Gran Muralla China",
        "Machu Picchu, Perú","Chichén Itzá, México",
        "Petra, Jordania","El Coliseo de Roma, Italia",
        "La Acrópolis de Atenas, Grecia","Las pirámides de Giza, Egipto",
        "Angkor Wat, Camboya","El desierto de Sahara, África",
        "La selva amazónica, Sudamérica","El monte Fuji, Japón",
        "La bahía de Ha Long, Vietnam","El monte Kilimanjaro, África",
        "El Gran Cañón, Estados Unidos","El Gran Barrera de Coral, Australia",
        "Las cataratas del Niágara, Estados Unidos/Canadá","La ciudad de Nueva York, Estados Unidos",
        "La ciudad de Londres, Reino Unido","La ciudad de París, Francia","La ciudad de Tokio, Japón",
        "La ciudad de Roma, Italia","La ciudad de Pekín, China","La ciudad de Estambul, Turquía",
        "La ciudad de Dubai, Emiratos Árabes Unidos","La ciudad de Río de Janeiro, Brasil",
        "La ciudad de Buenos Aires, Argentina","La ciudad de Ciudad de México, México",
        "La ciudad de Bogotá, Colombia","La ciudad de Lima, Perú","La ciudad de Santiago de Chile, Chile",
        "La ciudad de Johannesburgo, Sudáfrica","La ciudad de Cape Town, Sudáfrica","La ciudad de Sydney, Australia",
        "La ciudad de Melbourne, Australia","La ciudad de Brisbane, Australia","La ciudad de Perth, Australia",
        "La ciudad de Auckland, Nueva Zelanda","La ciudad de Wellington, Nueva Zelanda","La ciudad de Nueva York, Estados Unidos",
        "La ciudad de Londres, Reino Unido1","La ciudad de París, Francia1","La ciudad de Tokio, Japón1",
        "La ciudad de Roma, Italia1","La ciudad de Pekín, China1","La ciudad de Estambul, Turquía1",
        "La ciudad de Dubai, Emiratos Árabes Unidos1","La ciudad de Río de Janeiro, Brasil1","La ciudad de Buenos Aires, Argentina1",
        "La ciudad de Ciudad de México, México1","La ciudad de Bogotá, Colombia1","La ciudad de Lima, Perú1",
        "La ciudad de Santiago de Chile, Chile1","La ciudad de Johannesburgo, Sudáfrica1",
        "La ciudad de Cape Town, Sudáfrica1","La ciudad de Sydney, Australia1","La ciudad de Melbourne, Australia1",
        "La ciudad de Brisbane, Australia1","La ciudad de Perth, Australia1","La ciudad de Auckland, Nueva Zelanda1",
        "La ciudad de Wellington, Nueva Zelanda1","El glaciar Perito Moreno, Argentina","El glaciar Vatnajökull, Islandia",
        "El glaciar Aletsch, Suiza","El glaciar Mendenhall, Alaska","El glaciar Columbia, Canadá",
        "El glaciar Franz Josef, Nueva Zelanda","El glaciar Perito Moreno, Argentina1","El glaciar Vatnajökull, Islandia",
        "El glaciar Aletsch, Suiza","El glaciar Mendenhall, Alaska","El glaciar Columbia, Canadá",
        "El glaciar Columbia, Canadá1","El glaciar Columbia, Canadá2","El glaciar Columbia, Canadá3",
        "El glaciar Columbia, Canadá4","El glaciar Franz Josef, Nueva Zelanda"};
        
        String[] nombresPersonas = {"Juan", "Pedro", "Luis", "José", "Antonio", "Francisco", "Manuel", "David", "Carlos", 
            "José María", "Juan Carlos", "Miguel",
            "Carmen", "Isabel", "María José", "Laura", "Marta", "Ana María", "Elena", "Teresa", "María Isabel", "Ana",
            "Alejandro", "Daniel", "David", "Eduardo", "Francisco Javier", "Gabriel", "Javier", "José Luis", "Juan Pablo", "Luis Miguel",
            "Manuel", "Miguel Ángel", "Pablo", "Raúl", "Rodrigo", "Sergio", "Víctor",
            "Alejandra", "Andrea", "Claudia", "Cristina", "Daniela", "Elena", "Eva", "Laura", "Lucía", "María Carmen",
            "María Dolores", "María José", "Marta", "Patricia", "Raquel", "Sara",
            "Adrián", "Álvaro", "Antonio José", "Bruno", "Carlos Javier", "Diego", "Enrique", "Fernando", "Gabriel Alejandro", "Gonzalo", 
            "Hugo", "Ignacio", "Javier Alejandro", "Jesús", "José Antonio", "José Luis", "Lucas", "Mateo", "Pablo Andrés",
            "Alejandra María", "Andrea Sofía", "Claudia María", "Cristina María", "Daniela Lucía", "Elena María", "Eva María", "Laura María", 
            "Lucía María", "María Alejandra", "María Carmen Lucía", "María Dolores María", "María José María", "Marta María", "Patricia María", 
            "Raquel María", "Sara María", "Sofía María",
            "Aarón", "Alberto Alejandro", "Alejandro David", "Álvaro Manuel", "Ángel Javier", "Antonio David", "Bruno José", "Carlos Alberto", 
            "Daniel Andrés", "David Alejandro", "Diego Francisco", "Enrique José", "Fernando Manuel", "Gabriel David", "Gonzalo Andrés", "Hugo David", 
            "Ignacio José", "Javier David", "Jesús Manuel", "José Antonio David", "José Luis Manuel", "Lucas David", "Mateo Alejandro", 
            "Pablo Manuel", "Raúl David", "Rodrigo Manuel", "Sergio David", "Víctor Andrés",
            "Aitor", "Alejandro Gabriel", "Álvaro David", "Bruno Alejandro", "Carlos Daniel", "Daniel Alejandro", "David Manuel", "Diego José", 
            "Eduardo Javier", "Enrique David", "Fernando José", "Gabriel Andrés", "Gonzalo David", "Hugo Alejandro", "Ignacio David", 
            "Javier Manuel", "Jesús Andrés", "José Antonio Manuel", "José Luis Andrés", "Lucas Alejandro", "Mateo David", "Pablo Alberto", 
            "Raúl Andrés", "Rodrigo David", "Sergio Andrés", "Víctor David"};
        
        String[] nombresEspecialidades = {"Español","Frances","Italiano","Bulgaro","Rumano"};
        
        String[] nombresDescripciones = {"De toda la vida","Siempre lleno de gente","Da ganas de entrar",
            "Buena comida","Comida de toda la vida","Con estilo","Buen ambiente","Gente de todo tipo",
            "Menu del dia","Comida para grandes y pequeños","Siempre hay sitio para ti"};
        
        String[] nombresComentarios = {"Abierto", "Abundante", "Acogedor", "Admirable", "Agradable", "Alegre", 
            "Amoroso", "Apetitoso", "Atractivo", "Aventurado", "Bello", "Bendito", "Bonito", "Brillante", 
            "Cálido", "Cargado", "Cariñoso", "Cautivante", "Cierto", "Chévere", "Curioso", "Delicioso", "Dinámico", 
            "Divertido", "Ecléctico", "Elegante", "Emocionante", "Encantador", "Enérgico", "Enriquecedor", 
            "Espléndido", "Espectacular", "Estupendo", "Exquisito", "Fascinante", "Feliz", "Fuerte", "Gentil", 
            "Gracioso", "Gustadísimo", "Hermoso", "Increíble", "Inmenso", "Innovador", "Interesante", "Intenso", 
            "Inspirador", "Irrepetible", "Lindo", "Magnífico", "Maravilloso", "Merecido", "Mágico", "Moderno", 
            "Original", "Pura", "Real", "Rico", "Saboroso", "Sano", "Sensual", "Simpático", "Sorprendente", 
            "Sutil", "Único", "Valioso", "Verdadero", "Vital", "Viviente"};
        
        String[] nombresAlimentos = {"Pan", "leche", "huevos", "queso", "carne", "pescado", "arroz", "pasta", "patatas", "verduras", 
            "frutas", "frutos secos", "semillas", "legumbres", "yogur", "miel", "azúcar", "aceite", "vinagre", "sal", "pimienta", 
            "especias", "hierbas aromáticas", "salsas", "bebidas alcohólicas", "bebidas no alcohólicas"};
        
        String[] nombresCiudades = {"Pamplona", "Barcelona", "Madrid", "Bilbao", "Tudela", "Barcelona", 
            "Madrid", "Sevilla", "Valencia", "Málaga", "Zaragoza", "Alicante", "Córdoba", "Granada", "Murcia", 
            "Vigo", "Gijón", "Santander", "Oviedo", "Donostia-San Sebastián", "Logroño", "Salamanca", "Toledo", 
            "Cáceres", "Ávila", "Cuenca", "León", "Huelva", "Jaén", "Almería", "Palma de Mallorca", "Santa Cruz de Tenerife", 
            "Las Palmas de Gran Canaria", "Melilla", "Ceuta", "Lugo", "Pontevedra", "Ourense", "Ferrol", "Santiago de Compostela"};
        
        String[] nombresProvincias = {"Navarra", "Barcelona", "Madrid", "Bizkaia", "Navarra", "Barcelona", "Madrid", "Sevilla", "Valencia", 
            "Málaga", "Zaragoza", "Alicante", "Córdoba", "Granada", "Murcia", "Pontevedra", "Asturias", "Cantabria", "Asturias", "Guipúzcoa", 
            "La Rioja", "Salamanca", "Toledo", "Cáceres", "Ávila", "Cuenca", "León", "Huelva", "Jaén", "Almería", "Baleares", 
            "Santa Cruz de Tenerife", "Las Palmas", "Melilla", "Ceuta", "Lugo", "Pontevedra", "Ourense", "A Coruña", "Santiago de Compostela"};
        
        String[] nombresCalles = {"Mayor", "Avenida", "Calle", "Plaza", "Amaya", "Cataluña", "Fermin Tirapu", "Landazabal", 
            "España", "Paz", "Marqués de Larios", "Independencia", "Maisonnave", "Laurel", "Trapería", "Príncipe", "Corrida", "Uría", 
            "Marina", "Ciudad", "Laurel", "Franco", "San Juan", "Virgen de la Montaña", "Toledo", "Mayor", "Santa Clara", "Paseo", 
            "Real", "Rúa Nova", "Santa Clara", "Paseo del Revellín", "Cruz", "Príncipe", "Ancha", "Calleja de las Flores", "Calle Mayor de Triana", 
            "Carlos V", "Plaza del Revellín", "Kalea Kalea", "Rias Baixas", "Calle Laurel", "Plaza de la Catedral", "Boulevard Sur"};
        
        String[] nombresContestaciones = {"Muchas gracias","Callate","Jajajaja","Gracias","Hasta pronto"};
        
        for (int i = 1; i <= 100; i++) {
            Direccion direccion = new Direccion(nombresCiudades[i%nombresCiudades.length],nombresProvincias[i%nombresProvincias.length], 
                    nombresCalles[i%nombresCalles.length], i);
            
            if (i % 3 < 2){
                propietario = new Propietario(nombresPersonas[i]+" jr", ""+(i+111111111), LocalDate.of(1950+((i+1)%50), (i%11)+1, (i%25)+1));
                bs.nuevoUsuario(propietario);

                cliente = new Cliente(nombresPersonas[i], ""+(i+111111111), LocalDate.of(1950+((i+1)%50), (i%11)+1, (i%25)+1));
                bs.nuevoUsuario(cliente);
            }
            
            if(i < 33){ // generamos bares
                Bar bar = new Bar("Bar "+nombresLocales[i], direccion, nombresDescripciones[i%nombresDescripciones.length], propietario);
                bar.agregarEspecialidad(nombresEspecialidades[i%nombresEspecialidades.length]);
                bs.nuevoLocal(bar);
                
                Review review = new Review(random.nextInt(11),nombresComentarios[i%nombresComentarios.length],LocalDate.of(1950+((i+1)%50), (i%11)+1, (i%25)+1),bs.obtenerLocal(bar.getDireccion()),(Cliente) bs.obtenerUsuario(cliente.getNick()));
                bs.nuevaReview(review);
                
                if (i % 2 == 0){
                    Donacion donacion = new Donacion(bar,nombresAlimentos[i%nombresAlimentos.length],(i%40)+5);
                    donacion.setUsuario((Cliente) bs.obtenerUsuario(cliente.getNick()));
                    bs.donaciones.add(donacion);
                    
                    Contestacion contestacion = new Contestacion(nombresContestaciones[i%nombresContestaciones.length],LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),bar);
                    bs.nuevaContestacion(contestacion, review);
                }
                
                if(i % 5 == 0){
                    bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente.getNick()), (Reservable) bs.obtenerLocal(bar.getDireccion()), LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));
                }
            }
            else if((i > 33) && (i < 66)){ // generamos restaurantes
                Restaurante restaurante = new Restaurante("Restaurante "+nombresLocales[i], direccion, nombresDescripciones[i%nombresDescripciones.length], propietario, 20, 100, 5);
                bs.nuevoLocal(restaurante);
                
                Review review = new Review(random.nextInt(11),nombresComentarios[i%nombresComentarios.length],LocalDate.of(1950+((i+1)%50), (i%11)+1, (i%25)+1),bs.obtenerLocal(restaurante.getDireccion()),(Cliente) bs.obtenerUsuario(cliente.getNick()));
                bs.nuevaReview(review);
                
                if (i % 2 == 0){
                    Donacion donacion = new Donacion(restaurante,nombresAlimentos[i%nombresAlimentos.length],(i%40)+5);
                    donacion.setUsuario((Cliente) bs.obtenerUsuario(cliente.getNick()));
                    bs.donaciones.add(donacion);
                    
                    Contestacion contestacion = new Contestacion(nombresContestaciones[i%nombresContestaciones.length],LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),restaurante);
                    bs.nuevaContestacion(contestacion, review);
                }
                
                if(i % 5 == 0){
                    bs.nuevaReserva((Cliente) bs.obtenerUsuario(cliente.getNick()), (Reservable) bs.obtenerLocal(restaurante.getDireccion()), LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute()));
                }
            }
            else{ // generamos pubs
                Pub pub = new Pub(((i%12)+8)+":00", (i%12)+":00", "Pub "+nombresLocales[i], direccion, nombresDescripciones[i%nombresDescripciones.length], propietario);
                bs.nuevoLocal(pub);
                
                Review review = new Review(random.nextInt(11),nombresComentarios[i%nombresComentarios.length],LocalDate.of(1950+((i+1)%50), (i%11)+1, (i%25)+1),bs.obtenerLocal(pub.getDireccion()),(Cliente) bs.obtenerUsuario(cliente.getNick()));
                bs.nuevaReview(review);
                
                if (i % 2 == 0){
                    Donacion donacion = new Donacion(pub,nombresAlimentos[i%nombresAlimentos.length],(i%40)+5);
                    donacion.setUsuario((Cliente) bs.obtenerUsuario(cliente.getNick()));
                    bs.donaciones.add(donacion);
                    
                    Contestacion contestacion = new Contestacion(nombresContestaciones[i%nombresContestaciones.length],LocalDate.of(LocalDate.now().getYear()+1,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()),pub);
                    bs.nuevaContestacion(contestacion, review);
                }
            }
        }
        CargarListaDonaciones(bs.donaciones);

        return bs;
    }
}
