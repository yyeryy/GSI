package GSILabs.BTesting.P01;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
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
        Cliente cliente = new Cliente("Alfonso", "1234", LocalDate.of(2000,1,1));
        bs.nuevoUsuario(cliente);
        Direccion direccion = new Direccion("Pamplona","Navarra","kalea",1);
        Propietario propietario = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth())); 
        Bar bar = new Bar("Bar", direccion, "Local para el ejemplo S06", propietario);
        if (bs.nuevaReserva(cliente, bar, LocalDate.MIN, LocalTime.MIN)){
	    System.out.println("Se ha creado una reserva para un local inexistente");
	    return false;
	}
	System.out.println("No se pueden hacer reservas para un local inexistente");
	return true;
    }
}
