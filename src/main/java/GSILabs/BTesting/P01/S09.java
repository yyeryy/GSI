package GSILabs.BTesting.P01;

import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import static GSILabs.BModel.Local.tipoLocal.PUB;
import GSILabs.BModel.Propietario;
import static GSILabs.BModel.Usuario.tipoUsuario.PROPIETARIO;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;

/**
 * Clase S09
 * No se pueden añadir cuatro dueños a un bar
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class S09 {
    BusinessSystem bs = new BusinessSystem();
    
    /**
     * Ejecucion del test S09
     * @return Estado de la ejecución del Test
     */
    boolean testS9() {
        // Creo los 4 dueños y los añado al sistema
        for(int i = 0; i<4; i++)
        {
            Propietario propietario = new Propietario("dueno"+i+"","1234",LocalDate.of(2000,1,1),PROPIETARIO);
            bs.nuevoUsuario(propietario);
        }
        // Creo un local para añadirle dueños
        Direccion direccion = new Direccion("a","b","c",1);
        Propietario propietario = new Propietario("Juanjo", "1234", LocalDate.of(LocalDate.now().getYear()-18,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), PROPIETARIO); 
        Local local = new Local("Pruebas", direccion, "Pub de prueba", PUB, propietario);
        // Añado los dueños
        try{
            for(int i = 0; i<4; i++)
                bs.asociarLocal(local, (Propietario) bs.obtenerUsuario("dueno"+i+""));
        }catch(IllegalArgumentException e){
            return true;}
        return false;
    }
}
