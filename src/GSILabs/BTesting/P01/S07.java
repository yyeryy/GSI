package GSILabs.BTesting.P01;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import static GSILabs.BModel.Usuario.tipoUsuario.PROPIETARIO;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;

/**
 * Clase S07
 * No se pueden hacer reservas para un local inexistente, aunque esté en la misma dirección que
 * otro existente
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class S07 {
    BusinessSystem bs = new BusinessSystem();
    
    /**
     * Ejecucion del test S07
     * @return Estado de la ejecución del Test
     */
    boolean testS7() {
        // Creo al cliente
        Cliente cliente = new Cliente("Jorhe", "1234", LocalDate.of(2000,1,1));
        bs.nuevoUsuario(cliente);
        // Creo el local
        Direccion direccion = new Direccion("Pamplona","Navarra","kalea",1);
        Propietario propietario = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), PROPIETARIO); 
        Bar bar1 = new Bar("Bar1", direccion, "Bar1 para el ejemplo S07", propietario);
        bs.nuevoLocal(bar1);
        // Hacer reserva en un bar falso con la misma direccion
        Bar bar2 = new Bar("Bar2", direccion, "Bar2 para el ejemplo S07", propietario);
	if (bs.nuevoLocal(bar1)){
	    System.out.println("Se ha creado un local en la direccion de otro");
	    return false;
	}
	System.out.println("No se pueden poner dos locales en la misma direccion");
        return true;
    }
}
