package GSILabs.BTesting.P01;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import static GSILabs.BModel.Local.tipoLocal.BAR;
import GSILabs.BModel.Reservable;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * No se pueden hacer reservas para un local inexistente;
 */
public class S06 {
    BusinessSystem bs = new BusinessSystem();
    
    boolean testS6() {
        Cliente cliente = new Cliente("Prueba", "1234", LocalDate.of(2000,1,1),CLIENTE);
        bs.nuevoUsuario(cliente);
        Direccion direccion = new Direccion("a","b","c",1);
        Bar bar = new Bar("Bar", direccion, "Local para el ejemplo S06");
        return !bs.nuevaReserva(cliente, bar, LocalDate.MIN, LocalTime.MIN);
    }
}
