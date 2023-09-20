/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.time.LocalDate;

/**
 * Clase Cliente
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Cliente extends Usuario {

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

    @Override
    public String toString() {
        return "Cliente{" + "Nick=" + this.getNick() + " | Contraseña= " + this.getContraseña() + " | Fecha de Nacimiento= " + this.getFechaNacimiento().toString() + " | Tipo de Usuario= " + this.getTipo().toString() + "}";
    }
    
}
