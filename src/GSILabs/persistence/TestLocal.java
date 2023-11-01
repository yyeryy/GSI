package GSILabs.persistence;

import GSILabs.BModel.Local;
import java.io.IOException;

public class TestLocal {
    public static void main(String[] args) throws IOException {
        String str = ""
                + "Local{"
                + "nombre=ISusiIvana, "
                + "direccion=Dirección{"
                    + "localidad=Pamplona, "
                    + "provincia=Gran_Reino_De_Navarra, "
                    + "calle=Suuuuu, numero=1}, "
                + "descripción=almuerzo, "
                + "tipo=BAR, "
                + "Propietario=Propietario{"
                    + "nick=Ivana, "
                    + "contraseña=awerfrewfwe, "
                    + "fecha_de_nacimiento=2000-12-06}, "
                + "Propietario=Propietario{"
                    + "nick=Javiera, "
                    + "contraseña=awerfrewfsswe, "
                    + "fecha_de_nacimiento=2000-12-06}"
                + "}";
        System.out.println("Original: \n" + str);
        Local local = parser.parseLocal(str);
        System.out.println("Final: \n" + local.toString());
        System.out.println(str.equals(local.toString()));
    }
    
    // Test si no cumple la edad
    
    // Nick menor de 3 caracteres
}