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
	
	
	//1
        S01 s01 = new S01();
        System.out.print("Test 1:");
        if(s01.testS1())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");

        //2
        S02 s02 = new S02();
        System.out.print("Test 2:");
        if(s02.testS2())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");

        //3
        S03 s03 = new S03();
        System.out.print("Test 3:");
        if(s03.testS3())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");

        //4
        S04 s04 = new S04();
        System.out.print("Test 4:");
        if(s04.testS4())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");

        //5
        S05 s05 = new S05();
        System.out.print("Test 5:");
        if(s05.testS5())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");

        //6
        S06 s06 = new S06();
        System.out.print("Test 6:");
        if(s06.testS6())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");

        //7
        S07 s07 = new S07();
        System.out.print("Test 7:");
        if(s07.testS7())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");

        //8
        S08 s08 = new S08();
        System.out.print("Test 8:");
        if(s08.testS8())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");

        //9
        S09 s09 = new S09();
        System.out.print("Test 9:");
        if(s09.testS9())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");

        //10
        S10 s10 = new S10();
        System.out.print("Test 10:");
        if(s10.testS10())
            System.out.println("\tcompleto");
        else
            System.out.println("\n\tINCOMPLETO");
	
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