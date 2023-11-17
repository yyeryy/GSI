package GSILabs.BSystem;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Local.tipoLocal;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import GSILabs.BModel.Usuario.tipoUsuario;
import GSILabs.connect.AdminGateway;
import GSILabs.connect.ClientGateway;
import GSILabs.persistence.XMLParsingException;
import static GSILabs.persistence.parser.obtenerContenidoEtiqueta;
import static GSILabs.persistence.parser.parseBar;
import static GSILabs.persistence.parser.parseCliente;
import static GSILabs.persistence.parser.parseLocal;
import static GSILabs.persistence.parser.parsePropietario;
import static GSILabs.persistence.parser.parsePub;
import static GSILabs.persistence.parser.parseRestaurante;
import static GSILabs.persistence.parser.parseReview;
import static GSILabs.persistence.parser.parseUsuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
//import jdk.internal.net.http.common.Pair;

/**
 * Clase PublicBusinessSystem
 * Clase que extiende a la clase BusinessSystem, para la implementación de las
 * interfaces ClientGateway y AdminGateway.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 15.11.2023
 */
public class PublicBusinessSystem extends BusinessSystem implements ClientGateway, AdminGateway {

    /**
     * Incorpora una nueva review al sistema, en caso de que sus datos (Usuario, 
     * Local) sean correctos y no haya otra introducida para la misma fecha.
     * @param r La review a introducir al sistema.
     * @return True si y solo si la operacion fue completada.
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public boolean insertaReview(Review r) throws RemoteException {
        return nuevaReview(r);
    }

    /**
     * Elimina una review del sistema, siempre y cuando exista y no tenga una 
     * contestacion asociada.
     * @param r Review a eliminar
     * @return True si y solo si la operacion fue completada.
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public boolean quitaReview(Review r) throws RemoteException {
        return eliminaReview(r);
    }

    /**
     * Devuelve el mejor bar de la cuidad que coincida con el nombre.
     * @param ciudad nombre de la cuidad
     * @return El bar con mejor puntuación media de la cuidad
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public Bar mejorBar(String ciudad) throws RemoteException {
        //No nos dan provinvia
        Bar mejorBar = null;
        Review[] reviewsLocal;
        int valoracionMax = 0;
        for (Local local : locales) {
            int valoracionMedia = 0;
            if(local.getDireccion().getLocalidad().equalsIgnoreCase(ciudad) && local.getTipo() == tipoLocal.BAR){
                reviewsLocal = verReviews(local);
                for (int i = 0; i < reviewsLocal.length; i++) {
                    valoracionMedia += reviewsLocal[i].getValoracion();
                }
                if(valoracionMax < valoracionMedia){
                    valoracionMax = valoracionMedia;
                    mejorBar = (Bar) local;
                }
            }
        }
        return mejorBar;
    }

    /**
     * Devuelve los num restaurantes de la cuidad con mejores puntuaciones.
     * @param ciudad nombre de la cuidad
     * @param num numero de restaurantes
     * @return Una tabla con Num restaurantes, conteniendo nulls en caso de que no existan tantos.
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public Restaurante[] mejoresRestaurantes(String ciudad, Integer num) throws RemoteException {
        Restaurante[] mejoresRestaurantes = new Restaurante[num];
        Integer[] valoraciones = new Integer[num];
        int index = 0;
        Review[] reviewsLocal;
        for (Local local : locales) {
            int valoracionMedia = 0;
            if(local.getDireccion().getLocalidad().equalsIgnoreCase(ciudad) && local.getTipo() == tipoLocal.RESTAURANTE){
                reviewsLocal = verReviews(local);
                for (int i = 0; i < reviewsLocal.length; i++) {
                    valoracionMedia += reviewsLocal[i].getValoracion();
                }
                if (index < num || valoracionMedia > valoraciones[0]) {
                    // Agrega el restaurante si hay espacio o si su valoración es mayor que la de los existentes
                    mejoresRestaurantes[0] = (Restaurante) local;
                    valoraciones[0] = valoracionMedia;
                    Arrays.sort(mejoresRestaurantes);
                    Arrays.sort(valoraciones); 
                    if (index < num) {
                        index++; // Incrementa solo si hay espacio en el array
                    }
                }
            }
        }
        return mejoresRestaurantes;
    }

    /**
     * Retrieves an Locals that matches EXACTLY (up to the case and/or spacing),
     * the name.
     * @param name Name of the local
     * @return The local , or a null response
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public Local getLocal(String name) throws RemoteException {
        for (Local local : locales) {
            if (local.getNombre().equals(name)) {
                return local;
            }
        }
        return null;
    }

    /**
     * Retrieves all the Locals that match, either partial o totally (up to the case and/or spacing),
     * the name given as argument.
     * @param name Complete or partial name of the ñpcañ
     * @return The list of local, that might eventually contain zero elements
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public Local[] getLocals(String name) throws RemoteException {
        List<Local> localesEncontrados = new ArrayList<>();
        for (Local local : locales) {
            if (local.getNombre().toLowerCase().contains(name.toLowerCase().trim())) {
                localesEncontrados.add(local);
            }
        }
        return localesEncontrados.toArray(new Local[0]);
    }

    /**
     * Elimina un local del sistema
     * @param l Local a eliminar
     * @return  True sí y sólo si se pudo completar la tarea
     * @throws RemoteException If some error happens in the remove invocation.
     */
    @Override
    public Boolean eliminaLocal(Local l) throws RemoteException {
        return eliminarLocal(l);
    }

