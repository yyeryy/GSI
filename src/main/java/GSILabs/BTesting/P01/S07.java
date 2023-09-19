package GSILabs.BTesting.P01;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Direccion;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * No se pueden hacer reservas para un local inexistente, aunque esté en la misma dirección que
otro existente;
 */
public class S07 {
    BusinessSystem bs = new BusinessSystem();
    
    boolean testS7() {
        // Creo al cliente
        Cliente cliente = new Cliente("Prueba", "1234", LocalDate.of(2000,1,1),CLIENTE);
        bs.nuevoUsuario(cliente);
        // Creo el local
        Direccion direccion = new Direccion("a","b","c",1);
        Bar bar1 = new Bar("Bar1", direccion, "Bar1 para el ejemplo S07");
        bs.nuevoLocal(bar1);
        // Hacer reserva en un bar falso con la misma direccion
        Bar bar2 = new Bar("Bar2", direccion, "Bar2 para el ejemplo S07");
        return !bs.nuevaReserva(cliente, bar2, LocalDate.MIN, LocalTime.MIN);
    }
}
