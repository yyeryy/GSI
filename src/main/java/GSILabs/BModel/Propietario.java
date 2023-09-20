/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.time.LocalDate;
import GSILabs.BSystem.BusinessSystem;

/**
 * Clase Propietario
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Propietario extends Usuario{
    
    /**
     * Instancia de la clase BusinessSystem de la cual utilizaremos sus métodos.
     */
    BusinessSystem businessSystem = new BusinessSystem();

    /**
     * Constructor Propietario
     * @param n Nick del propietario
     * @param c Contraseña del propietario
     * @param f Fecha de Nacimiento del propietario
     * @param t Tipo de Usuario
     */
    public Propietario(String n, String c, LocalDate f, tipoUsuario t) {
        super(n, c, f, t);
    }
    
    /**
     * Método realizarContestacion
     * El dueño puede realizar una Contestación a una Review, 
     * siempre y cuando el local al que se refiera sea suyo.
     * @param c Contestación a una Review
     * @param r Review en la que el dueño realiza la contestación
     */
    public void realizarContestacion(Contestacion c, Review r) {
        
        businessSystem.nuevaContestacion(c, r);
    }
}