    /**
    * Elimina todas la reviews (y contestaciones asociadas) de un local
    * @param l Local cuyas reviews deben eliminarse
    * @return True sí y sólo si se pudo completar la tarea
    * @throws RemoteException If some error happens in the remove invocation.
    */
    @Override
    public Boolean eliminaReviewsDeLocal(Local l) throws RemoteException {
        boolean revisionesEliminadas = false;
        for(Review review : reviews){
            if (review.getLocal().equals(l)) {
                eliminaReview(review);
                Contestacion c = obtenerContestacion(review);
                eliminaContestacion(c);
                revisionesEliminadas = true;
            }
        }
        return revisionesEliminadas;
    }

    /**
    * Elimina una review determinada del sistema
    * @param r Review a eliminar
    * @return True sí y sólo si se pudo completar la tarea
    * @throws RemoteException If some error happens in the remove invocation.
    */
    @Override
    public Boolean eliminarReview(Review r) throws RemoteException {
        if (reviews.contains(r)) {
            reviews.remove(r);
            return true;
        }
        return false;  
    }

    /**
    * Elimina todas la reviews de un cliente determinado
    * @param c Cliente cuyas reviews deben ser eliminadas
    * @return Número de reviews eliminadas, o -1 si el cliente no existe.
    * @throws RemoteException If some error happens in the remove invocation.
    */
    @Override
    public Integer eliminaReviewsDeUsuario(Cliente c) throws RemoteException {
        int reviewsEliminadas = 0;
        for(Review review : reviews){
            if (review.getUsuario().equals(c)) {
                eliminaReview(review);
                reviewsEliminadas++;
            }
        }
        if (reviewsEliminadas > 0) {
            return reviewsEliminadas;
        } else {
            return -1;
        }
    }

    /**
    * Crea un usuario falso al que se le añade una review falsa con la puntuación
    * determinada en el segunda argumento.
    * @param l Local al que añadir la review falsa
    * @param puntuacion puntuación de la review
    * @return True sí y sólo si se pudo completar la tarea
    * @throws RemoteException If some error happens in the remove invocation.
    */
    @Override
    public Boolean insertaReviewFalsa(Local l, Integer puntuacion) throws RemoteException {
        Usuario usuarioFalso = new Usuario("falso", "contraseña", LocalDate.now(), tipoUsuario.CLIENTE);
        nuevoUsuario(usuarioFalso);
        Review reviewFalsa = new Review(puntuacion, "comentario falso", LocalDate.now(), l, usuarioFalso);
        return nuevaReview(reviewFalsa);
    }
    
