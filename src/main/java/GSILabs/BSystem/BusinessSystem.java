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
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author yeray
 */
public class BusinessSystem implements LeisureOffice, LookupService{
    
    private static final int TAMANO_LISTAS = 100;
<<<<<<< HEAD
    HashSet<Usuario> usuarios;
=======
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
>>>>>>> efab8cafab282f99dfec20df46cc19235f987421
    //Usuario[] usuarios = new Usuario[TAMANO_LISTAS];
    Review[] reviews = new Review[TAMANO_LISTAS];
    ArrayList<Local> locales = new ArrayList<Local>();

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
        if(existeNick(r.getUsuario().getNick()) /*&& obtenerLocal(r.getLocal().getDireccion()*/){
            if(existeRewiew(r.getUsuario(), r.getLocal(), LocalDate.now())){
                return false;
            }
            reviews[reviews.length] = r;
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminaReview(Review r) {
        if(reviews.length == 0){
            return false;
        }
        for(int i = 0; i < reviews.length; i++){
            if(reviews[i].getUsuario().getNick().equalsIgnoreCase(r.getUsuario().getNick()) && reviews[i].getFechaReview() == (r.getFechaReview()) && reviews[i].getLocal().equals(r.getLocal())){
                //Eliminar review
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existeRewiew(Usuario u, Local l, LocalDate ld) {
        Review[] reviews = verReviews(l);
        if(reviews.length == 0){
            return false;
        }
        for(int i = 0; i < reviews.length; i++){
            if(reviews[i].getUsuario().getNick().equalsIgnoreCase(u.getNick())){
                if(reviews[i].getFechaReview() == ld){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean nuevaContestacion(Contestacion c, Review r) {//GRUPO2
        //Almacenar fecha contestacion
        //Comprobar si tiene contestacion
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean tieneContestacion(Review r) {//GRUPO2
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Contestacion obtenerContestacion(Review r) {//GRUPO2
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminaContestacion(Contestacion c) {//GRUPO2
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminaContestacion(Review r) {//GRUPO2
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean nuevoLocal(Local l) {
        locales.add(l);
        return true;
    }

    @Override
    public boolean eliminarLocal(Local l) {
        if(locales.size() < 1) {
            return false;
        }
        for(int i = 0; i < locales.size(); i++){
            if(locales.get(i).equals(l)){
                locales.remove(i);
                return true;
            }
        }
        return false;
    }

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

    @Override
    public boolean asociarLocal(Local l, Propietario p) {
        return l.addPropietario(p);
    }

    @Override
    public boolean desasociarLocal(Local l, Propietario p) {
        return l.removePropietario(p);
    }

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
    public Review[] verReviews(Local l) {//GRUPO2
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        while (encontradoCliente == 0 && user < TAMANO_LISTAS){
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
        while (encontradoLocal == 0 && local < TAMANO_LISTAS){
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
        while (encontradoReserva == 0 && reserva < TAMANO_LISTAS){
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
        while (encontradoCliente == 0 && user < TAMANO_LISTAS){
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
        while ( local < TAMANO_LISTAS){
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Obtiene todas las reservas del dia usado como argumento
     * @param ld la fecha a consultar
     * @return La lista de las reservas.
     */
    @Override
    public Reserva[] obtenerReservas(LocalDate ld) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Elimina una reserva del sistema, en caso de que esta exista
     * @param r La reserva a eliminar.
     * @return True si y solo si la operacion fue completada.
     */
    @Override
    public boolean eliminarReserva(Reserva r) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Local[] listarLocales(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Bar[] listarBares(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Restaurante[] listarRestaurantes(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Pub[] listarPubs(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float obtenerValoracionMedia(Local l) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float obtenerValoracionMedia(Propietario p) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public float obtenerValoracionMedia(Local l, int edadEntre, int edadHasta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Local[] obtenerLocalesOrdenados(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Local[] obtenerLocalesOrdenados(String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Bar[] obtenerBaresOrdenados(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Restaurante[] obtenerRestaurantesOrdenados(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Pub[] obtenerPubOrdenados(String ciudad, String provincia) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
