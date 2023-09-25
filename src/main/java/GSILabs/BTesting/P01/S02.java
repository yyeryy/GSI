package GSILabs.BTesting.P01;

import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;

/**
 * Clase S02
 * Si busca a un usuario que no existe con ObtenerUsuario, el resultado es null
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class S02 {
    BusinessSystem bs = new BusinessSystem();
    
    /**
     * Ejecucion del test S02
     * @return Estado de la ejecución del Test
     */
    boolean testS2() {
        try{
            bs.nuevoUsuario(new Usuario("Juan", "1234", LocalDate.of(2000,1,1), CLIENTE));} // añadimos usuario al sistema
        catch(IllegalArgumentException e){
            System.out.println(e);}
        if(bs.obtenerUsuario("Vega") == null)
        {
            return true;
        }
        return false;
    }
}
