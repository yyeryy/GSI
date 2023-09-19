package GSILabs.BTesting.P01;

import GSILabs.BSystem.BusinessSystem;
import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Local.tipoLocal;
import static GSILabs.BModel.Local.tipoLocal.BAR;
import GSILabs.BModel.Reservable;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * No se pueden añadir dos Reviews del mismo usuario, el mismo día para el mismo local.
 */
public class S10 {
    BusinessSystem bs = new BusinessSystem();

    boolean testS10() {
        Usuario usuario = new Usuario("Prueba", "1234", LocalDate.of(2000,1,1), CLIENTE);
        Local local = new Local("Casa pepe", new Direccion("Pamplona", "Navarra", "calle", 7), "el local", tipoLocal.BAR);
        Review review1 = new Review(3, "coment1", LocalDate.now(), local, usuario);
        bs.nuevaReview(review1);
        return !bs.nuevaReview(review1);
    }
}