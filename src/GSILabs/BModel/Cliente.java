package GSILabs.BModel;

import static GSILabs.BModel.Usuario.tipoUsuario.CLIENTE;
import java.time.LocalDate;
import java.util.Objects;

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
     */
    public Cliente(String n, String c, LocalDate f) {
        super(n, c, f, CLIENTE);
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
        Cliente other = (Cliente) obj;
        return Objects.equals(getNick(), other.getNick());
    }

    @Override
    public String toString() {
        return "Cliente{" + "nick=" + this.getNick() + ", contraseña=" + this.getContraseña() + ", fecha_de_nacimiento=" + this.getFechaNacimiento().toString() + ", tipo=" + this.getTipo().toString() + "}";
    }
}
