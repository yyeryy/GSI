package GSILabs.BModel;

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
