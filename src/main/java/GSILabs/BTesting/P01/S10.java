package GSILabs.BTesting.P01;

import GSILabs.BSystem.BusinessSystem;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Local.tipoLocal;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import static GSILabs.BModel.Usuario.tipoUsuario.PROPIETARIO;
import java.time.LocalDate;

/**
 * Clase S10
 * No se pueden añadir dos Reviews del mismo usuario, el mismo día para el mismo local.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class S10 {
    BusinessSystem bs = new BusinessSystem();

    boolean testS10() {
        Usuario usuario = new Usuario("Prueba", "1234", LocalDate.of(2000,1,1), CLIENTE);
        Propietario propietario = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), PROPIETARIO); 
        Local local = new Local("Casa pepe", new Direccion("Pamplona", "Navarra", "calle", 7), "el local", tipoLocal.BAR, propietario);
        Review review1 = new Review(3, "coment1", LocalDate.now(), local, usuario);
        bs.nuevaReview(review1);
        return !bs.nuevaReview(review1);
    }
}