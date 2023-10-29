package GSILabs.persistence;

import GSILabs.BModel.Direccion;
import java.io.IOException;

public class TestDireccion {
    public static void main(String[] args) throws IOException {
        String str = "Dirección{localidad=Pamplona, provincia=Gran_Reino_De_Navarra, calle=Suuuuu, numero=1}";
        Direccion direccion = parser.parseDireccion(str);
        System.out.println(str.equals(direccion.toString()));
    }
    
    // Test si no cumple la edad
    
    // Nick menor de 3 caracteres
}
