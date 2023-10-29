package GSILabs.persistence;

import GSILabs.BModel.Usuario;
import java.io.IOException;

public class TestUsuario {
    public static void main(String[] args) throws IOException {
        String str = "Usuario{nick=Ivana, contraseña=awerfrewfwe, fecha_de_nacimiento=2000-12-06, tipo=PROPIETARIO}";
        Usuario usuario = parser.parseUsuario(str);
        System.out.println(str.equals(usuario.toString()));
    }
    
    // Test si no cumple la edad
    
    // Nick menor de 3 caracteres
}
