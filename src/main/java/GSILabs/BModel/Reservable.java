/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package GSILabs.BModel;

import java.util.ArrayList;
import GSILabs.BModel.Reserva;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 34636
 */
public interface Reservable {
/*
    Tanto los restaurantes como los Bares son Reservables. El sistema debe poder almacenar Reservas
de Clientes para Bares o Restaurantes. Cada Reserva incluye únicamente la fecha y hora en que
se efectuará, así como un posible porcentaje de descuento.
*/

    public ArrayList<ClienteReserva> listaReserva = new  ArrayList<ClienteReserva>();

    default void nuevaReserva(Cliente cliente, LocalDate fecha, LocalTime hora){

        int descuento = 0;
        Reserva reservaEntrante = new Reserva(fecha, hora, descuento);
        ClienteReserva nuevoClienteReserva = new ClienteReserva(cliente, reservaEntrante);
        listaReserva.add(nuevoClienteReserva);

    };

    default Reserva[] reservasDeCliente(Cliente c){
        return null;
    }

    default boolean comprobarReserva(Reserva r){
        int pos = 0;
        int encontrada = 0;
        while(pos < listaReserva.size() && encontrada == 0){
            if(listaReserva.get(pos).getR().equals(r)){
                encontrada = 1;
            }
            pos++;
        }
        if(encontrada == 1){
        return true;
        }
        return false;
    }

    default boolean eliminarReserva(Reserva r){
        if(comprobarReserva(r)){
            int pos = 0;
            int encontrada = 0;
            while(pos < listaReserva.size() && encontrada == 0){
                if(listaReserva.get(pos).getR().equals(r)){
                    listaReserva.remove(pos);
                    encontrada = 1;
                }
                pos++;
            }
            if(encontrada == 1){
                return true;
            }
        }
        return false;
    }

    default Reserva[] todasReserva(){
        if(listaReserva.size() > 0){
            Reserva[] reservas = new Reserva[listaReserva.size()];
            int pos = 0;
            while(pos < listaReserva.size()){
                reservas[pos] = (listaReserva.get(pos).getR());
                pos++;
            }
            return reservas;
        }
        return null;
    }
    
    default Reserva[] reservasDeDia(LocalDate ld){
        return null;
    }

}
