package GSILabs.BModel;

/**
 * Clase SesionUsuario
 * Clase mediante la que se almacena la información del usuario en un perfil
 * de la aplicación DonaAplicacion.
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 02.12.2023
 */
public class SesionUsuario {

    private String nombre;
    private String contrasena;
    private String tipoUsuario;

    public SesionUsuario(String nombre, String contrasena, String tipoUsuario) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
}
