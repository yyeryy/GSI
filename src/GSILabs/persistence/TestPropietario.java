package GSILabs.persistence;

import GSILabs.BModel.Propietario;
import java.io.IOException;

public class TestPropietario {
    public static void main(String[] args) throws IOException {
        String str = "Propietario{nick=Ivana, contraseña=awerfrewfwe, fecha_de_nacimiento=2000-12-06}";
        Propietario propietario = parser.parsePropietario(str);
        System.out.println(str.equals(propietario.toString()));
    }
    
    // Test si no cumple la edad
    
    // Nick menor de 3 caracteres
}
