/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package GSILabs.BTesting.P01;

import GSILabs.BModel.Usuario;
import GSILabs.BModel.Usuario.tipoUsuario;
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
}