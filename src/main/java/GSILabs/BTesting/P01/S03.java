package GSILabs.BTesting.P01;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Direccion;
import static GSILabs.BModel.Local.tipoLocal.*;
import GSILabs.BSystem.BusinessSystem;

/**
 * No se puede introducir los Locales en la misma dirección;
 */
public class S03 {
    BusinessSystem bs = new BusinessSystem();
    
    
    boolean testS3() {
        Direccion direccion = new Direccion("a","b","c",1);
        Local local1 = new Local("Local1", direccion, "Local 1 para el ejemplo S03", BAR);
        Local local2 = new Local("Local2", direccion, "Local 2 para el ejemplo S03", RESTAURANTE);   
        bs.nuevoLocal(local1);
        return !bs.nuevoLocal(local2);
    }
}
