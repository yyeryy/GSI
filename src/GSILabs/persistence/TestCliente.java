package GSILabs.persistence;

import GSILabs.BModel.Cliente;
import java.io.IOException;

public class TestCliente {
    public static void main(String[] args) throws IOException {
        String str = "Cliente{nick=Ivana, contraseña=awerfrewfwe, fecha_de_nacimiento=2000-12-06}";
        Cliente cliente = parser.parseCliente(str);
        System.out.println(str.equals(cliente.toString()));
    }
    
    // Test si no cumple la edad
    
    // Nick menor de 3 caracteres
}
