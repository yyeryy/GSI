package GSILabs.BTesting.P01;

import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;

/**
     * Si introduce a un usuario, este puede ser luego localizado a partir de su ID
*/
public class S01 {
    BusinessSystem bs = new BusinessSystem();
    
    public boolean testS1() {
        String nick = "Prueba";
        
        Usuario usuario = new Usuario("Prueba", "1234", LocalDate.of(2000,1,1), CLIENTE);
        if(bs.nuevoUsuario(usuario)) //Se crea el usuario con exito
            if(bs.obtenerUsuario(nick) == usuario)
                return true;
        return false;
    }
}