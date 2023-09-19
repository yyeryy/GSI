/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BSystem;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;



/**
 *
 * @author yeray
 */
public class BusinessSystem implements LeisureOffice, LookupService{
    
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Review> reviews = new ArrayList<>();
    ArrayList<Local> locales = new ArrayList<>();

    @Override
    public boolean nuevoUsuario(Usuario u) {
        if (existeNick(u.getNick()))
	    return false;
	usuarios.add(u);
	return true;
    }

    @Override
    public boolean eliminaUsuario(Usuario u) {
        if (existeNick(u.getNick())){
	    usuarios.remove(u);
	    return true;
	}
	return false;
    }

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

    @Override
    public boolean existeNick(String nick) {
	for (Usuario u : usuarios) {
	    if (u.getNick().equals(nick))
		return true;
	}
	return false;
    }

    @Override
    public Usuario obtenerUsuario(String nick) {
        for (Usuario u : usuarios) {
	    if (u.getNick().equals(nick))
		return u;
	}
	return null;
    }

    @Override
    public boolean nuevaReview(Review r) {
        if(existeNick(r.getUsuario().getNick())){
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

    @Override
    public boolean eliminaReview(Review r) {
        if(reviews.isEmpty()){
            return false;
        }
        return reviews.remove(r);
    }

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

    @Override
    public boolean tieneContestacion(Review r) {
        if(existeRewiew(r.getUsuario(), r.getLocal(), r.getFechaReview())){
            if(r.getContestacion() != null){
                return true;
            }
        }
        return false;
    }

    @Override
    public Contestacion obtenerContestacion(Review r) {
        if(existeRewiew(r.getUsuario(), r.getLocal(), r.getFechaReview())){
            if(r.getContestacion() != null){
               return r.getContestacion(); 
            }
        }
        return null;
    }

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
     * @param viejoL
     * @param nuevoL
     * @return 
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

    @Override
    public ArrayList<Review> verReviews(Local l) {
        ArrayList<Review> reviewsLocal = new ArrayList<>();
        if(obtenerLocal(l.getDireccion()) != null){
            for(Review r : reviews){
                if(r.getLocal().equals(l)){
                    reviewsLocal.add(r);
                }
            }
            return reviewsLocal;
        }
        return null;
    }

    /** Locales **/
    
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
        /*
        Comprobar si el usuario existe y es cliente
        */
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
        if(encontradoCliente == 0 || cliente.getTipo() != CLIENTE){
            return false;
        }

        /*
        Comprobar si el local reservable existe (Bar/Restaurante)
        */
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

        /*
        Comrpobar si la fecha es futura
        */
        LocalDate fechaActual = LocalDate.now();
        if(fechaActual.isAfter(ld)){
            return false;
        }
        LocalTime horaActual = LocalTime.now();
        if(horaActual.isAfter(lt)){
            return false;
        }

        /*
        Comprobar si existe alguna reserva en la misma fecha
        */
        Reserva[] localReservas = obtenerReservas(r);
        int encontradoReserva = 0;
        int reserva = 0;
        while (encontradoReserva == 0 && reserva < localReservas.length){
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
        /*
        Comprobar si el usuario existe y es cliente
        */
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
        if(encontradoCliente == 0 || cliente.getTipo() != CLIENTE){
            return null;
        }
        int local = 0;
        ArrayList<Reserva> listaReserva = new  ArrayList<Reserva>();
        while ( local < locales.size()){
            if (locales.get(local).getTipo() != PUB){
                Reservable elLocalR = null;
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
        if(0 < listaReserva.size()){
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
        /*
        Comprobar si el local reservable existe (Bar/Restaurante)
        */
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
        /*
        Comprobar si el local reservable existe (Bar/Restaurante)
        */
        int local = 0;
        ArrayList<Reserva> listaReserva = new  ArrayList<Reserva>();
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
     * @return 
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
    
}
