/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.time.LocalDate;
import GSILabs.BSystem.BusinessSystem;
import java.util.Objects;

/**
 * Clase Cliente
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Cliente extends Usuario {
    
    /**
     * Instancia de la clase BusinessSystem de la cual utilizaremos sus métodos.
     */
    BusinessSystem businessSystem = new BusinessSystem();
    
    /**
     * Constructor Cliente
     * @param n Nick del cliente
     * @param c Contraseña del cliente
     * @param f Fecha de nacimiento del cliente
     * @param t Tipo de usuario
     */
    public Cliente(String n, String c, LocalDate f, tipoUsuario t) {
        super(n, c, f, t);
    }

    /**
     * Método realizarReview
     * Un cliente puede hacer reviews de los locales.
     * @param local Local al cual el cliente va a hacer una review
     * @param valoracion Valoración que da el cliente al local
     * @param comentario Comentario que hace el cliente al local
     */
    public void realizarReview(Local local, int valoracion, String comentario){
        if(valoracion > 5 || valoracion < 0){
            throw new IllegalArgumentException("La valoracion debe ser entre 0 y 5"); // revisar esto que hace y sino solo sout
        }else if(comentario.length() > 500){
            throw new IllegalArgumentException("El comentario debe tener menos de 500 caracteres"); // revisar esto que hace y sino solo sout
        }else if(!businessSystem.existeRewiew(this, local, LocalDate.now())){
            throw new IllegalArgumentException("Ya existe una review del mismo dia y usuario"); // revisar esto que hace y sino solo sout
        }
        
        businessSystem.nuevaReview(new Review(valoracion, comentario, LocalDate.now(), local, this));
    }

    /**
     * Método accederLocales
     * Un Cliente es un usuario de la página que puede
     * acceder a la información de los locales de ocio.
     * @param ciudad Ciudad en la cual se muestran los locales de ocio
     * @param provincia Provincia en la cual se muestran los locales de ocio
     */
    public void accederLocales(String ciudad, String provincia) {
        Local locales[] = businessSystem.listarLocales(ciudad, provincia);
        //Imprimir locales
    }


}
