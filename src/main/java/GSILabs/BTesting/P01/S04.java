package GSILabs.BTesting.P01;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import static GSILabs.BModel.Local.tipoLocal.*;
import GSILabs.BModel.Propietario;
import static GSILabs.BModel.Usuario.tipoUsuario.PROPIETARIO;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;

/**
 * Clase S04
 * Si se añade un local, y se elimina posteriormente, se puede introducir un 
 * bar en la misma dirección
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class S04 {
    BusinessSystem bs = new BusinessSystem();
    
    /**
     * Ejecucion del test S04
     * @return Estado de la ejecución del Test
     */
    boolean testS4() {
        Direccion direccion = new Direccion("a","b","c",1);
        Propietario propietario = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), PROPIETARIO); 
        Local local = new Local("Local", direccion, "Local para el ejemplo S03", BAR, propietario);
        bs.nuevoLocal(local);
        bs.eliminarLocal(local);
        return bs.nuevoLocal(local);
    }
}
