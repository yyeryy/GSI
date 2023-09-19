package GSILabs.BTesting.P01;

import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;

/**
 * Si busca a un usuario que no existe con ObtenerUsuario, el resultado es null;
 */
public class S02 {
    BusinessSystem bs = new BusinessSystem();
    
    boolean testS2() {
        bs.nuevoUsuario(new Usuario("Marilin", "1234", LocalDate.of(2000,1,1), CLIENTE)); // añadimos usuario al sistema
        if(bs.obtenerUsuario("Vega") == null)
        {
            return true;
        }
        return false;
    }
}
