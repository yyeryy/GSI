package GSILabs.BTesting.P01;

import GSILabs.BModel.Local;
import GSILabs.BModel.Direccion;
import static GSILabs.BModel.Local.tipoLocal.*;
import GSILabs.BModel.Propietario;
import static GSILabs.BModel.Usuario.tipoUsuario.PROPIETARIO;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;

/**
 * No se puede introducir los Locales en la misma dirección;
 */
public class S03 {
    BusinessSystem bs = new BusinessSystem();
    
    
    boolean testS3() {
        Direccion direccion = new Direccion("a","b","c",1);
        Propietario propietario = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), PROPIETARIO); 
        Local local1 = new Local("Local1", direccion, "Local 1 para el ejemplo S03", BAR, propietario);
        Local local2 = new Local("Local2", direccion, "Local 2 para el ejemplo S03", RESTAURANTE, propietario);   
        bs.nuevoLocal(local1);
        return !bs.nuevoLocal(local2);
    }
}
