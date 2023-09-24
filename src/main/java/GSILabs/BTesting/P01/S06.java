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
 * Clase S06
 * No se pueden hacer reservas para un local inexistente
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class S06 {
    BusinessSystem bs = new BusinessSystem();
    
    /**
     * Ejecucion del test S06
     * @return Estado de la ejecución del Test
     */
    boolean testS6() {
        Cliente cliente = new Cliente("Prueba", "1234", LocalDate.of(2000,1,1),CLIENTE);
        bs.nuevoUsuario(cliente);
        Direccion direccion = new Direccion("a","b","c",1);
        Propietario propietario = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), PROPIETARIO); 
        Bar bar = new Bar("Bar", direccion, "Local para el ejemplo S06", propietario);
        return !bs.nuevaReserva(cliente, bar, LocalDate.MIN, LocalTime.MIN);
    }
}
