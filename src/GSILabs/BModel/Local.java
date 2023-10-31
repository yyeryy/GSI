package GSILabs.BModel;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase Local
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Local {
public class Local{
    private String nombre;
    private Direccion direccion;
    private String descripcion;
    private tipoLocal tipo;
    private ArrayList<Usuario> propietarios;

    public enum tipoLocal {
        BAR,
        RESTAURANTE,
        PUB
    }

    /**
     * Constructor Local
     * @param nombre Nombre del local
     * @param direccion Dirección del local
     * @param descripcion Descripción del local
     * @param tipo Tipo del local (Bar, Restaurante o Pub)
     * @param propietario Propietario del local
     */
    public Local(String nombre, Direccion direccion, String descripcion, tipoLocal tipo, Propietario propietario) {
        this.nombre = nombre;
        this.direccion = direccion;
        descripcionValida(descripcion);
        this.tipo = tipo;
        this.propietarios = new ArrayList<>();
        this.propietarios.add(propietario);
    }
    
    //<editor-fold defaultstate="collapsed" desc="getters and setters">
    public String getNombre() {
        return nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public tipoLocal getTipo() {
        return tipo;
    }
    
    public ArrayList<Usuario> getPropietarios() {
        return propietarios;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(tipoLocal tipo) {
        this.tipo = tipo;
    }
    //</editor-fold>
    
    
    /**
     * Función que comprueba que la descripción del local no supere los 300 caracteres.
     * @param desc Descripciónde del local
     */
    public final void descripcionValida(String desc) {
        if (desc.length() > 300) {
            throw new IllegalArgumentException("La descripción debe tener como máximo 300 caracteres.");
        }
        this.descripcion = desc;
    }
    
     /**
     * Función que añade un propietario al local.
     * @param propietario Propietario a añadir
     * @return Booleano que indica si la operacion ha tenido éxito.
     */
    public boolean addPropietario(Propietario propietario) {
        if(this.propietarios.size() > 2) {
            throw new IllegalArgumentException("Número máximo de propietarios alcanzado.");
        }
        this.propietarios.add(propietario);
        return true;
    }
    
     /**
     * Función que elimina un propietario del local.
     * @param propietario Propietario a eliminar.
     * @return Booleano que indica si la operacion ha tenido éxito.
     */
    public boolean removePropietario(Propietario propietario) {
        if(this.propietarios.size() < 1) {
            throw new IllegalArgumentException("El local no tiene propietarios.");
        }
        for(int i = 0; i < this.propietarios.size(); i++){
            if(this.propietarios.get(i).equals(propietario)){
                this.propietarios.remove(i);
                return true;
            }
        }
        throw new IllegalArgumentException("Esa persona no es propietaria de ese local.");
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getDireccion());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
          return true;
        if (obj == null)
          return false;
        if (getClass() != obj.getClass())
          return false;
        Local other = (Local) obj;
        return Objects.equals(getDireccion(), other.getDireccion());
    }

    @Override
    public String toString() {
        return "Local{" + "nombre=" + nombre + ", direccion=" + direccion + ", descripcion=" + descripcion + ", tipo=" + tipo + ", propietarios=" + propietarios + '}';
    }
    
}
