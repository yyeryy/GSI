package GSILabs.BTesting.P01;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import static GSILabs.BModel.Usuario.tipoUsuario.PROPIETARIO;
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
        Propietario propietario = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), PROPIETARIO); 
        Bar bar1 = new Bar("Bar1", direccion, "Bar1 para el ejemplo S07", propietario);
        bs.nuevoLocal(bar1);
        // Hacer reserva en un bar falso con la misma direccion
        Bar bar2 = new Bar("Bar2", direccion, "Bar2 para el ejemplo S07", propietario);
        return !bs.nuevaReserva(cliente, bar2, LocalDate.MIN, LocalTime.MIN);
    }
}
