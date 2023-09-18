/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

/**
 *
 * @author Ivan1
 */
public class ClienteReserva {
    
    private Cliente c;
    private Reserva R;

    public ClienteReserva(Cliente c, Reserva R) {
        this.c = c;
        this.R = R;
    }

    public Cliente getC() {
        return c;
    }

    public Reserva getR() {
        return R;
    }

}
