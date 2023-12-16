package GSILabs.MongoDB;

import GSILabs.BModel.Local;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Usuario;
import static GSILabs.MongoDB.ConexionBBDD.descargarLocalPropietario;
import static GSILabs.MongoDB.ConexionBBDD.descargarUsuario;

/**
 *
 * @author 34636
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Usuario usuario = descargarUsuario("Javier");
        System.out.println("Usuario:\n" + usuario);
        Local local = descargarLocalPropietario(new Propietario(usuario.getNick(),usuario.getContraseña(),usuario.getFechaNacimiento()));
        System.out.println("Local:\n" + local);
    }
    
}
