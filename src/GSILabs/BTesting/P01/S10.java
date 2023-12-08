package GSILabs.BTesting.P01;

import GSILabs.BModel.Cliente;
import GSILabs.BSystem.BusinessSystem;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Local.tipoLocal;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Review;
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

    /**
     * Ejecucion del test S10
     * @return Estado de la ejecución del Test
     */
    boolean testS10() {
        Cliente usuario = new Cliente("Prueba", "1234", LocalDate.of(2000,1,1));
        Propietario propietario = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth())); 
        Local local = new Local("Casa pepe", new Direccion("Pamplona", "Navarra", "calle", 7), "el local", tipoLocal.BAR, propietario);
        Review review1 = new Review(3, "coment1", LocalDate.now(), local, usuario);
        bs.nuevaReview(review1);
        if(!bs.nuevaReview(review1)){
            System.out.println("No se pueden añadir dos Reviews del mismo usuario, el mismo día para el mismo local");
            return true;
        }
        System.out.println("Review añadida correctamente");
        return false;
    }
}