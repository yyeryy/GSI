package GSILabs.BTesting.P01;

import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;

/**
 * Clase S05
 * No se puede introducir un usuario menor de edad
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class S05 {
    BusinessSystem bs = new BusinessSystem();
    
    /**
     * Ejecucion del test S05
     * @return Estado de la ejecución del Test
     */
    boolean testS5() {
        try{
            new Usuario("Prueba", "1234", LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), CLIENTE); 
        }
        catch(IllegalArgumentException e)
        {
	    System.out.println(e);
            return true;
        }
	System.out.println("El usuario es mayor de edad y se ha creado correctamente");
        return false;
    } 
}
