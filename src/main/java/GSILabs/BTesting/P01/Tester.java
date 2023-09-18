package GSILabs.BTesting.P01;

import GSILabs.BModel.Usuario;
import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import GSILabs.BSystem.BusinessSystem;
import java.time.LocalDate;
/**
 *
 * @author arang
 */
public class Tester {
    BusinessSystem bs;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Inicio de test");
        Tester tester = new Tester();
        if(!tester.testS1()){
            System.out.println("Test 1 incompleto");
        }
    }
    
    /**
     * Si introduce a un usuario, este puede ser luego localizado a partir de su ID
     */
    public boolean testS1() {
        System.out.println("Test 1");
        Usuario usuario = new Usuario("Prueba", "1234", LocalDate.of(2000,1,1), CLIENTE);
        if(bs.nuevoUsuario(usuario)) //Se crea el usuario con extio
        {
            System.out.println("Usuario creado");
            return true;
        }
        return false;
    }
    
    
    /*
    * Si busca a un usuario que no existe con ObtenerUsuario, el resultado es null
    */
    public boolean testS2() {
        System.out.println("Test 2");
	bs.nuevoUsuario(new Usuario("Marilin", "1234", LocalDate.of(2000,1,1), CLIENTE)); // añadimos usuario al sistema
        if(bs.obtenerUsuario("Vega") == null)
        {
            System.out.println("Usuario es null si no existe");
            return true;
        }
        return false;
    }
    
    /* 
    * No se puede introducir un usuario menor de edad
    */
    public boolean testS5() {
        System.out.println("Test 5");
	Usuario usuario = new Usuario("Marilin", "1234", LocalDate.of(2020,1,1), CLIENTE); 
        if(bs.nuevoUsuario(usuario)) // si es true es que el usuario se ha añadido
        {
            System.out.println("Usuario menor de edad añadido");
            return false;
        }
	System.out.println("No se ha añadido al usuario menor de edad");
        return true;
    }
    
}