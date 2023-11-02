package GSILabs.persistence;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Reserva;
import GSILabs.BModel.Restaurante;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class TestPub {
    public static void main(String[] args) throws IOException {

        String str = ""
                    + "Pub{"
                    +"nombre=Pub, "
                    +"dirección=Dirección{"
                        +"localidad=Pamplona, "
                        + "provincia=Navarra, "
                        + "calle=kalea, "
                        + "numero=1}, "
                    + "descripción=Local para el ejemplo S06, "
                    + "tipo=PUB, "
                    + "propietario=Propietario{"
                        + "nick=Juanjo, "
                        + "contraseña=1234, "
                        + "fecha_de_nacimiento=2005-11-02}, "
                    + "hora Apertura=15:00, "
                    + "hora Clausura=23:30}";

        System.out.println("Original: \n" + str);
        Pub pub = parser.parsePub(str);
        System.out.println("Final: \n" + pub.toString());
        System.out.println(str.equals(pub.toString()));
    }
    
 
}