    /**
     * Parse XML File (Ejercicio 5 - Práctica 3)
     * @param f Fichero XML
     * @return Clase BusinessSystem
     * @throws XMLParsingException
     * Parsea un fichero XML.
     * @throws java.io.IOException Exception por error de manejo de ficheros.
     */
    public static PublicBusinessSystem parseXMLFilePublic(File f) throws XMLParsingException, IOException {        
        BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
        //Leer fichero
        String contenido = "";
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {contenido += linea;}
        //Comprobar si esta vacio
        if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
        String str = contenido;


        String strUsuariosTodos = obtenerContenidoEtiqueta(str,"Usuarios");
        List<Usuario> usuarios = new ArrayList<>();
        String strLocalesTodos = obtenerContenidoEtiqueta(str,"Locales");
        List<Local> locales = new ArrayList<>();
        String strReviewsTodos = obtenerContenidoEtiqueta(str,"Reviews");
        List<Review> reviews = new ArrayList<>();


        //Para todos los Usuarios
        if(strUsuariosTodos != null){

            List<String> strPropietarios = new ArrayList<>();
            for(String strPropietario : strUsuariosTodos.split("<Propietario>")){
                strPropietarios.add(obtenerContenidoEtiqueta("<Propietario>"+strPropietario+"</Propietario>", "Propietario"));
            }
            strPropietarios.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

            List<String> strClientes = new ArrayList<>();
            for(String strCliente : strUsuariosTodos.split("<Cliente>")){
                strClientes.add(obtenerContenidoEtiqueta("<Cliente>"+strCliente+"</Cliente>", "Cliente"));
            }
            strClientes.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

            List<String> strUsuarios = new ArrayList<>();
            for(String strUsuario : strUsuariosTodos.split("<Usuario>")){
                strUsuarios.add(obtenerContenidoEtiqueta("<Usuario>"+strUsuario+"</Usuario>", "Usuario"));
            }
            strUsuarios.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

            for(String strPropietario : strPropietarios){
                usuarios.add(parsePropietario(strPropietario));
            }
            for(String strCliente : strClientes){
                usuarios.add(parseCliente(strCliente));
            }
            for(String strUsuario : strUsuarios){
                usuarios.add(parseUsuario(strUsuario));
            }
        }


        //Para todos los Locales
        if(strLocalesTodos != null){

            List<String> strLocales = new ArrayList<>();
            for(String strLocal : strLocalesTodos.split("<Local>")){
                strLocales.add(obtenerContenidoEtiqueta("<Local>"+strLocal+"</Local>", "Local"));
            }
            strLocales.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

            List<String> strBares = new ArrayList<>();
            for(String strBar : strLocalesTodos.split("<Bar>")){
                strBares.add(obtenerContenidoEtiqueta("<Bar>"+strBar+"</Bar>", "Bar"));
            }
            strBares.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

            List<String> strPubs = new ArrayList<>();
            for(String strPub : strLocalesTodos.split("<Pub>")){
                strPubs.add(obtenerContenidoEtiqueta("<Pub>"+strPub+"</Pub>", "Pub"));
            }
            strPubs.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

            List<String> strRestaurantes = new ArrayList<>();
            for(String strRestaurante : strLocalesTodos.split("<Restaurante>")){
                strRestaurantes.add(obtenerContenidoEtiqueta("<Restaurante>"+strRestaurante+"</Restaurante>", "Restaurante"));
            }
            strRestaurantes.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

            for(String strLocal : strLocales){
                locales.add(parseLocal(strLocal));
            }
            for(String strBar : strBares){
                locales.add(parseBar(strBar));
            }
            for(String strPub : strPubs){
                locales.add(parsePub(strPub));
            }
            for(String strRestaurante : strRestaurantes){
                locales.add(parseRestaurante(strRestaurante));
            }
        }

        //Para todos las Reviews
        if(strReviewsTodos != null){
            List<String> strReviews = new ArrayList<>();
            for(String strReview : strReviewsTodos.split("<Review>")){
                strReviews.add(obtenerContenidoEtiqueta("<Review>"+strReview+"</Review>", "Review"));
            }
            strReviews.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

            for(String strReview : strReviews){
                reviews.add(parseReview(strReview));
            }
        }
        //Instancia de BusinessSystem
        PublicBusinessSystem bs = new PublicBusinessSystem();

        //Añadir Usuarios
        for(int i = 0; i < usuarios.size(); i++){
            bs.nuevoUsuario(usuarios.get(i));
        }

        //Añadir Locales
        for(int i = 0; i < locales.size(); i++){
            bs.nuevoLocal(locales.get(i));
        }
        for(int i = 0; i < reviews.size(); i++){
            bs.nuevaReview(reviews.get(i));
        }

        return bs;
        

    }
}
