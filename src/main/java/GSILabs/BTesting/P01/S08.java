package GSILabs.BTesting.P01;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import static GSILabs.BModel.Local.tipoLocal.BAR;
import GSILabs.BModel.Reservable;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * No se pueden añadir comentarios para Reviews que no existen;
 */
public class S08 {
    BusinessSystem bs = new BusinessSystem();
    
    boolean testS8() {
        Usuario usuario = new Usuario("Prueba", "1234", LocalDate.of(2000,1,1), CLIENTE);
        Local local = new Local("Casa pepe", new Direccion("Pamplona", "Navarra", "calle", 7), "el local", Local.tipoLocal.BAR);
        Contestacion contestacion = new Contestacion("comentario", LocalDate.now(), local);
        return !bs.nuevaContestacion(contestacion, new Review(3, "coment1", LocalDate.now(), local, usuario));
    }
}