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

    ArrayList<ClienteReserva> listaReserva = new  ArrayList<ClienteReserva>();

    public void nuevaReserva(Cliente c, LocalDate ld, LocalTime lt);

    public Reserva[] reservasDeCliente(Cliente c);

    public boolean estaReserva(Reserva r);

    public boolean eliminarReserva(Reserva r);

    public Reserva[] todasReserva();
    
    public Reserva[] reservasDeDia(LocalDate ld);

}
