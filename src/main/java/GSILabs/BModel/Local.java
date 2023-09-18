package GSILabs.BModel;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author 34636
 */
public class Local {
    /*El sistema almacena información de locales de ocio en España. Un local tienen un nombre y está
    en una localización concreta. (Localidad, Provincia, Calle y Número). No puede haber dos locales
    en la misma dirección. Los locales pueden almacenar una breve descripción de no más de 300
    caracteres.*/
    String nombre;
    private Direccion direccion;
    private String descripcion;
    private tipoLocal tipo;
    private ArrayList<Usuario> propietarios;

    public enum tipoLocal {
        BAR,
        RESTAURANTE,
        PUB
    }

    public Local(String nombre, Direccion direccion, String descripcion, tipoLocal tipo) {
        this.nombre = nombre;
        this.direccion = direccion;
        descripcionValida(descripcion);
        this.tipo = tipo;
        this.propietarios = new ArrayList<>();
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
    
    
    // Función que comprueba que la descripción del local no supere los 300 caracteres.
    public void descripcionValida(String desc) {
        if (desc.length() > 300) {
            throw new IllegalArgumentException("La descripción debe tener como máximo 300 caracteres.");
        }
        this.descripcion = desc;
    }
    
    public boolean addPropietario(Propietario propietario) {
        if(this.propietarios.size() > 2) {
            throw new IllegalArgumentException("Número máximo de propietarios alcanzado.");
        }
        this.propietarios.add(propietario);
        return true;
    }
    
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
        return Objects.hash(direccion);
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
        return Objects.equals(direccion, other.direccion);
    }
}
