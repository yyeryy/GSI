package GSILabs.BSystem;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Donacion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Local.tipoLocal;
import static GSILabs.BModel.Local.tipoLocal.*;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Reserva;
import GSILabs.BModel.Reservable;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import GSILabs.persistence.XMLParsingException;
import GSILabs.persistence.XMLWritingException;
import static GSILabs.persistence.parser.obtenerContenidoEtiqueta;
import static GSILabs.persistence.parser.parseBar;
import static GSILabs.persistence.parser.parseCliente;
import static GSILabs.persistence.parser.parseDonacion;
import static GSILabs.persistence.parser.parseLocal;
import static GSILabs.persistence.parser.parsePropietario;
import static GSILabs.persistence.parser.parsePub;
import static GSILabs.persistence.parser.parseRestaurante;
import static GSILabs.persistence.parser.parseReview;
import static GSILabs.persistence.parser.parseUsuario;
import GSILabs.serializable.XMLRepresentable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;


/**
 * Clase BusinessSystem
 * Clase que define los métodos implementados de LeisureOffice y LookupService
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class BusinessSystem implements LeisureOffice, LookupService, XMLRepresentable, Serializable{
    
    public ArrayList<Usuario> usuarios = new ArrayList<>();
    public ArrayList<Review> reviews = new ArrayList<>();
    public ArrayList<Local> locales = new ArrayList<>();
    public ArrayList<Donacion> donaciones = new ArrayList<>();

    /** Usuarios **/
    
    /**
     * Da de alta un usuario, en caso de que su informacion no incumpla las 
     * normas referentes al nick o edad.
     * @param u El nuevo usuario
     * @return Cierto si el usuario pudo ser añadido.
     */
    @Override
    public boolean nuevoUsuario(Usuario u) {
        if (existeNick(u.getNick()))
	    return false;
	usuarios.add(u);
	return true;
    }
    
    /**
     * Elimina al usuario que se pase como argument.
     * @param u El usuario
     * @return True si y solo si el usuario existia y pudo ser eliminado.
     */
    @Override
    public boolean eliminaUsuario(Usuario u) {
        if (existeNick(u.getNick())){
	    usuarios.remove(u);
	    return true;
	}
	return false;
    }
    
    /**
     * Reemplaza en el sistema al usuario viejo por el nuevo. Para que esto suceda debe
     * Cumplirse que el usuario viejo exista y que el nuevo no incumpla normas
     * relativas a las politicas de Usuarios (nick y/o edad)
     * @param u El usuario
     * @param nuevoU El nuevo usuario
     * @return True si el usuario se encontro y pudo ser modificado
     */
    @Override
    public boolean modificaUsuario(Usuario u, Usuario nuevoU) {
        if (existeNick(u.getNick())){
	    u.setNick(nuevoU.getNick());
	    u.setContraseña(nuevoU.getContraseña());
	    u.setFechaNacimiento(nuevoU.getFechaNacimiento());
	    u.setTipo(nuevoU.getTipo());
	    return true;
	}
	return false;
    }

    /**
     * Comprueba si existe algun usuario con ese mismo nick
     * @param nick id del usuario a comprobar
     * @return True si existe un usuario con ese nick
     */
    @Override
    public boolean existeNick(String nick) {
	for (Usuario u : usuarios) {
	    if (u.getNick().equals(nick))
		return true;
	}
	return false;
    }

    /**
     * Comprueba si existe algun el local que se le pasa
     * @param local id del usuario a comprobar
     * @return True si existe un usuario con ese nick
     */
    public boolean existeLocal(Local local) {
	for (Local l : locales) {
	    if (l.getDireccion().equals(local))
		return true;
	}
	return false;
    }
    
    /**
     * Recupera el usuario asociado a un nick, en caso de que exista.
     * @param nick id del usuario a obtener
     * @return El usuario con el nick. Debe devolver null si existeNick(nick) es falso.
     */
    @Override
    public Usuario obtenerUsuario(String nick) {
        for (Usuario u : usuarios) {
	    if (u.getNick().equals(nick))
		return u;
	}
	return null;
    }
    
    /** Reviews **/
    
    /**
     * Incorpora una nueva review al sistema, en caso de que sus datos (Usuario, 
     *  Local) sean correctos y no haya otra introducida para la misma fecha.
     * @param r La review a introducir al sistema.
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean nuevaReview(Review r) {
        if(existeNick(r.getUsuario().getNick()) && (r.getValoracion() >= 0) && (r.getValoracion() <= 5) && r.getComentario().length() >= 0 && r.getComentario().length() <= 500){
            if(existeRewiew(r.getUsuario(), r.getLocal(), LocalDate.now())){
                for(Local l : locales){
                    if(l.getDireccion().equals(r.getLocal().getDireccion())){
                        return false;
                    }
                }
            }
            reviews.add(r);
            return true;
        }
        return false;
    }
    
    /**
     * Elimina una review del sistema, siempre y cuando exista y no tenga una 
     * contestacion asociada.
     * @param r Review a eliminar
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean eliminaReview(Review r) {
        if(reviews.isEmpty() || tieneContestacion(r) || !existeRewiew(r.getUsuario(), r.getLocal(), r.getFechaReview())){
            return false;
        }
        return reviews.remove(r);
    }

    /**
     * Comprueba si la visita de un usuario a un local en una fecha dada ha sido 
     * comentada. En caso deque alguno de los datos sea incorrecto, o inexistente, el 
     * resultado sera false.
     * @param u el usuario
     * @param l el local visitadl
     * @param ld la fecha de visita
     * @return True si y solo si la review existe.
     */
    @Override
    public boolean existeRewiew(Usuario u, Local l, LocalDate ld) {
        if(reviews.isEmpty()){
            return false;
        }
        for(Review review : reviews){
            if(review.getUsuario().getNick().equalsIgnoreCase(u.getNick())){
                if(review.getFechaReview() == ld){
                    return true;
                }
            }
        }
        return false;
    }
    
    /** Contestaciones **/
    
    /**
     * Añade una contestacion a una review, en caso de que la review exista y no este 
     * ya comentada.
     * @param c Contestacion a añadir
     * @param r Review
     * @return True si y solo si la operacion fue completada y se pudo añadir la review.
     */
    @Override
    public boolean nuevaContestacion(Contestacion c, Review r) {
        if(existeRewiew(r.getUsuario(), r.getLocal(), r.getFechaReview())){
            if(r.getContestacion() == null){
                r.setContestacion(c);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Consulta la existencia de una contestacion para una review. Devolvera
     * falso si la contestacion no existe, o si la Review no esta registrada en
     * el sistema.
     * @param r Review a añadir
     * @return True si y solo si la Review existe y tiene contestacion
     */
    @Override
    public boolean tieneContestacion(Review r) {
        if(existeRewiew(r.getUsuario(), r.getLocal(), r.getFechaReview())){
            if(r.getContestacion() != null){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Recupera la contestacion para una review dada, si esta existe.
     * @param r Review a consultar
     * @return La contestacion, o null si esta, o la propia review, no existen
     */
    @Override
    public Contestacion obtenerContestacion(Review r) {
        if(existeRewiew(r.getUsuario(), r.getLocal(), r.getFechaReview())){
            if(r.getContestacion() != null){
               return r.getContestacion(); 
            }
        }
        return null;
    }
    
    /**
     * Elimina la contestacion pasada como argumento
     * @param c Contestacion a eliminar
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean eliminaContestacion(Contestacion c) {
        if(reviews.isEmpty()){
            return false;
        }
        for (Review review : reviews) {
            if (review.getContestacion().getComentario().equalsIgnoreCase(c.getComentario())){
                review.setContestacion(null);
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina la contestacion asociada a una review
     * @param r La review cuya contestacion hay que elimnar
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean eliminaContestacion(Review r) {
        if(reviews.isEmpty()){
            return false;
        }
        for (Review review : reviews) {
            if (review.getContestacion().getComentario().equalsIgnoreCase(r.getContestacion().getComentario())){
                review.setContestacion(null);
                return true;
            }
        }
        return false;
    }
    
    /** Locales **/

    /**
     * Añade un local al sistema, siempre que no exista otro en la misma direccion.
     * @param l El nuevo local
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean nuevoLocal(Local l) {
        if(null != obtenerLocal(l.getDireccion()))
            return false;
        locales.add(l);
        return true;
    }

    /**
     * Elimina un local determinado, si este existe como tal en el sistema.
     * @param l EL local a eliminar
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean eliminarLocal(Local l) {
        if(locales.size() < 1) {
            return false;
        }
        return locales.remove(l);
    }

    /**
     * Obtiene los datos del local instalado en una determinada direccion fisica
     * @param d Direccion del local.
     * @return El local almacenado en el sistema, o null si no existe.
     */
    @Override
    public Local obtenerLocal(Direccion d) {
        if(locales.size() < 1) {
            return null;
        }
        for(int i = 0; i < locales.size(); i++){
            if(locales.get(i).getDireccion().equals(d)){
                return locales.get(i);
            }
        }
        return null;
    }

    /**
     * Asocia un local a un propietario, en caso de que ambos existan y no se haya llegado
     * al limite de Propietarios por local
     * @param l Local existente en en sistema
     * @param p Propietario existente en el sistema
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean asociarLocal(Local l, Propietario p) {
        return l.addPropietario(p);
    }

    /**
     * Desliga un local de un propietario, en caso de que ambos existan y estén
     * ya relacionados
     * @param l Local existente en en sistema
     * @param p Propietario existente en el sistema
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean desasociarLocal(Local l, Propietario p) {
        return l.removePropietario(p);
    }

    /**
     * 
     * @param viejoL Local Viejo
     * @param nuevoL Nuevo Local
     * @return Booleano que indica si se ha actualizado el local correctamente.
     */
    @Override
    public boolean actualizarLocal(Local viejoL, Local nuevoL) {
        if(locales.size() < 1) {
            return false;
        }
        locales.remove(viejoL);
        locales.add(nuevoL);
        return true;
    }
    
    /**
     * Ver las review asociadas a un local
     * @param l Local existente en en sistema
     * @return Lista de reviews del sistema. En caso de que el Local no exista, sera
     *  el valor null.
     */
    @Override
    public Review[] verReviews(Local l) {
        ArrayList<Review> reviewsLocal = new ArrayList<>();
        if(obtenerLocal(l.getDireccion()) != null){
            for(Review r : reviews){
                if(r.getLocal().equals(l)){
                    reviewsLocal.add(r);
                }
            }

            //Convertimos el arraylist en array
            Review[] arrayReview = new Review[reviewsLocal.size()];
            for (int j = 0; j < reviewsLocal.size(); j++) {
                arrayReview[j] = reviewsLocal.get(j);
            }
            
            return arrayReview;
        }
        return null;
    }

    /** Reservas **/
    
    /**
     * Anota una nueva reserva para un cliente dado, en un local reservable
     * para una fecha y hora concreta. El cliente y el local deben existir,
     * y la fecha y hora debe ser futura. El cliente no debe tener otra reserva para el
     * mismo local en la misma fecha.
     * @param c Cliente que hace la reserva
     * @param r Local donde se efectua la reserva
     * @param ld Fecha de la reserva
     * @param lt Hora de la reserva
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean nuevaReserva(Cliente c, Reservable r, LocalDate ld, LocalTime lt) {
        //Comprobar si el usuario existe y es cliente
        int encontradoCliente = 0;
        int user = 0;
        Cliente cliente = null;
        while (encontradoCliente == 0 && user < usuarios.size()){
            if (usuarios.get(user) != c){
                user++;
            }else{
                encontradoCliente = 1;
                cliente = c;
            }
        }
        if(encontradoCliente == 0 || cliente == null || cliente.getTipo() != CLIENTE){
            return false;
        }

        //Comprobar si el local reservable existe (Bar/Restaurante)
        int encontradoLocal = 0;
        int local = 0;
        while (encontradoLocal == 0 && local < locales.size()){
            if (locales.get(local) != r){
                local++;
            }else{
                encontradoLocal = 1;
            }
        }
        if(encontradoLocal == 0){
            return false;
        }

        //Comprobar si la fecha es futura
        LocalDate fechaActual = LocalDate.now();
        if(fechaActual.isAfter(ld)){
            return false;
        }

        if(fechaActual.isEqual(ld)){
            LocalTime horaActual = LocalTime.now();
            if(horaActual.isAfter(lt)){

                return false;
            }
        }

        //Comprobar si existe alguna reserva en la misma fecha
        Reserva[] localReservas = obtenerReservas(r);
        int encontradoReserva = 0;
        int reserva = 0;
        int len;
        if(localReservas == null){
            len = 0;
        }else{
            len = localReservas.length;
        }
        
        while (encontradoReserva == 0 && reserva < len){
            if (!localReservas[reserva].getFecha().equals(ld)){
                reserva++;
            }else{
                encontradoReserva = 1;
            }
        }
        if(encontradoReserva == 1){
            return false;
        }

        r.nuevaReserva(c, ld, lt);

        return true;

    }

    /**
     * Obtiene todas las reservas (futuras y pasadas) del cliente.
     * @param c El cliente a consultar
     * @return La lista de las reservas, o null si el cliente no existe.
     */
    @Override
    public Reserva[] obtenerReservas(Cliente c) {
        //Comprobar si el usuario existe y es cliente
        int encontradoCliente = 0;
        int user = 0;
        Cliente cliente = null;
        while (encontradoCliente == 0 && user < usuarios.size()){
            if (usuarios.get(user) != c){
                user++;
            }else{
                encontradoCliente = 1;
                cliente = c;
            }
        }
        if(encontradoCliente == 0 || cliente == null || cliente.getTipo() != CLIENTE){
            return null;
        }
        int local = 0;
        ArrayList<Reserva> listaReserva = new  ArrayList<>();
        while ( local < locales.size()){
            if (locales.get(local).getTipo() != PUB){
                Reservable elLocalR;
                if (locales.get(local).getTipo() == BAR){
                    elLocalR = (Bar) locales.get(local);
                }else{
                    elLocalR = (Restaurante) locales.get(local);
                }
                Reserva[] reservasUnLocal = elLocalR.reservasDeCliente(c);
                if(reservasUnLocal != null){
                    int pos = 0;
                    while ( pos < reservasUnLocal.length){
                        listaReserva.add(reservasUnLocal[pos]);
                    pos++;
                    }
                }
            }
            local++;
        }
        if(!listaReserva.isEmpty()){
            Reserva[] reservas = new Reserva[listaReserva.size()]; 
            int pos = 0;
            while(pos < listaReserva.size()){
                reservas[pos] = (listaReserva.get(pos));
                pos++;
            }
            return reservas;
        }
        return null; 
    }

    /**
     * Obtiene todas las reservas (futuras y pasadas) del local.
     * @param r El local a consultar
     * @return La lista de las reservas, o null si el local no existe.
     */
    @Override
    public Reserva[] obtenerReservas(Reservable r) {
        //Comprobar si el local reservable existe (Bar/Restaurante)
        int encontradoLocal = 0;
        int local = 0;
        while (encontradoLocal == 0 && local < locales.size()){
            if (locales.get(local) != r){
                local++;
            }else{
                encontradoLocal = 1;
            }
        }
        if(encontradoLocal == 0){
            return null;
        }
        return r.todasReserva();

    }

    /**
     * Obtiene todas las reservas del dia usado como argumento
     * @param ld la fecha a consultar
     * @return La lista de las reservas.
     */
    @Override
    public Reserva[] obtenerReservas(LocalDate ld) {
        //Comprobar si el local reservable existe (Bar/Restaurante)
        int local = 0;
        ArrayList<Reserva> listaReserva = new  ArrayList<>();
        while ( local < locales.size()){
            if(locales.get(local).getTipo() != PUB){
                Reservable elLocal = (Reservable) locales.get(local);
                Reserva[] reservasUnLocal = elLocal.reservasDeDia(ld);
                if(reservasUnLocal != null){
                    int pos = 0;
                    while ( pos < reservasUnLocal.length){
                        listaReserva.add(reservasUnLocal[pos]);
                    pos++;
                    }
                }        
            }
            local++;
        }
        int pos = 0;
        Reserva[] reservas = new Reserva[listaReserva.size()];
        while(pos < listaReserva.size()){
            reservas[pos] = (listaReserva.get(pos));
            pos++;
        }
        return reservas;
    }

    /**
     * Elimina una reserva del sistema, en caso de que esta exista
     * @param r La reserva a eliminar.
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean eliminarReserva(Reserva r) {
        int local = 0;
        int encontrado = 0;
        while(local < locales.size() && encontrado == 0){
            if(locales.get(local).getTipo() != PUB){
                Reservable elLocal = (Reservable) locales.get(local);
                if(elLocal.comprobarReserva(r)){
                   return elLocal.eliminarReserva(r);
                }
                
            }
            local++;
        }
        return false;
    }

    /**
     * Lista los locales en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de locales, potencialmente de longitud 0.
     */
    @Override
    public Local[] listarLocales(String ciudad, String provincia) {
        ArrayList<Local> lista = new ArrayList<>();
        for(int i = 0; i < locales.size(); i++){
            if((locales.get(i).getDireccion().getProvincia().equals(provincia)) && (locales.get(i).getDireccion().getLocalidad().equals(ciudad))){
                lista.add(locales.get(i));
            }
        }
        return (Local[])lista.toArray();
    }

    /**
     * Lista los bares en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de bares, potencialmente de longitud 0.
     */
    @Override
    public Bar[] listarBares(String ciudad, String provincia) {
        ArrayList<Local> lista = new ArrayList<>();
        for(int i = 0; i < locales.size(); i++){
            if((locales.get(i).getTipo().equals(tipoLocal.BAR)) && (locales.get(i).getDireccion().getProvincia().equals(provincia)) && (locales.get(i).getDireccion().getLocalidad().equals(ciudad))){
                lista.add(locales.get(i));
            }
        }
        return (Bar[])lista.toArray();
    }

    /**
     * Lista los restaurantes en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de bares, potencialmente de longitud 0.
     */
    @Override
    public Restaurante[] listarRestaurantes(String ciudad, String provincia) {
        ArrayList<Local> lista = new ArrayList<>();
        for(int i = 0; i < locales.size(); i++){
            if((locales.get(i).getTipo().equals(tipoLocal.RESTAURANTE)) && (locales.get(i).getDireccion().getProvincia().equals(provincia)) && (locales.get(i).getDireccion().getLocalidad().equals(ciudad))){
                lista.add(locales.get(i));
            }
        }
        return (Restaurante[])lista.toArray();
    }

    /**
     * Lista los pubs en una ciudad dada
     * @param ciudad Ciudad de interes
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return La lista de bares, potencialmente de longitud 0.
     */
    @Override
    public Pub[] listarPubs(String ciudad, String provincia) {
        ArrayList<Local> lista = new ArrayList<>();
        for(int i = 0; i < locales.size(); i++){
            if((locales.get(i).getTipo().equals(tipoLocal.PUB)) && (locales.get(i).getDireccion().getProvincia().equals(provincia)) && (locales.get(i).getDireccion().getLocalidad().equals(ciudad))){
                lista.add(locales.get(i));
            }
        }
        return (Pub[])lista.toArray();
    }

    /**
     * Obtiene la valoración media de un local, o -1 si éste no existe.
     * @param l Local de interés.
     * @return La valoración media de las reviews asociadas, o 0 si no existen reviews.
     */
    @Override
    public float obtenerValoracionMedia(Local l) {
        int valoracionTotal = 0;
        int reviewsLocal = 0;
        if(!locales.contains(l)) return -1;
        for(int i = 0; i < reviews.size(); i++) {
            if(reviews.get(i).getLocal().equals(l)) {
                valoracionTotal += reviews.get(i).getValoracion();
                reviewsLocal++;
            }
        }
        if(reviewsLocal == 0) return 0;
        return ((float)valoracionTotal / (float)reviewsLocal);
    }

    /**
     * Obtiene la valoración media de un propietario, o -1 si éste no existe.
     * @param p El propietario a investigar
     * @return La valoración media de las reviews asociadas a sus locales, o 0 si no existen reviews.
     */
    @Override
    public float obtenerValoracionMedia(Propietario p) {
        int valoracionTotal = 0;
        int reviewsPropietario = 0;
        if(!usuarios.contains(p)) return -1;
        for(int i = 0; i < reviews.size(); i++) {
            if(reviews.get(i).getLocal().getPropietarios().contains(p)) {
                valoracionTotal += reviews.get(i).getValoracion();
                reviewsPropietario++;
            }
        }
        if(reviewsPropietario == 0) return 0;
        return ((float)valoracionTotal / (float)reviewsPropietario);
    }

    /**
     * Obtiene la valoración media de un local realizadas por gente que,
     * en el momento de la valoración, estaba en un rango de edad determinado, 
     * o null si éste no existe, o -1 si éste no existe.
     * @param l Local de interés.
     * @param edadEntre Edad minima del rango (incluida)
     * @param edadHasta Edad maxima del rango (incluida)
     * @return Valoración media realizada al local
     */
    @Override
    public float obtenerValoracionMedia(Local l, int edadEntre, int edadHasta) {
        int valoracionTotal = 0;
        int reviewsLocal = 0;
        if(!locales.contains(l)) return -1;
        for(int i = 0; i < reviews.size(); i++) {
            if(reviews.get(i).getLocal().equals(l)) {
                int edad = Period.between(reviews.get(i).getUsuario().getFechaNacimiento(), reviews.get(i).getFechaReview()).getYears();
                if(edadEntre <= edad && edad <= edadHasta) {
                    valoracionTotal += reviews.get(i).getValoracion();
                    reviewsLocal++;
                }
            }
        }
        if(reviewsLocal == 0) return 0;
        return ((float)valoracionTotal / (float)reviewsLocal);
    }

    /**
     * Obtiene los locales de una ciudad y provincia ordenados por su valoración media
     * en las reviews asociadas. Los locales que no tienen reviews serán valorados
     * con 0.
     * @param ciudad Ciudad de interés
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return Los locales ordenados por nota descendente
     */
    @Override
    public Local[] obtenerLocalesOrdenados(String ciudad, String provincia) {
        ArrayList<Local> lista = new ArrayList<>();
        for(int i = 0; i < locales.size(); i++){
            if((locales.get(i).getDireccion().getProvincia().equals(provincia)) && (locales.get(i).getDireccion().getLocalidad().equals(ciudad))){
                lista.add(locales.get(i));
            }
        }
        Collections.sort(lista, (Local local1, Local local2) -> {
            float valoracionMedia1 = obtenerValoracionMedia(local1);
            float valoracionMedia2 = obtenerValoracionMedia(local2);
            return Float.compare(valoracionMedia1, valoracionMedia2);
        });
        return (Local[])lista.toArray();
    }

    /**
     * Obtiene los locales de una provincia ordenados por su valoración media
     * en las reviews asociadas. Los locales que no tienen reviews serán valorados
     * con 0.
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return Los locales ordenados por nota descendente
     */
    @Override
    public Local[] obtenerLocalesOrdenados(String provincia) {
        ArrayList<Local> lista = new ArrayList<>();
        for(int i = 0; i < locales.size(); i++){
            if(locales.get(i).getDireccion().getProvincia().equals(provincia)){
                lista.add(locales.get(i));
            }
        }
        Collections.sort(lista, (Local local1, Local local2) -> {
            float valoracionMedia1 = obtenerValoracionMedia(local1);
            float valoracionMedia2 = obtenerValoracionMedia(local2);
            return Float.compare(valoracionMedia1, valoracionMedia2);
        });
        return (Local[])lista.toArray();
    }

    /**
     * Obtiene los bares de una ciudad y provincia ordenados por su valoración media
     * en las reviews asociadas. Los locales que no tienen reviews serán valorados
     * con 0.
     * @param ciudad Ciudad de interés
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return Los bares ordenados por nota descendente
     */
    @Override
    public Bar[] obtenerBaresOrdenados(String ciudad, String provincia) {
        ArrayList<Local> lista = new ArrayList<>();
        for(int i = 0; i < locales.size(); i++){
            if((locales.get(i).getTipo().equals(tipoLocal.BAR)) && (locales.get(i).getDireccion().getProvincia().equals(provincia)) && (locales.get(i).getDireccion().getLocalidad().equals(ciudad))){
                lista.add(locales.get(i));
            }
        }
        Collections.sort(lista, (Local local1, Local local2) -> {
            float valoracionMedia1 = obtenerValoracionMedia(local1);
            float valoracionMedia2 = obtenerValoracionMedia(local2);
            return Float.compare(valoracionMedia1, valoracionMedia2);
        });
        return (Bar[])lista.toArray();
    }

    /**
     * Obtiene los restaurantes de una ciudad y provincia ordenados por su valoración media
     * en las reviews asociadas. Los locales que no tienen reviews serán valorados
     * con 0.
     * @param ciudad Ciudad de interés
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return Los restaurantes ordenados por nota descendente
     */
    @Override
    public Restaurante[] obtenerRestaurantesOrdenados(String ciudad, String provincia) {
        ArrayList<Local> lista = new ArrayList<>();
        for(int i = 0; i < locales.size(); i++){
            if((locales.get(i).getTipo().equals(tipoLocal.RESTAURANTE)) && (locales.get(i).getDireccion().getProvincia().equals(provincia)) && (locales.get(i).getDireccion().getLocalidad().equals(ciudad))){
                lista.add(locales.get(i));
            }
        }
        Collections.sort(lista, (Local local1, Local local2) -> {
            float valoracionMedia1 = obtenerValoracionMedia(local1);
            float valoracionMedia2 = obtenerValoracionMedia(local2);
            return Float.compare(valoracionMedia1, valoracionMedia2);
        });
        return (Restaurante[])lista.toArray();
    }

    /**
     * Obtiene los pubs de una ciudad y provincia ordenados por su valoración media
     * en las reviews asociadas. Los locales que no tienen reviews serán valorados
     * con 0.
     * @param ciudad Ciudad de interés
     * @param provincia Provincia en la que se encuentra la ciudad
     * @return Los pubs ordenados por nota descendente
     */
    @Override
    public Pub[] obtenerPubOrdenados(String ciudad, String provincia) {
        ArrayList<Local> lista = new ArrayList<>();
        for(int i = 0; i < locales.size(); i++){
            if((locales.get(i).getTipo().equals(tipoLocal.PUB)) && (locales.get(i).getDireccion().getProvincia().equals(provincia)) && (locales.get(i).getDireccion().getLocalidad().equals(ciudad))){
                lista.add(locales.get(i));
            }
        }
        Collections.sort(lista, (Local local1, Local local2) -> {
            float valoracionMedia1 = obtenerValoracionMedia(local1);
            float valoracionMedia2 = obtenerValoracionMedia(local2);
            return Float.compare(valoracionMedia1, valoracionMedia2);
        });
        return (Pub[])lista.toArray();
    }
    
// Donacion

    /**
     * Anota una Donacion en el sistema,
     * @param dona Donacion a inserta en BusinessSystem
     * @return True si y solo si la operacion fue completada.
     */
    public boolean nuevaDonacion(Donacion dona){
        return donaciones.add(dona);
    }

    /**
     * Anota una Donacion en el sistema,
     * con el local que realiza la donacion, nombre del producto y la cantidad,
     * el usuario de la Donacion se inicializa en null
     * @param l Local que hace la Donacion
     * @param p Nombre del producto a donar
     * @param c Cantidad del producto a donar
     * @return True si y solo si la operacion fue completada.
     */
    public boolean nuevaDonacion(Local l, String p, int c){
        Donacion dona = new Donacion(l, p, c);
        return donaciones.add(dona);
    }

    /**
     * Actualiza una Donacion con usuario null en el sistema
     * a tener como usuario el Usuario que reserva la Donacion,
     * @param d Donacion que el usuario reserva
     * @param u Usuario que reserva la Donacion
     * @return True si y solo si la operacion fue completada.
     */
    public boolean reservarDonacion(Donacion d, Usuario u){
        int i = donaciones.indexOf(d);
        Donacion dona = donaciones.get(i);
        dona.setUsuario(u);
        return true;
    }

    /**
     * Elimina una Donacion del sistema, en caso de que esta exista
     * @param d La donacion a eliminar.
     * @return True si y solo si la operacion fue completada.
     */
    public boolean eliminarDonacion(Donacion d){
        return donaciones.remove(d);
    }

    /**
     * Obtiene todas las Donaciones reservadas por un usuario
     * @param u Usuario al que se obtine todas sus donaciones reservadas
     * @return True si y solo si la operacion fue completada.
     */
    public Donacion[] obtenerDonaciones(Usuario u){

        ArrayList<Donacion> lista = new ArrayList<>();
        for(int i = 0; i < this.donaciones.size(); i++){
            if(this.donaciones.get(i).getUsuario().equals(u)){
                lista.add(this.donaciones.get(i));
            }
        }

        return (Donacion[])lista.toArray();
    }

    /**
     * Obtiene todas las Donaciones hechas por un Local
     * @param l Local al que se obtine todas sus donaciones realizadas
     * @return True si y solo si la operacion fue completada.
     */
    public Donacion[] obtenerDonaciones(Local l){

        ArrayList<Donacion> lista = new ArrayList<>();
        for(int i = 0; i < this.donaciones.size(); i++){
            if(this.donaciones.get(i).getLocal().equals(l)){
                lista.add(this.donaciones.get(i));
            }
        }
        return (Donacion[])lista.toArray();
    }

    /**
     * Importa pubs (Ejercicio 6 - Práctica 2)
     * @param f fichero
     * @return valor de fila
     * Incorpore a la clase GSILabs.BSystem.BusinessSystem un método 
     * importaPubs(File f):int que cargue un listado de Bares desde un fichero ods. 
     * Para ello, f debe apuntar a un fichero existente. Dicho fichero debe tener 
     * una única página, tal que el nombre del bar al que se refiere se almacene 
     * en la primera columna. El resto de información se almacenará a partir de 
     * la segunda columna, incluyendo su dirección. Los bares deben comenzar en 
     * la primera fila de la hoja, y se considerará que no hay bares más allá de 
     * la primera fila cuya primera columna esté vacía. Puede descargar un ejemplo 
     * de fichero desde MiAulario, con el nombre P02Ej05.ods. El valor que devuelve 
     * el método es el número de bares incorporados con éxito al sistema (nótese 
     * que el fichero podría no cumplir con las normas de la política de negocio). 
     * Nota: En caso de que la lógica de negocio implementada en la Práctica 01 
     * no sea acorde a la información del fichero P02Ej05.ods, consúltelo con el 
     * docente.
     */
    public int importaPubs(File f){	
        try {
	    
            SpreadSheet spreadSheet = SpreadSheet.createFromFile(f);

            //Obtención de la hoja de cálculo
            Sheet sheet = spreadSheet.getSheet(0);
	   
            //Comprobación de cual es la primera fila cuya primera columna este vacía y devolución de ese valor de fila
	    for (int i = 0; i < sheet.getRowCount(); i++) {
                if (sheet.getValueAt(0, i).toString().isEmpty()){
		    return i;
		}
            }
	    return sheet.getRowCount();

        } catch (IOException e) {
            e.printStackTrace();
        }
	return 0;
	
    }
    
    /**
     * Generación de una representación XML del objeto BusinessSystem.
     * @return Representación XML del objeto en forma de cadena
     */
    @Override
    public String toXML() {

        String[] partes;
        String xmlData = "";
        //Cabecera
        xmlData += "<BusinessSystem>\n";

        //Usuarios
        xmlData += "<Usuarios>\n";
        for(int i = 0; i < this.usuarios.size(); i++){

            partes = this.usuarios.get(i).toXML().split("<Usuario>", 2);
            if(partes.length == 2){
                xmlData += "<Usuario>" + partes[1];
            }else{
                partes = this.usuarios.get(i).toXML().split("<Cliente>", 2);
                if(partes.length == 2){
                    xmlData += "<Cliente>" + partes[1];
                }else{
                    partes = this.usuarios.get(i).toXML().split("<Propietario>", 2);
                    if(partes.length == 2){
                        xmlData += "<Propietario>" + partes[1];
                    }
                }
            }
        }
        xmlData += "</Usuarios>\n";

        xmlData += "<Reviews>\n";
        //Reviews
        for(int i = 0; i < this.reviews.size(); i++){
            partes = this.reviews.get(i).toXML().split("<Review>", 2);
            if(partes.length == 2){
                xmlData += "<Review>" + partes[1];
            }
        }
        xmlData += "</Reviews>\n";

        xmlData += "<Locales>\n";
        //Locales
        for(int i = 0; i < this.locales.size(); i++){
            partes = this.locales.get(i).toXML().split("<Local>", 2);
            if(partes.length == 2){
                xmlData += "<Local>" + partes[1];
            }else{
                partes = this.locales.get(i).toXML().split("<Bar>", 2);
                if(partes.length == 2){
                    xmlData += "<Bar>" + partes[1];
                } else{
                    partes = this.locales.get(i).toXML().split("<Restaurante>", 2);
                    if(partes.length == 2){
                        xmlData += "<Restaurante>" + partes[1];
                    }else{
                        partes = this.locales.get(i).toXML().split("<Pub>", 2);
                        if(partes.length == 2){
                            xmlData += "<Pub>" + partes[1];
                        }
                    }
                }
            }
        }
        xmlData += "</Locales>\n";

        // Donaciones
        xmlData += "<Donaciones>\n";
        //Reviews
        for(int i = 0; i < this.donaciones.size(); i++){
            partes = this.donaciones.get(i).toXML().split("<Donacion>", 2);
            if(partes.length == 2){
                xmlData += "<Donacion>" + partes[1];
            }
        }
        xmlData += "</Donaciones>\n";

        //Cierre
        xmlData += "</BusinessSystem>\n";
        return formatearXML(xmlData);
    }



    /**
     * Parse XML File (Ejercicio 5 - Práctica 3)
     * @param f Fichero XML
     * @return Clase BusinessSystem
     * @throws XMLParsingException
     * Parsea un fichero XML.
     * @throws java.io.IOException Exception por error de manejo de ficheros.
     */
    public static BusinessSystem parseXMLFile(File f) throws XMLParsingException, IOException {        
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
        String strDonacionesTodos = obtenerContenidoEtiqueta(str,"Donaciones");
        List<Donacion> donacioness = new ArrayList<>();


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

        //Para todos las Reviews
        if(strDonacionesTodos != null){
            List<String> strDonacions = new ArrayList<>();
            for(String strReview : strDonacionesTodos.split("<Donacion>")){
                strDonacions.add(obtenerContenidoEtiqueta("<Donacion>"+strReview+"</Donacion>", "Donacion"));
            }
            strDonacions.remove(0); //El primero es un null, debido a la forma de trocear, se debe eliminar

            for(String strDonacion : strDonacions){
                donacioness.add(parseDonacion(strDonacion));
            }
        }

        //Instancia de BusinessSystem
        BusinessSystem bs = new BusinessSystem();

        //Añadir Usuarios
        for(int i = 0; i < usuarios.size(); i++){
            bs.nuevoUsuario(usuarios.get(i));
        }

        //Añadir Locales
        for(int i = 0; i < locales.size(); i++){
            bs.nuevoLocal(locales.get(i));
        }

        // Añadir Reviews
        for(int i = 0; i < reviews.size(); i++){
            bs.nuevaReview(reviews.get(i));
        }

        // Añadir Donaciones
        for(int i = 0; i < donacioness.size(); i++){
            bs.nuevaDonacion(donacioness.get(i));
        }

        return bs;
        

    }
    
    /**
     * Guardado de la representación XML del objeto BusinessSystem
     * en el fichero indicado por parámetro.
     * @param f Fichero XML en el que se guarda la representación XML del objeto
     * @return Booleano que indica si el fichero se ha guardado exitosamente.
     */
    @Override
    public boolean saveToXML(File f) {
        try {
        String xmlData = toXML();
            try (FileWriter writer = new FileWriter(f)) {
                writer.write(xmlData);
            }
        return true;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * Guardado de la representación XML del objeto BusinessSystem
     * en un fichero XML que se almacenará en la ruta indicada por parámetro.
     * @param filePath Ruta del fichero donde se va a guardar la reprentación XML.
     * @return Booleano que indica si el fichero se ha guardado exitosamente.
     */
    @Override
    public boolean saveToXML(String filePath) {
        File file = new File(filePath);
        return saveToXML(file);
    }
    
    /**
     * Write XML File (Ejercicio 3 - Práctica 4)
     * @param f Fichero XML
     * @return Booleano que indica si el fichero XML se ha escrito correctamente.
     * Escribe un fichero XML.
     * @throws GSILabs.persistence.XMLWritingException Exception de fallo de escritura de ficheros XML.
     */
    public boolean writeXMLFile(File f) throws XMLWritingException{
        try{          
            saveToXML(f);
            return true;
        }catch(Exception exception){
            return false;
        }
    }
    
    /**
     * Load XML File (Ejercicio 5 - Práctica 4)
     * @param f Fichero XML
     * @return Booleano que indica si el fichero XML se ha cargado correctamente.
     * Carga un fichero XML.
     * @throws GSILabs.persistence.XMLParsingException Exception de Parseo de XML
     */
    public static boolean loadXMLFile(File f) throws XMLParsingException {
        try {
            BusinessSystem loadedBusinessSystem = BusinessSystem.parseXMLFile(f);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            //Leer fichero
            String contenido = "";
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {contenido += linea + "\n";}
            //Comprobar si esta vacio
            if(contenido.length() == 0) {throw new XMLParsingException("Fichero vacio.");}
            String str = contenido;


            //Conteo de lineas para comparar
            int lineasXml = contenido.split("\n").length;
            int lineasBS = loadedBusinessSystem.toXML().split("\n").length;
            
            //Si el fichero XML se ha cargado correctamente devolvemos true
            return lineasXml == lineasBS;
        //Si por el contrario ocurre algún error devolvemos false.
        } catch(Exception exception) {
            return false;
            //throw new XMLParsingException("Error al analizar el archivo XML");
        }
    }
    
    /**
     * A partir de un string XML sin tabulaciones añade las tabulaciones pertinentes
     * @param input string sin tabulaciones.
     * @return string con tabulaciones.
     */
    public static String formatearXML(String input) {
        //Eliminar tabulacion
        input = input.replaceAll("\t", "");
        StringBuilder stringBuilder = new StringBuilder();
        int tabulacion = 0; //Nivel de tabulacion a meter tras salto de linea

        String[] lineas = input.split("\n"); //Trocear por salto de linea
        for (String linea : lineas) {
            if (linea.startsWith("</")) {tabulacion--;} // Caso fin objeto
            for (int i = 0; i < tabulacion; i++) {stringBuilder.append("\t");}
            stringBuilder.append(linea).append("\n");
            if (linea.matches(".*<[A-Z].*")) {tabulacion++;} //Caso de inicio objeto
        }
        return stringBuilder.toString();
    }

}

