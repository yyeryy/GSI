/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GSILabs.BModel;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import GSILabs.BModel.Reserva;

/**
 * Clase Reservable
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 * Tanto los restaurantes como los Bares son Reservables. El sistema debe poder almacenar Reservas
 * de Clientes para Bares o Restaurantes. Cada Reserva incluye únicamente la fecha y hora en que
 * se efectuará, así como un posible porcentaje de descuento.
 */
public interface Reservable {
    
    /**
     * Lista de Reservas de un Local Reservable (Bar o Restaurante)
     */
    public ArrayList<Reserva> listaReserva = new ArrayList<Reserva>();

    /**
     * Dado un Cliente existente sin ninguna reserva en ese día, una fecha y hora futura,
     * Crear y Guarda una nueva Reserva en la lista de Reserva
     * @param cliente El Cliente
     * @param fecha Fecha de la reserva
     * @param hora Hora de la reserva
     */
    default void nuevaReserva(Cliente cliente, LocalDate fecha, LocalTime hora){

        int descuento = 0;
        Reserva reserva = new Reserva(cliente, fecha, hora, descuento);
        listaReserva.add(reserva);

    };

    /**
     * Dado un Cliente existente,
     * Obtener todas las Reservas (pasadas y futuras) del Cliente
     * en el Local Reservables 
     * @param c El Cliente
     * @return La lista de las reservas del Cliente, o null si no existe ninguna reserva.
     */
    default Reserva[] reservasDeCliente(Cliente c){
        if(listaReserva.size() > 0){
            int pos = 0;
            ArrayList<Reserva> todasReservas = new  ArrayList<Reserva>();
            while(pos < listaReserva.size()){
                if(listaReserva.get(pos).getCliente().equals(c)){
                    todasReservas.add(listaReserva.get(pos));
                }
                pos++;
            }

            if(todasReservas.size() > 0){
                pos = 0;
                Reserva[] reservas = new Reserva[todasReservas.size()];
                while(pos < todasReservas.size()){
                    reservas[pos] = (todasReservas.get(pos));
                    pos++;
                }
                return reservas;
            }
        }
        return null;
    }

    /**
     * Dado una Reserva,
     * Comprobar si la Reserva existe en el Local Reservable
     * @param r La Reserva
     * @return True si y solo si existe la Reserva en el Local Reservable
     */
    default boolean comprobarReserva(Reserva r){
        int pos = 0;
        int encontrada = 0;
        while(pos < listaReserva.size() && encontrada == 0){
            if(listaReserva.get(pos).equals(r)){
                encontrada = 1;
            }
            pos++;
        }
        if(encontrada == 1){
            return true;
        }
        return false;
    }

    /**
     * Dado una Reserva del Local Reservable,
     * Eliminar la Reserva de la Lista de Reservas del Local Reservable
     * @param r La Reserva
     * @return True si y solo si se ha elimina la Reserva de la Lista de Reservas
     */
    default boolean eliminarReserva(Reserva r){
        return listaReserva.remove(r);

    }

    /**
     * Obtener todas las Reservas (pasadas y futuras) del Local Reservable
     * @return La lista de las reservas, o null si no existe ninguna reserva.
     */
    default Reserva[] todasReserva(){
        if(listaReserva.size() > 0){
            Reserva[] reservas = new Reserva[listaReserva.size()];
            int pos = 0;
            while(pos < listaReserva.size()){
                reservas[pos] = listaReserva.get(pos);
                pos++;
            }
            return reservas;
        }
        return null;
    }
    
    /**
     * Dada una Fecha,
     * Obtener todas las Reservas en la Fecha en el Local Reservable
     * @param ld La Fecha
     * @return La lista de las reservas para esa Fecha, o null si no existe ninguna reserva.
     */
    default Reserva[] reservasDeDia(LocalDate ld){
        if(listaReserva.size() > 0){
            int pos = 0;
            ArrayList<Reserva> diaReservas = new  ArrayList<Reserva>();
            while(pos < listaReserva.size()){
                if(listaReserva.get(pos).getFecha().equals(ld)){
                    diaReservas.add(listaReserva.get(pos));
                }
                pos++;
            }

            if(diaReservas.size() > 0){
                pos = 0;
                Reserva[] reservas = new Reserva[diaReservas.size()];
                while(pos < diaReservas.size()){
                    reservas[pos] = (diaReservas.get(pos));
                    pos++;
                }
                return reservas;
            }
        }
        return null;
    }

}
