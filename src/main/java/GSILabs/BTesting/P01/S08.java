package GSILabs.BTesting.P01;

import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import static GSILabs.BModel.Usuario.tipoUsuario.PROPIETARIO;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;

/**
 * Clase S08
 * No se pueden añadir comentarios para Reviews que no existen
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class S08 {
    BusinessSystem bs = new BusinessSystem();
    
    boolean testS8() {
        Usuario usuario = new Usuario("Prueba", "1234", LocalDate.of(2000,1,1), CLIENTE);
        Propietario propietario = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), PROPIETARIO); 
        Local local = new Local("Casa pepe", new Direccion("Pamplona", "Navarra", "calle", 7), "el local", Local.tipoLocal.BAR, propietario);
        Contestacion contestacion = new Contestacion("comentario", LocalDate.now(), local);
        return !bs.nuevaContestacion(contestacion, new Review(3, "coment1", LocalDate.now(), local, usuario));
    }
}