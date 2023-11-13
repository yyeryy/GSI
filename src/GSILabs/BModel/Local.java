package GSILabs.BModel;

import static GSILabs.BSystem.BusinessSystem.formatearXML;
import GSILabs.serializable.XMLRepresentable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase Local
 * @author Grupo 3 - GSI
 * @version 1.0
 * @since 04.09.2023
 */
public class Local implements XMLRepresentable{
    private String nombre;
    private Direccion direccion;
    private String descripcion;
    private tipoLocal tipo;
    private ArrayList<Propietario> propietarios;

    public enum tipoLocal {
        BAR,
        RESTAURANTE,
        PUB;
        
        public static tipoLocal parse(String text){
            if(BAR.name().equals(text)){
                return BAR;
            }
            else if(RESTAURANTE.name().equals(text)){
                return RESTAURANTE;
            }
            else if(PUB.name().equals(text)){
                return PUB;
            }
            else{
                throw new IllegalArgumentException("El argumento es inválido.");
            }
        }
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
    
    public ArrayList<Propietario> getPropietarios() {
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
        String salida = "Local{" + "nombre=" + nombre + ", dirección=" + direccion.toString() + ", descripción=" + descripcion + ", tipo=" + tipo + ", ";
        for(int i = 0; i < propietarios.size(); i++){
            salida = salida + "Propietario=" + propietarios.get(i).toString();
            if(i != propietarios.size() - 1)
                salida = salida + ", ";
        }
        return salida + "}";
    }
    
    public String toXML() {
        String[] partes;
        String xmlData = "";
        // Cabecera
        xmlData += "<Local>\n";
        // Nombre
        xmlData += "<nombre>" + this.getNombre() + "</nombre>\n";
        // Direccion
        partes = this.getDireccion().toXML().split("<Direccion>", 2);
        xmlData += "<Direccion>" + partes[1];
        // Descripcion
        xmlData += "<descripcion>" + this.getDescripcion() + "</descripcion>\n";
        // Tipo
        xmlData += "<tipo>" + this.getTipo().toString()+ "</tipo>\n";
        // Propietario
        for(int i = 0; i<this.getPropietarios().size(); i++){
            partes = this.getPropietarios().get(i).toXML().split("<Propietario>", 2);
            xmlData += "<Propietario>" + partes[1];
        }
        // Cierre
        xmlData += "</Local>\n";
        return formatearXML(xmlData);
    }
    
    public boolean saveToXML(File f) {
        try {
        String xmlData = toXML();
            try (FileWriter writer = new FileWriter(f)) {
                writer.write(xmlData);
            }
        return true;
        } catch (IOException e) {
            return false;
        }
    }

    public boolean saveToXML(String filePath) {
        File file = new File(filePath);
        return saveToXML(file);
    }
}
