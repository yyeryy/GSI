package GSILabs.persistence;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Reserva;
import GSILabs.BModel.Restaurante;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TestContestacion {
    public static void main(String[] args) throws IOException {

        String str = ""
                    + "Contestacion{"
                    +"comentario=Restaurante, "
                    +"fechaReview=2000-12-02, "
                    +"local=Local{"
                        + "nombre=ISusiIvana, "
                        + "dirección=Dirección{"
                            + "localidad=Pamplona, "
                            + "provincia=Gran_Reino_De_Navarra, "
                            + "calle=Suuuuu, numero=1}, "
                        + "descripción=almuerzo, "
                        + "tipo=BAR, "
                        + "Propietario=Propietario{"
                            + "nick=Ivana, "
                            + "contraseña=awerfrewfwe, "
                            + "fecha_de_nacimiento=2000-12-06}"
                        + "}"
                    + "}";


        System.out.println("Original: \n" + str);
        Contestacion contestacion = parser.parseContestacion(str);
        System.out.println("Final: \n" + contestacion.toString());
        System.out.println(str.equals(contestacion.toString()));
    }
    
 
}
