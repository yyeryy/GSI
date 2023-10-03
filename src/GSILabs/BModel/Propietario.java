/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GSILabs.BModel;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase Propietario
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Propietario extends Usuario{
    
    /**
     * Constructor Propietario
     * @param n Nick del propietario
     * @param c Contraseña del propietario
     * @param f Fecha de Nacimiento del propietario
     */
    public Propietario(String n, String c, LocalDate f) {
        super(n, c, f, tipoUsuario.PROPIETARIO);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getNick());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Propietario other = (Propietario) obj;
        return Objects.equals(getNick(), other.getNick());
    }

    @Override
    public String toString() {
        return "Propietario{" + "Nick=" + this.getNick() + " | Contraseña= " + this.getContraseña() + " | Fecha de Nacimiento= " + this.getFechaNacimiento().toString() + "}";
    }
    
}
