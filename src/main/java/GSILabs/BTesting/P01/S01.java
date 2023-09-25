package GSILabs.BTesting.P01;

import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;

/**
 * Clase S01
 * Si introduce a un usuario, este puede ser luego localizado a partir de su ID
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class S01 {
    BusinessSystem bs = new BusinessSystem();
    
    /**
     * Ejecucion del test S01
     * @return Estado de la ejecución del Test
     */
    public boolean testS1() {
        String nick = "Prueba";
        try{
            Usuario usuario = new Usuario(nick, "1234", LocalDate.of(2000,1,1), CLIENTE);
            if(bs.nuevoUsuario(usuario)) //Se crea el usuario con exito
            if(bs.obtenerUsuario(nick) == usuario){
		System.out.println("Usuario localizado a partir de su id");
                return true;
	    }
            else{
                System.out.println("El usuario no se ha localizado");
                return false;
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e);}
        return false;
    }
}