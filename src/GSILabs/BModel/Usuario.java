package GSILabs.BModel;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase Usuario
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 * El sistema almacena información de usuarios. Cada usuario tiene un nick único de al menos tres
 * caracteres y una contraseña. Además, se debe almacenar la información de la fecha de nacimiento
 * del mismo. No se permiten usuario de menos de 14 años. Cada usuario debe tener un perfil
 * determinado, a escoger entre Propietario y Cliente.
 */
public class Usuario {

    private String nick;
    private String contraseña;
    private LocalDate fechaNacimiento;
    private tipoUsuario tipo;

    /**
     * Tipo de usuario que se permiten en la aplicación.
     */
    public enum tipoUsuario {
        /**
         * Indica si el usuario es propietario de un local.
         */
        PROPIETARIO,
        /**
         * Indica si un usuario es cliente de un local.
         */
        CLIENTE
    }

    /**
     * Constructor Usuario
     * @param n id o nombre del usuario
     * @param c su contraseña para iniciar sesion
     * @param f su fecha de nacimiento
     * @param t si el usuario es cliente o propietario
     */
    
    public Usuario(String n, String c, LocalDate f, tipoUsuario t) {
        nickValido(n); // this.nick = nick;
	contraseñaValido(c); // this.contraseña = password;
	fechaNacimientoValido(f); // this.fechaNacimiento = fechaNacimiento;
	tipo = t;
    }
    
    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public String getNick() {
	return nick;
    }
    
    public void setNick(String nick) {
	this.nick = nick;
    }
    
    public String getContraseña() {
	return contraseña;
    }
    
    public void setContraseña(String contraseña) {
	this.contraseña = contraseña;
    }
    
    public LocalDate getFechaNacimiento() {
	return fechaNacimiento;
    }
    
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
    }
    
    public tipoUsuario getTipo() {
	return tipo;
    }
    
    public void setTipo(tipoUsuario tipo) {
	this.tipo = tipo;
    }
//</editor-fold>

    /**
     * Función que comprueba que el nick proporcionado supere los 2 caracteres.
     * @param n Nick del usuario a comprobar.
     */
    public void nickValido(String n) {
        if (n.length() < 3) {
            throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres."); // revisar esto que hace y sino solo sout
        }
        this.nick = n;
    }
    
    /**
     * Función que comprueba que la contraseña no sea nula
     * @param c Contraseña del usuario a comprobar.
     */
    public void contraseñaValido(String c) {
        if (c.length() < 1) {
            throw new IllegalArgumentException("La contraseña no puede ser nula."); // revisar esto que hace y sino solo sout
        }
        this.contraseña = c;
    }
    
    /**
     * Función que comprueba que la fecha de nacimiento cumpla con la edad minima permitida por el sistema
     * @param f Fecha de nacimiento del usuario a comprobar.
     */
    public void fechaNacimientoValido(LocalDate f) {
        if (f.getYear() > LocalDate.now().getYear()-14) {
            throw new IllegalArgumentException("La fecha de nacimiento no cumple con la edad minima permitida por el sistema."); // revisar esto que hace y sino solo sout
        }
        this.fechaNacimiento = f;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nick);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Usuario other = (Usuario) obj;
        return Objects.equals(nick, other.nick);
    }

    @Override
    public String toString() {
        return "Usuario{" + "nick=" + nick + ", contrasena=" + contraseña + ", fechaNacimiento=" + fechaNacimiento.toString() + ", tipo=" + tipo + '}';
    }

}
